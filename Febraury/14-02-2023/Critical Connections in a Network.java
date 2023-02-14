/*
There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.

 

Example 1:


Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.
Example 2:

Input: n = 2, connections = [[0,1]]
Output: [[0,1]]
 

Constraints:

2 <= n <= 105
n - 1 <= connections.length <= 105
0 <= ai, bi <= n - 1
ai != bi
There are no repeated connections.
*/

class Solution {
    List<List<Integer>> res;
    int timer = 0;
    public void criticalEdge(int u, int par, boolean[] visited, List<List<Integer>> adj, int[] dis, int[] low){
        
        //updating values of low and discover
        dis[u] = timer;
        low[u] = timer;
        visited[u] = true;
        timer ++; //incrementing timer
        //recurssive case
        for(int v: adj.get(u)){
            if(v!=par && !visited[v]){
                criticalEdge(v, u, visited, adj, dis, low);
                //also update for parent
                //checking for bridge
                low[u] =Math.min(low[u], low[v]);
                if(low[v]>dis[u]){
                    res.add(Arrays.asList(u, v));
                }
                
            }
            else if(v!=par && visited[v]){
                low[u]=Math.min(low[u], low[v]);//changing value to least value.
            }
        }
        
        
    }
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> adj= new ArrayList<>();
        int[] dis = new int[n];
        int[] low = new int[n];
        
        res = new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<Integer>());
        }
        for(List edges: connections){
            int u = (int)edges.get(0);
            int v = (int)edges.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        boolean[] visited = new boolean [n];
        
        criticalEdge(0, -1, visited, adj, dis, low);
        return res;
    }
}
