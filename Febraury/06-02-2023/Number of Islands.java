/*
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
*/

class Solution {
    List<List<List<Integer>>> lst = new ArrayList<>();
    HashMap<List<Integer>, Integer> map = new HashMap<>();
    List<Boolean> visited = new ArrayList<>();
    public void dfs(List current){
        int index = map.get(current);
        for(List node:lst.get(index)){
            int node_pos = map.get(node);
            if(!visited.get(node_pos)){
                visited.set(node_pos, true);
                dfs(node);
            }
        }
    }
    public boolean isValid(int row, int col, int sizer, int sizec){
        if(row<0 || col<0 || row>=sizer || col>=sizec)
            return false;
        return true;
    }
    public int numIslands(char[][] grid) {
        
        int count =0;
        List<Integer> offsetRow = Arrays.asList(0,-1,0,1);
        List<Integer> offsetCol = Arrays.asList(-1,0,1,0);
        int sizer = grid.length;
        int sizec = grid[0].length;
        for(int row=0;row<grid.length;row++){
            for(int col=0;col<grid[0].length;col++){
                //System.out.println(grid[row][col]);
                if(grid[row][col]=='1'){
                    lst.add(new ArrayList<>());
                    for(int i=0;i<4;i++){
                        int row_pos = row+offsetRow.get(i);
                        int col_pos = col+offsetCol.get(i);
                        if(isValid(row_pos, col_pos, sizer, sizec)){
                            if(grid[row_pos][col_pos]=='1')
                                lst.get(count).add(Arrays.asList(row_pos, col_pos));
                        }
                    }
                    //System.out.println("entered");
                    map.put(Arrays.asList(row, col),count);
                    count++;
                }
            }
        }
        //System.out.println(map);        
        //System.out.println(lst);

        //add false for every node
        for(int i=0;i<count;i++){
            visited.add(false);
        }
        
        int chains = 0;
        //System.out.println(map.size());
        for(List node: map.keySet()){
            int index = map.get(node);
            //System.out.println(visited.get(index));
            if(!visited.get(index)){
                visited.set(index, true);
                dfs(node);
                chains++;
            }
            
            //System.out.println(visited.get(index));
            //System.out.println(visited+" every");
        }
        return chains;
    }
}
