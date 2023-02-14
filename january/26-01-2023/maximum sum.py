'''
Write a program that adds up the largest row sum and the largest column sum from an N-rows * M-columns array of elements.

As a preliminary pharse, you should reformat the sequence of numbers as a matrix, whose number of rows and columns are to be specified as arguments.

Input Format

input1: Integer for row dimension of the array. input2: Integer for column dimension of the array. input3: Array elements to be entered in row major

Constraints

-

Output Format

return Largest row sum + Largest column sum

Sample Input 0

2
2
1 2 5 6
Sample Output 0

19
Explanation 0

In 2 * 2 Matrix 
1   2
5   6 
row Sum = [1 + 2] & [5 + 6] = 3 & 11 (11 is big)
column sum = [1 + 5] & [2 + 6]= 6 & 8 (8 is big)

So of largest column sum + largest row sum = 11 + 8 = 19
'''

row = int(input())
col = int(input())

lst = list(map(int,input().split()))

arr = []

for i in range(len(lst)//col):
    arr.append(lst[i*col:i*col+col])

#print(arr)
maxi_r = sum(arr[0])
for j in range(1,row):
    tot = sum(arr[j])
    if maxi_r<tot:
        maxi_r = tot

maxi_c = sum(arr[i][0] for i in range(row))
for j in range(1,col):
    tot = sum(arr[i][j] for i in range(row))
    if maxi_c<tot:
        maxi_c = tot
print(maxi_r+maxi_c)
