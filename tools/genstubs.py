import re, os, collections
refs = collections.defaultdict(set)
kinds = {}
for line in open('allv.txt', errors='replace'):
    m = re.search(r'// +(Method|InterfaceMethod|Field) +((?:javax|com/nokia)[^ ]+)\.("?[^:". ]+"?):(\S+)', line)
    if m:
        k, cls, name, desc = m.groups()
        if cls.startswith('com/nokia/mid/appl'): continue
        refs[cls].add((k, name, desc))
        if k == 'InterfaceMethod': kinds[cls] = 'interface'
def jt(d):
    out=[]; i=0
    while i < len(d):
        arr=0
        while d[i]=='[': arr+=1; i+=1
        c=d[i]
        if c=='L':
            j=d.index(';',i); t=d[i+1:j].replace('/','.'); i=j+1
        else:
            t={'I':'int','Z':'boolean','B':'byte','C':'char','S':'short','J':'long','F':'float','D':'double','V':'void'}[c]; i+=1
        out.append(t+'[]'*arr)
    return out
def sig(desc):
    a, r = desc[1:].split(')')
    return jt(a), jt(r)[0]
extra_if = {'javax/microedition/lcdui/CommandListener':[], 'javax/microedition/media/PlayerListener':[], 'javax/microedition/media/control/VolumeControl':[], 'javax/microedition/media/Control':[]}
os.makedirs('stubs', exist_ok=True)
supers = {'javax/microedition/m3g/Mesh':'javax.microedition.m3g.Node','javax/microedition/m3g/World':'javax.microedition.m3g.Node','javax/microedition/m3g/Camera':'javax.microedition.m3g.Node',
 'javax/microedition/lcdui/Canvas':'javax.microedition.lcdui.Displayable','javax/microedition/lcdui/Form':'javax.microedition.lcdui.Screen','com/nokia/mid/ui/FullCanvas':'javax.microedition.lcdui.Canvas',
 'javax/microedition/m3g/Texture2D':'javax.microedition.m3g.Transformable','javax/microedition/m3g/Background':'javax.microedition.m3g.Object3D','javax/microedition/m3g/Appearance':'javax.microedition.m3g.Object3D'}
def write(cls, body, kind='class', sup=None, extra=''):
    pkg, name = cls.rsplit('/',1)
    p = 'stubs/'+cls+'.java'; os.makedirs(os.path.dirname(p), exist_ok=True)
    s = 'package %s;\n' % pkg.replace('/','.')
    s += 'public %s %s%s%s {\n%s\n}\n' % (kind if kind!='abstract' else 'abstract class', name, (' extends '+sup) if sup else '', extra, body)
    open(p,'w').write(s)
generated=set()
for cls, rs in refs.items():
    kind = kinds.get(cls,'class')
    body=[]
    for k,name,desc in sorted(rs):
        if k=='Field':
            t=jt(desc)[0]; body.append('  public static %s %s;' % (t,name)); continue
        if name.strip('"')=='<init>':
            a,_=sig(desc); body.append('  public %s(%s) {}' % (cls.rsplit('/',1)[1], ', '.join('%s a%d'%(t,i) for i,t in enumerate(a)))); continue
        a,r=sig(desc)
        params=', '.join('%s a%d'%(t,i) for i,t in enumerate(a))
        ret = '' if r=='void' else ' return %s;' % ({'int':'0','boolean':'false','long':'0','float':'0','short':'0','byte':'0','char':'0'}.get(r,'null'))
        if kind=='interface':
            body.append('  %s %s(%s);' % (r,name,params))
        else:
            st = 'static ' if k=='Method' and cls in ('javax/microedition/lcdui/Font','javax/microedition/lcdui/Image','javax/microedition/lcdui/Display','javax/microedition/m3g/Graphics3D','javax/microedition/m3g/Loader','javax/microedition/media/Manager','javax/microedition/rms/RecordStore','com/nokia/mid/ui/DeviceControl','com/nokia/mid/ui/DirectUtils') and name not in ('getHeight','stringWidth','substringWidth','getWidth','getRGB','getGraphics','getGraphics') else ''
            body.append('  public %s%s %s(%s) {%s}' % (st, r, name, params, ret))
    write(cls, '\n'.join(body), kind, supers.get(cls))
    generated.add(cls)
open('gen.txt','w').write('\n'.join(sorted(generated)))
