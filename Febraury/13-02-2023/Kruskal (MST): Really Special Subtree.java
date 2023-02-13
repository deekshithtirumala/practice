/*
Given an undirected weighted connected graph, find the Really Special SubTree in it. The Really Special SubTree is defined as a subgraph consisting of all the nodes in the graph and:

There is only one exclusive path from a node to every other node.
The subgraph is of minimum overall weight (sum of all edges) among all such subgraphs.
No cycles are formed
To create the Really Special SubTree, always pick the edge with smallest weight. Determine if including it will create a cycle. If so, ignore the edge. If there are edges of equal weight available:

Choose the edge that minimizes the sum  where  and  are vertices and  is the edge weight.
If there is still a collision, choose any of them.
Print the overall weight of the tree formed using the rules.

For example, given the following edges:

u	v	wt
1	2	2
2	3	3
3	1	5
First choose  at weight . Next choose  at weight . All nodes are connected without cycles for a total weight of .

Function Description

Complete the  function in the editor below. It should return an integer that represents the total weight of the subtree formed.

kruskals has the following parameters:

g_nodes: an integer that represents the number of nodes in the tree
g_from: an array of integers that represent beginning edge node numbers
g_to: an array of integers that represent ending edge node numbers
g_weight: an array of integers that represent the weights of each edge
Input Format

The first line has two space-separated integers  and , the number of nodes and edges in the graph.

The next  lines each consist of three space-separated integers ,  and , where  and  denote the two nodes between which the undirected edge exists and  denotes the weight of that edge.

Constraints

**Note: ** If there are edges between the same pair of nodes with different weights, they are to be considered as is, like multiple edges.

Output Format

Print a single integer denoting the total weight of the Really Special SubTree.
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
     * Complete the 'kruskals' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts WEIGHTED_INTEGER_GRAPH g as parameter.
     */

    /*
     * For the weighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] and <name>To[i]. The weight of the edge is <name>Weight[i].
     *
     */

    public static int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
        List<List<Integer>> edges = new ArrayList<>();
        for(int i=0;i<gFrom.size();i++){
            List<Integer> lst = new ArrayList<>();
            lst.add(gFrom.get(i));
            lst.add(gTo.get(i));
            lst.add(gWeight.get(i));
            
            edges.add(lst);
        }
        Collections.sort(edges, new Comparator<List<Integer>>(){
            //@Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(2).compareTo(o2.get(2));
            }
        });
        DisjointSet dis = new DisjointSet(gNodes);
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
        System.out.println(total_weight);
        return total_weight;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] gNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int gNodes = Integer.parseInt(gNodesEdges[0]);
        int gEdges = Integer.parseInt(gNodesEdges[1]);

        List<Integer> gFrom = new ArrayList<>();
        List<Integer> gTo = new ArrayList<>();
        List<Integer> gWeight = new ArrayList<>();

        for (int i = 0; i < gEdges; i++) {
            String[] gFromToWeight = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            gFrom.add(Integer.parseInt(gFromToWeight[0]));
            gTo.add(Integer.parseInt(gFromToWeight[1]));
            gWeight.add(Integer.parseInt(gFromToWeight[2]));
        }

        int res = Result.kruskals(gNodes, gFrom, gTo, gWeight);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
