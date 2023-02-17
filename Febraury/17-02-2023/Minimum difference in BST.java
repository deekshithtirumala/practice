/*
Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.

 

Example 1:


Input: root = [4,2,6,1,3]
Output: 1
Example 2:


Input: root = [1,0,48,null,null,12,49]
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [2, 100].
0 <= Node.val <= 105
 
*/


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
    public int minDiffInBST(TreeNode root) {
      //base case
        if (root==null)
        return 0;
        
        Stack<TreeNode> stack = new Stack<>();
        int pre_val = -1;
        int min_val = -1;
        //adding all left nodes
        while(root!=null){
            stack.push(root);
            root = root.left;
        }
        
        while (!stack.isEmpty()){
            int size = stack.size();
            
            for(int i= 0;i<size;i++){
                TreeNode curr = stack.pop();
                TreeNode right = curr.right;
                while(right!=null){
                    stack.push(right);
                    right = right.left; //moving to left most.
                }
                if (pre_val!=-1){
                    int diff = curr.val-pre_val;
                    if (min_val!=-1){
                        min_val = Math.min(min_val, diff);
                    }
                    else{
                        min_val = diff;
                    }
                }
                pre_val = curr.val;
                
            }
        }
        return min_val;  
    }
}
