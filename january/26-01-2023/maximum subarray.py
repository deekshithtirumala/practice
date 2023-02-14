'''
Given an integer array, find the maximum sum contiguous subarray. If the array contains all negative numbers, then the maximum subarray contains only the negative numbers with the least value.

Input Format

input1: N, the number of elements in an array input2: Array of numbers between -1000 <= input2[i] <= 1000.

Constraints

_

Output Format

Return the maximum sum of contiguous elements.

Sample Input 0

3
3 -1 2
Sample Output 0

4
'''
n = int(input())
lst = list(map(int, input().split()))

maxi = lst[0]

for i in range(len(lst)-1):
    for j in range(i+1,len(lst)):
        tot = sum(lst[i:j+1])
        #print(tot,'i value',i,'j value',j)
        if tot>maxi:
            maxi = tot
        #print(maxi)
print(maxi)
