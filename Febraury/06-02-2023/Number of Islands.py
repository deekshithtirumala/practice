"""
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
"""

class Solution:
    def dfs(self, row, col):
        if row==-1 or col==-1 or row==len(self.grid) or col==len(self.grid[0]):
            return 
        if self.grid[row][col]!='1':
            return 
        self.grid[row][col]="v"
        self.dfs(row,col-1)
        self.dfs(row-1,col)
        self.dfs(row,col+1)
        self.dfs(row+1,col)
        
    def numIslands(self, grid: List[List[str]]) -> int:
        self.grid = grid
        count = 0
        for row in range(len(grid)):
            for col in range(len(grid[0])):
                if grid[row][col]=="1":
                    self.dfs(row, col)
                    count+=1
                    
        return count
                    
