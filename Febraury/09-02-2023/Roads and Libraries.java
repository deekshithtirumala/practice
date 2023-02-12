/*
Determine the minimum cost to provide library access to all citizens of HackerLand. There are  cities numbered from  to . Currently there are no libraries and the cities are not connected. Bidirectional roads may be built between any city pair listed in . A citizen has access to a library if:

Their city contains a library.
They can travel by road from their city to a city containing a library.
Example

The following figure is a sample map of HackerLand where the dotted lines denote possible roads:

image




The cost of building any road is , and the cost to build a library in any city is . Build  roads at a cost of  and  libraries for a cost of . One of the available roads in the cycle  is not necessary.

There are  queries, where each query consists of a map of HackerLand and value of  and . For each query, find the minimum cost to make libraries accessible to all the citizens.

Function Description

Complete the function roadsAndLibraries in the editor below.
roadsAndLibraries has the following parameters:

int n: integer, the number of cities
int c_lib: integer, the cost to build a library
int c_road: integer, the cost to repair a road
int cities[m][2]: each  contains two integers that represent cities that can be connected by a new road
Returns
- int: the minimal cost

Input Format

The first line contains a single integer , that denotes the number of queries.

The subsequent lines describe each query in the following format:
- The first line contains four space-separated integers that describe the respective values of , ,  and , the number of cities, number of roads, cost of a library and cost of a road.
- Each of the next  lines contains two space-separated integers,  and , that describe a bidirectional road that can be built to connect cities  and .

Constraints

Each road connects two distinct cities.
Sample Input

STDIN       Function
-----       --------
2           q = 2
3 3 2 1     n = 3, cities[] size m = 3, c_lib = 2, c_road = 1
1 2         cities = [[1, 2], [3, 1], [2, 3]]
3 1
2 3
6 6 2 5     n = 6, cities[] size m = 6, c_lib = 2, c_road = 5
1 3         cities = [[1, 3], [3, 4],...]
3 4
2 4
1 2
2 3
5 6
Sample Output

4
12
Explanation

Perform the following  queries:

HackerLand contains  cities and can be connected by  bidirectional roads. The price of building a library is  and the price for repairing a road is .
image
The cheapest way to make libraries accessible to all is to:

Build a library in city  at a cost of .
Build the road between cities  and  at a cost of .
Build the road between cities  and  at a cost of .
This gives a total cost of . Note that the road between cities  and  does not need to be built because each is connected to city .

In this scenario it is optimal to build a library in each city because the cost to build a library is less than the cost to build a road.
image
*/

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'roadsAndLibraries' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER c_lib
     *  3. INTEGER c_road
     *  4. 2D_INTEGER_ARRAY cities
     */
    
    static List<List<Integer>> adj;
    static boolean[] visited;
    public static int dfs(int curr){
        
        //not visited node comes to this method
        int n_cities = 1;
        visited[curr]=true; //changing the visit state
        
        //calling all sub node and incrementing roads count.
        
        for(int node: adj.get(curr)){
            //add road only when node not visited
            if(!visited[node]){
                n_cities+= dfs(node);
            }
        }
        return n_cities;
    }
    public static long roadsAndLibraries(int n, int m,int c_lib, int c_road, List<List<Integer>> cities) {
        // n - vertices, m - no, of edges, cities -> edges links list, c_lid-> cost of library, c_road -> cost of road
        
        //initializing the static variables.
        
        
        adj = new ArrayList<>(); //list of adjacent elements. 
        visited = new boolean [n]; //default is false.
        //Note : indexing of list starts from 0 where as node name starts from 1
        
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        
        for(List pair: cities){
            int node = (int)pair.get(0)-1;
            int node_e = (int)pair.get(1)-1;
            adj.get(node).add(node_e); //as bidirectionally linked
            adj.get(node_e).add(node);
        }
        
        long total =0;
        
        for(int base=0;base<n;base++){
            if(!visited[base]){
                int n_cities = dfs(base);
                long cost_min_lib = c_road*(n_cities-1)+c_lib; //one library and no of cities -1 =  raods
                long cost_all_lib = c_lib*n_cities;
                total+= Math.min(cost_min_lib, cost_all_lib);
            }
        }
        
        
        
        return total;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                int c_lib = Integer.parseInt(firstMultipleInput[2]);

                int c_road = Integer.parseInt(firstMultipleInput[3]);

                List<List<Integer>> cities = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        cities.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                long result = Result.roadsAndLibraries(n, m, c_lib, c_road, cities);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
