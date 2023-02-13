/*
Given a graph which consists of several edges connecting its nodes, find a subgraph of the given graph with the following properties:

The subgraph contains all the nodes present in the original graph.
The subgraph is of minimum overall weight (sum of all edges) among all such subgraphs.
It is also required that there is exactly one, exclusive path between any two nodes of the subgraph.
One specific node  is fixed as the starting point of finding the subgraph using Prim's Algorithm.
Find the total weight or the sum of all edges in the subgraph.

Example



image
Starting from node , select the lower weight edge, i.e. , weight .

Choose between the remaining edges, , weight , and , weight .

The lower weight edge is  weight .

All nodes are connected at a cost of . The edge  is not included in the subgraph.

Function Description

Complete the prims function in the editor below.

prims has the following parameter(s):

int n: the number of nodes in the graph
int edges[m][3]: each element contains three integers, two nodes numbers that are connected and the weight of that edge
int start: the number of the starting node
Returns

int: the minimum weight to connect all nodes in the graph
Input Format

The first line has two space-separated integers  and , the number of nodes and edges in the graph.

Each of the next  lines contains three space-separated integers ,  and , the end nodes of , and the edge's weight.
The last line has an integer , the starting node.

Constraints





There may be multiple edges between two nodes.

Sample Input 0

5 6
1 2 3
1 3 4
4 2 6
5 2 2
2 3 5
3 5 7
1
Sample Output 0

15
Explanation 0

The graph given in the test case is shown as :

image

The starting node is  (in the given test case)
Applying the Prim's algorithm, edge choices available at first are :

 (WT. 3) and  (WT. 4) , out of which  is chosen (smaller weight of edge).

Now the available choices are :

 (WT. 4) ,  (WT. 5) ,  (WT. 2) and  (WT. 6) , out of which  is chosen by the algorithm.

Following the same method of the algorithm, the next chosen edges , sequentially are :

 and .

Hence the overall sequence of edges picked up by Prim's are:


and the total weight of the MST (minimum spanning tree) is : 
*/

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
class DisjointSet{
    List<Integer> rank = new ArrayList<Integer>();
    List<Integer> parent = new ArrayList<Integer>();
    public DisjointSet(int n){
        for(int i=0;i<=n;i++){
            rank.add(0);
            parent.add(i);
        }
    }
    public int findUlparent(int u){
        if(u==parent.get(u)){
            return u;
        }
        
        //recurrsive case
        parent.set(u, findUlparent(parent.get(u)));
        return parent.get(u);
    }
    
    public void unionSet(int u, int v){
        int ultpar_u = findUlparent(u);
        int ultpar_v = findUlparent(v);
        
        if(ultpar_u==ultpar_v){
            return ;
        }
        
        else if(rank.get(ultpar_u)<rank.get(ultpar_v)){
            //adding u to v --> u's parent = v 
            //1      4        4
            //|\     |\      /|\
            //2 3    5 6    1 5 6
            //       | |   /| | |
            //       8 7  2 3 8 7
            //rank of 1's = 1 and rank of 4's = 2
            //ultimate parent of 3's parent is set to ultimate parent 7
            parent.set(ultpar_u, ultpar_v);
        }
        else if(rank.get(ultpar_u)> rank.get(ultpar_v)){
            parent.set(ultpar_v, ultpar_u);
        }
        else{
            //rank are equal rank should be incremented
            parent.set(ultpar_v, ultpar_u);
            rank.set(ultpar_u, rank.get(ultpar_u)+1);
        }
    }
}
class Result {

    /*
     * Complete the 'prims' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY edges
     *  3. INTEGER start
     */

    public static int prims(int n, List<List<Integer>> edges, int start) {
        Collections.sort(edges, new Comparator<List<Integer>>(){
            //@Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(2).compareTo(o2.get(2));
            }
        });
        DisjointSet dis = new DisjointSet(n);
        int total_weight = 0;
        for(List edgeItem: edges){
            int u = (int)edgeItem.get(0);
            int v = (int)edgeItem.get(1);
            int w = (int)edgeItem.get(2);
            if(dis.findUlparent(u)!=dis.findUlparent(v)){
                total_weight +=w;
            }
            dis.unionSet(u, v);
        }
        System.out.println(edges);
        return total_weight;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String[] edgesRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> edgesRowItems = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                int edgesItem = Integer.parseInt(edgesRowTempItems[j]);
                edgesRowItems.add(edgesItem);
            }

            edges.add(edgesRowItems);
        }

        int start = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.prims(n, edges, start);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
