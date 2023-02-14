/*
Given two binary strings a and b, return their sum as a binary string.

 

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
 

Constraints:

1 <= a.length, b.length <= 104
a and b consist only of '0' or '1' characters.
Each string does not contain leading zeros except for the zero itself.
*/

class Solution {
    public String addBinary(String a, String b) {
        int carry = 0;
        int aLength = a.length()-1;
        int bLength = b.length()-1;
        String result = ""; //or you can create a string builder and add at beginning.
        while(aLength>=0 || bLength>=0){
            int sum = carry;
            if(aLength>=0){
                //a's operation
                sum+=a.charAt(aLength--)-'0';// as '1' ascii is 49 and '0'-> 48 (49-48=1)
            }
            if(bLength>=0){
                //b's operation
                sum+=b.charAt(bLength--)-'0';
            }
            result = Integer.toString(sum%2)+result;
            
            carry = sum/2;
        }
        if (carry>0){
            result = "1"+result;
        }
        return result;
    }
}
