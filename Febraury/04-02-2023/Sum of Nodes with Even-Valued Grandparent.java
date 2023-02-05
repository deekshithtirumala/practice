/*
Given the root of a binary tree, return the sum of values of nodes with an even-valued grandparent. If there are no nodes with an even-valued grandparent, return 0.

A grandparent of a node is the parent of its parent if it exists.

 

Example 1:


Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 18
Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.
Example 2:


Input: root = [1]
Output: 0
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
1 <= Node.val <= 100

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    static int total =0;
    public void postOrder(int pre, TreeNode root){
        if (root==null){
            return;
        }
        
        //post order recurssive call
        postOrder(root.val, root.left);
        postOrder(root.val, root.right);
        //System.out.println(pre+" "+root.val+" "+total);

        if(pre%2==0){
            if(root.left!=null){
                total+=root.left.val;
            }
            if(root.right!=null){
                total+=root.right.val;
            }
        }
        
    }
    public int sumEvenGrandparent(TreeNode root) {
        total = 0;
        postOrder(1,root);
        return total;
    }
}
