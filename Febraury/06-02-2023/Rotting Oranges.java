/*
You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

 

Example 1:


Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.

*/

class Solution {
    public boolean isValid(int row, int col, int sizer, int sizec){
        if(row<0 || col<0 || row>=sizer ||col>=sizec)
            return false;
        return true;
    }
    public int orangesRotting(int[][] grid) {
        LinkedList<List> queue = new LinkedList<>();
        
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if (grid[i][j]==2){
                    
                    queue.add(Arrays.asList(i,j));
                }
            }
        }
        //System.out.println(queue.poll());
        int sizer = grid.length;
        int sizec = grid[0].length;
        int time = 0;
        List<Integer> offsetRow= Arrays.asList(0,-1,0,1);
        List<Integer> offsetColumn= Arrays.asList(-1,0,1,0);
        while(!queue.isEmpty()){
            int queueSize = queue.size();
            for(int num=0;num<queueSize;num++){
                //System.out.println(queue.isEmpty());
                List<Integer> currentPosition = queue.poll();
                //System.out.println(currentPosition);
                for(int i=0;i<4;i++){
                    int row_pos = offsetRow.get(i)+currentPosition.get(0);
                    int col_pos = offsetColumn.get(i)+currentPosition.get(1);
                    if(isValid(row_pos,col_pos,sizer,sizec)){
                        if(grid[row_pos][col_pos]==1){
                            queue.add(Arrays.asList(row_pos,col_pos));
                            grid[row_pos][col_pos]=2;
                        }
                    }
                }
            }            
            time+=1;

            //System.out.println(queueSize+" = "+time);
        }
        
        for(int i=0;i<sizer;i++){
            for(int j=0;j<sizec;j++){
                if(grid[i][j]==1)
                    return -1;
            }
        }
        if (time==0){
            return 0;
        }
        return time-1;
    }
}
