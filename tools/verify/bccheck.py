import re,subprocess,sys,os,collections
A,B=sys.argv[1],sys.argv[2]   # class dirs: A=reference (old names), B=candidate (new names)
def javap(d,cls):
    return subprocess.run(['./javap.sh','-p','-c','-cp',d,cls],capture_output=True,text=True).stdout
def classes(d):
    out=[]
    for r,_,fs in os.walk(d):
        for f in fs:
            if f.endswith('.class'): out.append(os.path.relpath(os.path.join(r,f),d)[:-6].replace('/','.'))
    return sorted(out)
hdr_re=re.compile(r'^  ((?:public |private |protected |static |final |abstract |synchronized |native |volatile |transient )*)(.*?)(\(.*\))?(?: throws [\w., ]+)?;$')
def members(text):
    fields=[];methods=[]
    cur=None
    for line in text.split('\n'):
        if line.startswith('  ') and not line.startswith('   ') and line.rstrip().endswith(';'):
            m=hdr_re.match(line)
            if not m: continue
            mods,rest,params=m.groups()
            if params is None:
                name=rest.split()[-1]; typ=' '.join(rest.split()[:-1]); fields.append((name,mods.strip(),typ))
            else:
                parts=rest.split()
                name=parts[-1] if parts else ''
                ret=' '.join(parts[:-1])
                methods.append((name,mods.strip(),ret,params))
    return fields,methods
ADDED=set(filter(None,os.environ.get('ADDED','').split(',')))
EXCL=set(filter(None,os.environ.get('EXCL','').split(',')))
ca,cb=classes(A),classes(B)
assert ca==cb,(set(ca)^set(cb))
texts={c:(javap(A,c),javap(B,c)) for c in ca}
# build member maps
fmap={};mmap={}   # (cls,old)->new ; (cls,old,params)->new
problems=[]
for c in ca:
    fa,ma=members(texts[c][0]); fb,mb=members(texts[c][1])
    fb=[x for x in fb if x[0] not in ADDED]; mb=[x for x in mb if x[0] not in ADDED]
    if len(fa)!=len(fb) or len(ma)!=len(mb): problems.append(f'{c}: member count differs fields {len(fa)}/{len(fb)} methods {len(ma)}/{len(mb)}'); continue
    for x,y in zip(fa,fb):
        if x[1:]!=y[1:]: problems.append(f'{c}: field {x} vs {y}')
        fmap[(c,x[0])]=y[0]
    for x,y in zip(ma,mb):
        if x[1:]!=y[1:]: problems.append(f'{c}: method {x} vs {y}')
        mmap[(c,x[0],x[3])]=y[0]
ref_re=re.compile(r'// (Field|Method|InterfaceMethod) ([\w$./]+?)(?:\.)?(?:("<init>"|"<clinit>")|([\w$]+)):(\S+)$')
def sig_to_params(desc):
    return desc
def norm(text,cur,mapit):
    out=[]
    for line in text.split('\n'):
        line=re.sub(r'#\d+(,\s*\d+)?','#',line)
        line=re.sub(r'^\s+\d+: ','    ',line)
        line=re.sub(r'\b(ifeq|ifne|iflt|ifge|ifgt|ifle|if_icmpeq|if_icmpne|if_icmplt|if_icmpge|if_icmpgt|if_icmple|if_acmpeq|if_acmpne|ifnull|ifnonnull|goto|goto_w)\s+\d+',r'\1 T',line)
        line=re.sub(r'^(\s+)(-?\d+|default): \d+$',r'\1\2: T',line)
        line=line.replace('ldc_w','ldc').replace('ldc2_w','ldc2_w')
        line=re.sub(r'\s+//','  //',line)
        line=re.sub(r'(\S)\s{2,}(\S)',r'\1 \2',line)
        m=re.search(r'// (Field|Method|InterfaceMethod) (?:([\w$./]+)\.)?([\w$<>"]+):(\S+)',line)
        if m and mapit:
            kind,owner,name,desc=m.groups()
            oc=(owner or cur).replace('/','.')
            if kind=='Field':
                nn=fmap.get((oc,name),name)
            else:
                nn=name
                # find method with same old name & matching descriptor via params text: compare by descriptor
                nn=mdesc.get((oc,name,desc),name)
            line=line[:m.start()]+f'// {kind} {oc}.{nn}:{desc}'
        elif m:
            kind,owner,name,desc=m.groups(); oc=(owner or cur).replace('/','.')
            line=line[:m.start()]+f'// {kind} {oc}.{name}:{desc}'
        out.append(line)
    return '\n'.join(out)
