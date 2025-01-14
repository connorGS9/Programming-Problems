/*
 We can simplify the logic of the previous attempts for-switch-while loop structure using the Difference Array technique
 Create an int array of size s.length() + 1
 Go through each shift instruction set in the shifts array and if we are shifting forwards:
  increment difference[startIndex] and difference[endIndex + 1] by 1 .. Indicating we are altering everything in that range by 1
*/
class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int[] difference = new int[s.length() + 1]; //Difference array of size s + 1

        for (int[] shift : shifts) { //For every set of shift instructions in shifts
            if (shift[2] == 1) { //forwards shift
                difference[shift[0]] += 1; //The start of the shift range [start, end]
                difference[shift[1] + 1] -= 1; //Marks the end of the shift range (one index after end of range) -1
            } else { //backwards shift
                difference[shift[0]] -= 1; //Start of shift range
                difference[shift[1] + 1] += 1; //Marks end of shift range (one index after end of range) +1
            }
        }
        char[] res = s.toCharArray();
        int shift = 0; //Cummalitive shift variable

        for (int i = 0; i < s.length(); i++) {
            shift += difference[i];
            res[i] = (char)(((res[i] - 'a' + shift) % 26 + 26) % 26 + 'a'); //For safely warpping any a and z characters9
        }
        return new String(res);
    }
}

//EX: s = "abcd" , shifts = [ [0,2,1], [2,3,0] [0,2,1] ]
//*Build diff array of size s + 1 = 5
//Difference = [0,0,0,0,0]
//Run through all shifts and update difference array
//shift 1[2] = 1 (Fowards) update diff arr -> difference = [1,0,0,-1,0]
//shift 2[2] = 0 (Backwards) update diff arr -> difference = [1,0,-1,-1,1]
//shift 3[2] = 1 (Forwards) update diff arr -> difference = [2,0,-1,-2,1]

//now apply shift variable changes
//* i = 0 shift += 2  res[0] = a - a + 2 = C
//i = 1 shift += 0 res[1] = b - a + 2 = 3: D
//i = 2 shift += -1 res[2] = c - a + 1 = 3: D
//i = 3 shift += -2 res[3] = d - a - 1 = 2 : C
//abcd -> cddc