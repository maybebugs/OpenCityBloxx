import re, subprocess, os, sys, glob, json
base='reference/jar_extracted/'
J=['java','-m','jdk.jdeps/com.sun.tools.javap.Main']
def jtype(d):
    out=[];i=0
    while i<len(d):
        arr=0
        while d[i]=='[': arr+=1;i+=1
        c=d[i]
        if c=='L':
            j=d.index(';',i);t=d[i+1:j].split('/')[-1];i=j+1
        else:
            t={'I':'int','Z':'boolean','B':'byte','C':'char','S':'short','J':'long','F':'float','D':'double','V':'void'}[c];i+=1
        out.append(t+'[]'*arr)
    return out
def bytecode_methods(cls):
    txt=subprocess.run(J+['-c','-p',base+cls+'.class'],capture_output=True,text=True).stdout.split('\n')
    meths={}; cur=None
    for l in txt:
        m=re.match(r'  (?:public |private |protected |static |final |abstract |synchronized |native )*([\w.\[\]$<>]+) (\w+|"?<init>"?|static \{\})\((.*)\)(?: throws .*)?;$',l)
        m2=re.match(r'  (?:public |private |protected |static |final )*(\w+)\((.*)\);$',l)
        if m or m2 or l.strip()=='static {};':
            if m: name=m.group(2); params=m.group(3)
            elif m2: name='<init>'; params=m2.group(2)
            else: name='<clinit>'; params=''
            plist=tuple(p.strip().split('.')[-1] for p in params.split(',') if p.strip())
            cur=(name,plist); meths[cur]=set(); continue
        if cur:
            mm=re.search(r'(getstatic|putstatic|getfield|putfield)\s+#\d+\s+// Field ([\w/$.]+?)\.?([\w$]+):',l)
            mm=re.search(r'(getstatic|putstatic|getfield|putfield)\s+#\d+\s+// Field (?:([\w/$.]+)\.)?([\w$]+):',l)
            if mm:
                owner=mm.group(2) or cls
                meths[cur].add((owner.split('/')[-1],mm.group(3),mm.group(1)[:3]))
    return meths
def split_methods(src):
    res=[]
    for m in re.finditer(r'\n    (?:public |private |protected |static |final |abstract |synchronized )*([\w\[\]<>]+) (\w+)\(([^)]*)\)[^{;]*\{',src):
        start=m.end();depth=1;i=start
        while depth and i<len(src):
            c=src[i]
            if c=='{':depth+=1
            elif c=='}':depth-=1
            i+=1
        plist=tuple(p.strip().rsplit(' ',1)[0].split('.')[-1] for p in m.group(3).split(',') if p.strip())
        res.append(((m.group(2),plist),m.group(3),src[start:i]))
    return res
def src_fields(cls,body,params,allfields):
    # locals: declared names
    declre=re.compile(r'(?<![\w.])(?:final\s+)?(?:int|boolean|byte|short|long|char|float|double|[A-Z][\w]*)(?:\[\])*\s+([A-Za-z_]\w*)\s*(?==|;|,|\)|:)')
    locs=set(declre.findall(body))
    for p in params.split(','):
        p=p.strip().split()
        if p: locs.add(p[-1])
    used=set()
    for m in re.finditer(r'this\.(\w+)',body): used.add(m.group(1))
    for m in re.finditer(r'(?<![\w.])([A-Za-z_]\w*)(?![\w(])',body):
        n=m.group(1)
        if n in allfields and n not in locs:
            # skip if followed by '.' and is a class name (e.g. g.d()) -> only when type is not field
            used.add(n)
    return used,locs
if __name__=='__main__':
    cls=sys.argv[1]
    src=open('reference/decompiled/defpackage/%s.java'%cls).read()
    bc=bytecode_methods(cls)
    own=set()
    for k,v in bc.items():
        for (o,n,op) in v:
            if o==cls: own.add(n)
    # all declared fields
    out=subprocess.run(J+['-p',base+cls+'.class'],capture_output=True,text=True).stdout
    fields=set()
    for l in out.splitlines():
        l=l.strip()
        if l.endswith(';') and '(' not in l: fields.add(l[:-1].split()[-1])
    allf=fields|own
    sm=split_methods(src)
    seen=set()
    for key,params,body in sm:
        if key not in bc: print('NOBC',key); continue
        seen.add(key)
        bset=set(n for (o,n,op) in bc[key] if o==cls or o=='GameMIDlet' and cls=='House')
        sset,locs=src_fields(cls,body,params,allf)
        # a name used via ClassName.field e.g. House.d
        for m in re.finditer(r'\b%s\.(\w+)'%cls,body): sset.add(m.group(1))
        miss=bset-sset; extra=sset-bset
        if miss or (extra&locs):
            print(key[0],key[1],'| bytecode-only:',sorted(miss),'| src-only(shadow?):',sorted(extra&allf))
