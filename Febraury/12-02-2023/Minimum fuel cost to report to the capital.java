/*
There is a tree (i.e., a connected, undirected graph with no cycles) structure country network consisting of n cities numbered from 0 to n - 1 and exactly n - 1 roads. The capital city is city 0. You are given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional road connecting cities ai and bi.

There is a meeting for the representatives of each city. The meeting is in the capital city.

There is a car in each city. You are given an integer seats that indicates the number of seats in each car.

A representative can use the car in their city to travel or change the car and ride with another representative. The cost of traveling between two cities is one liter of fuel.

Return the minimum number of liters of fuel to reach the capital city.

 

Example 1:


Input: roads = [[0,1],[0,2],[0,3]], seats = 5
Output: 3
Explanation: 
- Representative1 goes directly to the capital with 1 liter of fuel.
- Representative2 goes directly to the capital with 1 liter of fuel.
- Representative3 goes directly to the capital with 1 liter of fuel.
It costs 3 liters of fuel at minimum. 
It can be proven that 3 is the minimum number of liters of fuel needed.
Example 2:


Input: roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
Output: 7
Explanation: 
- Representative2 goes directly to city 3 with 1 liter of fuel.
- Representative2 and representative3 go together to city 1 with 1 liter of fuel.
- Representative2 and representative3 go together to the capital with 1 liter of fuel.
- Representative1 goes directly to the capital with 1 liter of fuel.
- Representative5 goes directly to the capital with 1 liter of fuel.
- Representative6 goes directly to city 4 with 1 liter of fuel.
- Representative4 and representative6 go together to the capital with 1 liter of fuel.
It costs 7 liters of fuel at minimum. 
It can be proven that 7 is the minimum number of liters of fuel needed.
Example 3:


Input: roads = [], seats = 1
Output: 0
Explanation: No representatives need to travel to the capital city.
 

Constraints:

1 <= n <= 105
roads.length == n - 1
roads[i].length == 2
0 <= ai, bi < n
ai != bi
roads represents a valid tree.
1 <= seats <= 105
*/

class Solution {
    long min_fuel;
    public int dfs(int source, boolean[] visited, List<List<Integer>> adjacency, int seats){
        //setting source to visited
        visited[source] = true;
        int no_of_persons = 1;
        
        for(int node: adjacency.get(source)){
            if(!visited[node]){
                no_of_persons+=dfs(node, visited, adjacency, seats);
            }
        }

        this.min_fuel += Math.ceil((double)no_of_persons/seats);
        
        System.out.println(source+""+" persons"+ no_of_persons+" fuel "+min_fuel);

        return no_of_persons;
    }
    public long minimumFuelCost(int[][] roads, int seats) {
        //dfs best suits the question
        List<List<Integer>> adjacency  = new ArrayList<>();
        this.min_fuel = 0;
        //adding empty list to the adjacency list
        for(int i=0;i<roads.length+1;i++){
            adjacency.add(new ArrayList<>());
        }
        //adding the roads to adjacency list
        for(int[] pair: roads){
            int u = pair[0];
            int v = pair[1];
            adjacency.get(u).add(v); //as they are bidirectional roads
            adjacency.get(v).add(u);
        }
        
        //creating unvisited list
        boolean[] visited = new boolean[roads.length+1];
        visited[0] = true;
        for(int node: adjacency.get(0)){
            dfs(node, visited, adjacency, seats);
        }
        return this.min_fuel;
    }
}
