class Node:
    def __init__(self,val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class LinkedList:
    def __init__(self, data=0):
        self.head = Node(data)
    def pop(self):
        temp = self.head
        if self.head!=None:
            self.head = self.head.next
        else:
            raise ('no node present')
        return temp
    
