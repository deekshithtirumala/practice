'''
We consider two strings to be anagrams of each other if the first string's letters can be rearranged to form the second string. In other words, both strings must contain the same exact letters in the same exact frequency. For example, bacdc and dcbac are anagrams, but bacdc and dcbad are not.

Alice is taking a cryptography class and finding anagrams to be very useful. She decides on an encryption scheme involving two large strings where encryption is dependent on the minimum number of character deletions required to make the two strings anagrams. Can you help her find this number?

Given two strings,  and , that may not be of the same length, determine the minimum number of character deletions required to make  and  anagrams. Any characters can be deleted from either of the strings.

Example.


The only characters that match are the 's so we have to remove  from  and  from  for a total of  deletions.

Function Description

Complete the makingAnagrams function in the editor below.

makingAnagrams has the following parameter(s):

string s1: a string
string s2: a string
Returns

int: the minimum number of deletions needed
Input Format

The first line contains a single string, .
The second line contains a single string, .

Constraints

It is guaranteed that  and  consist of lowercase English letters, ascii[a-z].
Sample Input

cde
abc
Sample Output

4
Explanation

Delete the following characters from our two strings to turn them into anagrams:

Remove d and e from cde to get c.
Remove a and b from abc to get c.
 characters have to be deleted to make both strings anagrams.
'''

#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'makingAnagrams' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. STRING s1
#  2. STRING s2
#

def makingAnagrams(s1, s2):
    # Write your code here
    if len(s1)>len(s2):
        s1,s2 = s2,s1
    for i in range(len(s1)):
        for j in range(len(s2)):
            if s1[i]==s2[j]:
                s1 = s1[:i]+'0'+s1[i+1:]
                s2 = s2[:j]+s2[j+1:]
                break
    count = 0
    for i in s1:
        if i!='0':
            count+=1
    count+=len(s2)
    
    return count


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s1 = input()

    s2 = input()

    result = makingAnagrams(s1, s2)

    fptr.write(str(result) + '\n')

    fptr.close()
