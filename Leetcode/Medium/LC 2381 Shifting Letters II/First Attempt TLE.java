/*
 In this first attempt I attempted to brute force the shifting algorithim by using a switch case
 The switch case will tell us whether we are going to be shifting backwards or forwards based on the value of the char: direction
 Once we know the direction, I decided to hardcode the wrapping of z -> a for forwards shifting and a -> z for backwards shifting
 For all chars that are not a or z, we can use ASCII arithmetic to change a letter by one place in the english alphabet by
 adding or subtracting 1 turning the char into an int and casting it back to char will give us the shifted character
 we modify the String s in place by changing it to a char array and altering the values of the array since strings are immutable in Java
 once complete we can finally return the array as a string by creating a new String(charArray).
 This solution pased 38/39 test cases by fails massive test cases due to the countless iterations needed to covert a range such as
 the max length in the constraints (5 * 10^4) being the length of s AND the number of shift rows.
 Not only can the number of shift be extremely large but every single shift COULD cover the entire distance of the array
 Making the needed number of iterations in the for-switch-while combination loop: O(n * k) where n is the length of the string and k is the # of shifts
 In the worst case of s.length() and shifts[][] = (5 * 10^4) we will end up needing (2.5 * 10^9) iterations
*/

class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        char direction = '#';
        char[] sToChar = s.toCharArray();
        for (int[] shift : shifts) {
            int start = shift[0]; //start index
            int end = shift[1]; //end index

            if (shift[2] == 0)  {
                direction = 'b'; //backwards shift
            } else {
                direction = 'f'; //forward shift
            }
            switch (direction) {
                case 'b':
                    while(start <= end) {
                        if (sToChar[start] == 'a') { //if a change to z directly
                            sToChar[start] = 'z';
                        } else {
                            sToChar[start] = (char)(sToChar[start] - 1); //anything other than a just subtract 1 to char
                        }
                        start++;
                    }
                    break;

                case 'f':
                    while(start <= end) {
                        if (sToChar[start] == 'z') { //if z change to a directly
                            sToChar[start] = 'a';
                        } else {
                            sToChar[start] = (char)(sToChar[start] + 1); //anything other than z just add 1 to char
                        }
                        start++;
                    }
                    break;
            }
        }
        return new String(sToChar);
    }
}