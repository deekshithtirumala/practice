'''
Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

 

Example 1:


Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: preorder = [-1], inorder = [-1]
Output: [-1]
 

Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.
'''

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def constructTree(self, preOrder, inOrder, p_start, p_end, i_start, i_end, dic, curr):
        if p_start>p_end or len(preOrder)==0 or len(inOrder):
            return None
        
        root = TreeNode(preOrder[curr])
        
        
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        if (len(preorder)==0 or len(inorder)==0):
            return None
        root = TreeNode(preorder[0])
        if (len(preorder)==1 or len(inorder)==1):
            return root
        elif len(preorder)==2:
            if preorder[0]!=inorder[0]:
                root.left = TreeNode(preorder[1])
            else:
                root.right = TreeNode(preorder[1])
            return root
        index = inorder.index(preorder[0])
        left_in = inorder[:index]
        right_in = inorder[index+1:]
        
        left_pre = preorder[1:len(left_in)+1]
        right_pre = preorder[len(left_in)+1:len(left_in)+len(right_in)+1]
        
        
        root.left = self.buildTree(left_pre, left_in)
        root.right = self.buildTree(right_pre,right_in)
        return root