# need descriptor-keyed method map: derive from code comments of definitions? use header param text -> descriptor is hard; instead
# collect descriptor via invocation sites: pair mmap entries by (cls,name,paramtext) and convert paramtext to descriptor
prim={'int':'I','boolean':'Z','byte':'B','short':'S','long':'J','char':'C','float':'F','double':'D','void':'V'}
def to_desc(t):
    dims=t.count('[]'); t=t.replace('[]','').strip()
    if t in prim: d=prim[t]
    else: d='L'+t.replace('.','/')+';'
    return '['*dims+d
mdesc={}
for (c,name,params),new in mmap.items():
    inner=params.strip()[1:-1].strip()
    args=[a.strip() for a in inner.split(',')] if inner else []
    # find return type from members list
for c in ca:
    fa,ma=members(texts[c][0])
    for (name,mods,ret,params) in ma:
        inner=params.strip()[1:-1].strip()
        args=[a.strip() for a in inner.split(',')] if inner else []
        d='('+''.join(to_desc(a) for a in args)+')'+to_desc(ret if ret else 'void')
        mdesc[(c,name,d)]=mmap[(c,name,params)]
import json
with open(os.environ.get('MAPOUT','/dev/null'),'w') as fh:
    for (c,old),new in fmap.items():
        if c=='House': fh.write(f'{old} {new}\n')
    for (c,old,params),new in mmap.items():
        if c=='House' and old=='s': fh.write(f'METHOD:{old} {new}\n')
with open(os.environ.get('FULLOUT','/dev/null'),'w') as fh:
    last=None
    for (c,old),new in fmap.items():
        if old!=new:
            if c!=last: fh.write(f'# --- {c}\n'); last=c
            fh.write(f'{c}.{old} = {new}\n')
    for (c,old,params),new in mmap.items():
        if old!=new and old not in ('<init>',):
            if c!=last: fh.write(f'# --- {c}\n'); last=c
            fh.write(f'{c}.{old}{params.replace(", ",",")} = {new}\n')
diffs=collections.OrderedDict()
for c in ca:
    na=norm(texts[c][0],c,True); nb=norm(texts[c][1],c,False)
    # compare method by method (split on blank-line separated blocks, header names differ -> strip headers)
    def blocks(t):
        bs=[];cur=[]
        for l in t.split('\n'):
            if l.startswith('  ') and not l.startswith('   ') and l.rstrip().endswith(';') or l.startswith('  static {}'):
                if cur: bs.append(cur)
                cur=[l]
            else: cur.append(l)
        if cur: bs.append(cur)
        return bs
    ba,bb=blocks(na),blocks(nb)
    def nm(bl):
        m=re.search(r'([\w$]+)\(',bl[0]) or re.search(r'([\w$]+);\s*$',bl[0]); return m.group(1) if m else ''
    bb=[x for x in bb if nm(x) not in ADDED]
    if len(ba)!=len(bb): diffs[c+' (block count)']=(len(ba),len(bb)); continue
    for x,y in zip(ba,bb):
        # drop header line (names differ), compare body
        if c+'::'+nm(y) in EXCL: continue
        bx=[l for l in x[1:] if l.strip()]; by=[l for l in y[1:] if l.strip()]
        if bx!=by:
            diffs[c+' :: '+y[0].strip()]=(len(bx),len(by),next((f'{p!r} != {q!r}' for p,q in zip(bx,by) if p!=q),'length'))
print('classes',len(ca),'methods compared ok except:',len(diffs))
for k,v in list(diffs.items())[:60]: print(' DIFF',k,v)
for p in problems[:20]: print('PROBLEM',p)
