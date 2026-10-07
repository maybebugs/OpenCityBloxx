import re,sys
def replace_method(path, header_regex, new_text):
    s=open(path).read()
    m=re.search(header_regex,s)
    if not m: raise Exception('header not found: '+header_regex)
    i=s.index('{',m.start()); depth=0; j=i
    while True:
        c=s[j]
        if c=='{':depth+=1
        elif c=='}':
            depth-=1
            if depth==0: break
        j+=1
    s=s[:m.start()]+new_text.rstrip('\n')+s[j+1:]
    open(path,'w').write(s)
