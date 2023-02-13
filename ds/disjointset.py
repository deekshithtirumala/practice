class DisjointSet:
    def __init__(self, n):
        self.rank = []
        self.parent = []
        
        for i in range(n+1):
            self.rank.append(0)
            self.parent.append(i)
    def findUlparent(self, u):
        if u==self.parent[u]:
            return u
        
        #recurrsive case
        self.parent[u] = self.findUlparent(self.parent[u])
        return self.parent[u]
    def unionSet(self, u, v):
        ulpar_u = self.findUlparent(u)
        ulpar_v = self.findUlparent(v)
        
        if(ulpar_v==ulpar_u):
            return
        
        elif (self.rank[u]<self.rank[v]):
            self.parent[u] = ulpar_v
        elif (self.rank[u]>self.rank[v]):
            self.parent[v] = ulpar_u
        else:
            self.parent[v] = ulpar_u
            self.rank[u]+=1
        

dis = DisjointSet(7)
dis.unionSet(1, 2)
dis.unionSet(2, 3)
dis.unionSet(4, 5)
dis.unionSet(6, 7)
dis.unionSet(4, 6)
#dis.unionSet(3, 4)

print(dis.findUlparent(3), dis.findUlparent(4))
