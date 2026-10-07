import re, os, shutil, sys
SRC='reference/decompiled'
DST='stage1'
CLASSES={'a':'ModalScreen','b':'Vibra','c':'PlatformFactory','d':'Mesh3D','e':'Screen','f':'Storage','g':'Resources',
 'h':'HighScores','i':'Ui','j':'MenuController','k':'CityMode','l':'TextEntryListener','m':'ICanvas','n':'Renderer3D',
 'o':'SoundPlayer','p':'GameCanvas'}
BLOXX_A='Lang'
KEYWORD_FIX={'do':'do_'}
tok=re.compile(r'("(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\'|//[^\n]*|/\*.*?\*/|[A-Za-z_$][\w$]*|\s+|.)',re.S)
import json
STATIC=json.load(open('static_members.json'))
FORCED_FIELD={'House':{'b','c'},'HighScores':{'n'},'h':{'n'},'MenuController':{'p'},'j':{'p'}}
DECL=re.compile(r'(?<![\w.])(?:final\s+)?[A-Za-z_][\w]*(?:\[\])*\s+([A-Za-z_]\w*)\s*(?==|;|,|\)|:)')
def method_spans(src):
    spans=[];depth=0;start=None;i=0
    for m in re.finditer(r'[{}]',src):
        if m.group()=='{':
            if depth==1: start=m.start()
            depth+=1
        else:
            depth-=1
            if depth==1 and start is not None: spans.append((start,m.end())); start=None
    return spans
def process(name,src,is_bloxx=False):
    toks=tok.findall(src)
    # compute char offsets and locals per method span
    offs=[];o=0
    for t in toks: offs.append(o); o+=len(t)
    spans=method_spans(src)
    # header (signature) start for each span: previous ';' or '}' or '{' at depth1
    locals_at={}
    hdr=[]
    for (a,b) in spans:
        k=max(src.rfind(';',0,a),src.rfind('}',0,a),src.rfind('{',0,a))
        hdr.append((k+1,b))
    import bisect
    def locs_for(pos):
        for (a,b) in hdr:
            if a<=pos<b:
                if (a,b) not in locals_at:
                    seg=src[a:b]
                    # strip type tokens that are classes? just collect declared names
                    L=set(DECL.findall(seg))
                    sig=seg[:seg.find('{')]
                    for p in sig[sig.find('(')+1:sig.rfind(')')].split(','):
                        p=p.strip().split()
                        if p: L.add(p[-1])
                    locals_at[(a,b)]=L
                return locals_at[(a,b)]
        return set()
    out=[]; n=len(toks)
    def prev_sig(i):
        j=i-1
        while j>=0 and toks[j].isspace(): j-=1
        return j
    def next_sig(i):
        j=i+1
        while j<n and toks[j].isspace(): j+=1
        return j
    in_impl=False
    for i,t in enumerate(toks):
        if t in('implements','extends'): in_impl=True
        if t=='{': in_impl=False
        if re.fullmatch(r'[A-Za-z_$][\w$]*',t):
            p=prev_sig(i); q=next_sig(i)
            pt=toks[p] if p>=0 else ''; qt=toks[q] if q<n else ''
            if pt=='.':   # member access, never a class token unless package-qualified
                out.append(t); continue
            if t in KEYWORD_FIX and t=='do' and not (qt=='(' or qt=='{' ):
                out.append('do_'); continue
            if t in CLASSES or (is_bloxx and False):
                typ_ctx = in_impl or pt in('new','extends','implements','instanceof') or (re.fullmatch(r'[A-Za-z_$][\w$]*',qt) and qt not in ('instanceof',)) or qt=='[' and toks[next_sig(q)]==']' or (pt=='(' and qt==')' and False)
                cast = pt=='(' and qt==')' and toks[prev_sig(p)] not in ('if','while','for','switch','synchronized','catch') and toks[next_sig(q)] not in ('{',';',')','&&','||','?',':','==','!=','+','-','*','/','<','>')
                qual = qt=='.'
                # House.b / House.c are instance fields of type SoundPlayer/Vibra: x.b.a(...) handled below
                L=locs_for(offs[i])
                if t in L and not (typ_ctx and not qual): 
                    out.append(t); continue
                if qual and t in FORCED_FIELD.get(name,()): out.append(t); continue
                if qual and name in('House','GameMIDlet','HighScores','MenuController') and t in L: out.append(t); continue
                if qual:
                    mem=toks[next_sig(q)]
                    if name==t: pass
                    elif mem not in STATIC.get(t,[]) and name in('MenuController','HighScores','House','GameMIDlet','CityMode','Ui','Storage','Resources','Renderer3D','SoundPlayer','GameCanvas','TextEntryListener','Mesh3D'):
                        out.append(t); continue
                if typ_ctx or cast or qual:
                    # exceptions: local/field named like class followed by ident?  (decl "Type name" handled by typ_ctx)
                    out.append(CLASSES[t]); continue
            out.append(t)
        else: out.append(t)
    return ''.join(out).replace('com.nokia.mid.appl.bloxx.a.','com.nokia.mid.appl.bloxx.Lang.')
def main():
    if os.path.exists(DST): shutil.rmtree(DST)
    for root,_,files in os.walk(SRC):
        for f in files:
            if not f.endswith('.java'): continue
            p=os.path.join(root,f); rel=os.path.relpath(p,SRC)
            src=open(p).read()
            base=f[:-5]
            if rel.startswith('com/nokia'):
                new=process('bloxx_a',src,True)
                new=new.replace('public final class a {','public final class %s {'%BLOXX_A).replace('private static a b = null','private static %s b = null'%BLOXX_A)
                new=re.sub(r'\bnew a\(\)','new %s()'%BLOXX_A,new).replace('private a()','private %s()'%BLOXX_A).replace('a.class','%s.class'%BLOXX_A)
                new=re.sub(r'(?<![\w.])a (b|c)\b',r'%s \1'%BLOXX_A,new) if False else new
                outp=os.path.join(DST,'com/nokia/mid/appl/bloxx/%s.java'%BLOXX_A); new=open('lang_stage1.java').read()
            else:
                new=process(CLASSES.get(base,base),src)
                nm=CLASSES.get(base,base)
                if base in CLASSES:
                    new=re.sub(r'\bclass %s\b'%base,'class %s'%nm,new,1)
                    new=re.sub(r'\binterface %s\b'%base,'interface %s'%nm,new,1)
                    # constructors
                    new=re.sub(r'(\n\s+(?:public |private |protected )?)%s\('%base,r'\g<1>%s('%nm,new)
                new=new.replace('package defpackage;','')
                outp=os.path.join(DST,nm+'.java')
            os.makedirs(os.path.dirname(outp),exist_ok=True)
            open(outp,'w').write(new)
main()
