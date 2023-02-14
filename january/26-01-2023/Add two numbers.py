'''
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 

Example 1:


Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:

Input: l1 = [0], l2 = [0]
Output: [0]
Example 3:

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
 

Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.

'''

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        borrow = 0
        res = ListNode(0)
        head = res
        while l1!=None and l2!=None:
            res.val = (l1.val+l2.val+borrow)%10
            if l1.next!=None or l2.next!=None:
                res.next = ListNode()
                res = res.next
            borrow = (l1.val+l2.val+borrow)//10

            l1 = l1.next
            l2 = l2.next
        while l1!=None:
            #if l1 has more digits then l2
            res.val = (l1.val+borrow)%10
            if l1.next!=None:
                res.next = ListNode()
                res = res.next
                
            borrow = (l1.val+borrow)//10
            l1 = l1.next
        while l2!=None:
            #if l1 has more digits then l2
            res.val = (l2.val+borrow)%10
            if l2.next!=None:
                res.next = ListNode()
                res = res.next
            borrow = (l2.val+borrow)//10
            l2 = l2.next
        if borrow==1:
            res.next=ListNode(1)
        return head
