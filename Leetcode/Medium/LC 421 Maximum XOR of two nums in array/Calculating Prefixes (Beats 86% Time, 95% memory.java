/*
 In this efficient solution, we use a greedy approach to determine the maximum XOR of any two numbers in the array.
 The key idea is to process each bit from the most significant bit (MSB) to the least significant bit (LSB).
 At each step, we:
 1. Assume the current bit contributes to the maximum XOR.
 2. Use prefixes of numbers (up to the current bit) to determine if this assumption is feasible.
 If feasible, the current bit is included in the result; otherwise, it is discarded.
 This approach minimizes unnecessary computations and ensures an O(N * 32) complexity.
 */
class Solution {
    public int findMaximumXOR(int[] nums) {
        int maxXor = 0;

        for (int i = 31; i >= 0; i--) { //Starting at the MSB iterate down
            maxXor = (maxXor << 1); //Left shift maxXor by 1 to allow space for new bit it will be 0 for now
            int candidate = (maxXor | 1); //Candidate will become maxXor with an LSB of 1

            Set<Integer> prefixes = new HashSet<>(); //Our set of prefixes allows o(1) lookup and insertion
            for (int num : nums) { //For every num in nums array
                int prefix = (num >> i); //Right shift to take all bits other than i
                prefixes.add(prefix); //Add the prefix to our set
            }
            boolean possible = false; //Boolean to track if candidate is possible
            for (int prefix : prefixes) { //Iterate through all prefixes
                if (prefixes.contains(prefix ^ candidate)) { //if the prefix that is the XOR of current prefix and candidate is in prefixes set
                    possible = true; //Then the candidate is good
                    break; //Break instantly
                }
            }
            if (possible) { //If the candidate is possible the maxXor will be this candidate
                maxXor = candidate;
            } else { //Else maxXor is already the best option, and we release the Least Signfigant Bit (1) we added earlier on line 13
                maxXor = maxXor & ~1; //maxXor = maxXor without the least signifigant bit
            }
        }
        return maxXor; //Return maxXor
    }
}