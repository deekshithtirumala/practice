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
