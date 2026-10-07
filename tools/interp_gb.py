import re, json
lines = open('g_c.txt').read().split('\n')
start = next(i for i,l in enumerate(lines) if 'static java.lang.String b(int, java.lang.String[]);' in l)
code = {}; order=[]
sw = {}
i = start+2
in_sw=None
while i < len(lines):
    l = lines[i]
    if l.strip()=='' or l.startswith('    Exception table'): break
    if in_sw is not None:
        if l.strip()=='}': in_sw=None
        else:
            m2=re.match(r'\s+(\d+|default): (\d+)', l)
            sw[in_sw][m2.group(1)]=int(m2.group(2))
        i+=1; continue
    m = re.match(r'\s+(\d+): (\w+)\s*(.*)', l)
    if m:
        pc=int(m.group(1)); op=m.group(2); rest=m.group(3)
        code[pc]=(op,rest); order.append(pc)
        if op=='tableswitch': sw[pc]={}; in_sw=pc
    i+=1
nxt = {order[k]:order[k+1] for k in range(len(order)-1)}
def run(idarg, args_nonnull):
    L = {0:idarg, 1:('ARGS' if args_nonnull else None), 2:None, 3:None}
    st=[]; pc=0; keys=None
    while True:
        op,rest=code[pc]; n=nxt.get(pc)
        if op=='iconst_m1': st.append(-1)
        elif op.startswith('iconst_'): st.append(int(op[7:]))
        elif op in('bipush','sipush'): st.append(int(rest))
        elif op=='aconst_null': st.append(None)
        elif op.startswith('istore_') or op.startswith('astore_'): L[int(op[-1])]=st.pop()
        elif op.startswith('iload_') or op.startswith('aload_'): st.append(L[int(op[-1])])
        elif op=='astore': L[int(rest)]=st.pop()
        elif op=='aload': st.append(L[int(rest)])
        elif op=='dup': st.append(st[-1])
        elif op=='pop': st.pop()
        elif op=='goto': n=int(rest)
        elif op=='ifnull':
            v=st.pop(); 
            if v is None: n=int(rest)
        elif op=='ifnonnull':
            v=st.pop()
            if v is not None: n=int(rest)
        elif op=='anewarray': c=st.pop(); st.append(['?']*c)
        elif op=='aastore': v=st.pop(); ix=st.pop(); arr=st.pop(); arr[ix]=v
        elif op=='aaload': ix=st.pop(); arr=st.pop(); st.append(arr[ix])
        elif op=='invokestatic' and 'GameMIDlet.q' in rest: st.append('MIDLET')
        elif op=='getfield': st.pop(); st.append('KEYS')
        elif op=='invokeinterface' and 'getKeyName' in rest: k=st.pop(); st.pop(); st.append('key(%d)'%k)
        elif op=='tableswitch':
            v=st.pop(); n=sw[pc].get(str(v), sw[pc]['default'])
        elif op=='invokestatic' and 'bloxx/a.a' in rest:
            a=st.pop(); ix=st.pop(); return ix,a
        else: raise Exception((pc,op,rest))
        pc=n
res={}
for idv in range(0,161):
    r0=run(idv,False); r1=run(idv,True)
    res[idv]=(r0,r1)
json.dump(res,open('gb_map.json','w'))
for idv,(a,b) in res.items():
    print(idv,a,b)
