'''
question image link:- https://s3.amazonaws.com/hr-assets/0/1626329575-c1d3c60dbc-superiorarrayelement.jpg

Input Format

single line with all array elements

Constraints

N<=10^6

Output Format

number of superior array elements

Sample Input 0

7 9 5 2 8 7
Sample Output 0

3
Explanation 0

Here 9 8 and 7 are superior elements.


'''
lst = list(map(int,input().split()))

count = 1

max_indx = len(lst)-1- lst[::-1].index(max(lst))
for i in range(len(lst)-1):
    
    if i>=max_indx and lst[i]>max(lst[i+1:]):
        count+=1

print(count)
