# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def countNodes(self, root: Optional[TreeNode]) -> int:
        if root==None:
            return 0
        
        nodes = 1
        #recurrsive case
        left = self.countNodes(root.left)
        right = self.countNodes(root.right)
        
        if left!=None:
            nodes+=left
        if right!=None:
            nodes+=right
        return nodes
