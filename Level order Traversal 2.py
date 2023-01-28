'''
leetcode - 107
Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[15,7],[9,20],[3]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-1000 <= Node.val <= 1000
'''

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Node:
    def __init__(self,val, next_=None):
        self.val = val
        self.next = next_

class LinkedList:
    def __init__(self, data):
        self.head = Node(data)
    def pop(self):
        temp = self.head
        if self.head!=None:
            self.head = self.head.next
        else:
            raise ('no node present')
        return temp
    def add(self, data):
        temp = self.head
        if self.head==None:
            self.head = Node(data)
            return
        while temp.next!=None:
            temp = temp.next
        temp.next = Node(data)
    
    def size(self):
        count = 0
        temp = self.head
        while temp!=None:
            count+=1
            temp = temp.next
        return count
    def isEmpty(self):
        if self.head==None:
            return True
        return False
    def radd(self, data):
        #add the data before the head
        temp = Node(data)
        temp.next = self.head
        self.head = temp
    def list(self):
        #returns list of linkedlist
        if self.head==None:
            return []
        lst =[]
        temp = self.head
        while temp!=None:
            lst.append(temp.val)
            temp = temp.next
        return lst
class Solution:
    def levelOrderBottom(self, root: Optional[TreeNode]) -> List[List[int]]:
        if root==None:
            return []
        res = LinkedList([root.val])
        queue = LinkedList(root)
        #queue.add(root)
        while not queue.isEmpty():
            size = queue.size()
            lst = []
            for i in range(size):
                curr = queue.pop().val
                if curr.left!=None:
                    queue.add(curr.left)
                    lst.append(curr.left.val)
                if curr.right!=None:
                    queue.add(curr.right)
                    lst.append(curr.right.val)
            if len(lst)!=0:
                res.radd(lst)
        res_lst = []
        
        
        return res.list()
