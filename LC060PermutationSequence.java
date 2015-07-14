package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutation-sequence/
 */
public class LC060PermutationSequence {
    public String getPermutation(int n, int k) {
        if (n < 0 || k < 0) {
            return null;
        }
        StringBuffer result = new StringBuffer();
        List<Integer> remaining = new ArrayList<>();
        List<Integer> factorials = new ArrayList<>();
        factorials.add(0);
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
            factorials.add(factorial);
            remaining.add(i);
        }
        for (int i = 1; i < n; i++) {
            int block = factorials.get(n - i);
            int index = (k - 1) / block;
            result.append(remaining.remove(index));
            k -= index * block;
        }
        result.append(remaining.remove(0));
        return result.toString();
    }

    public static void main(String[] arguments) {
        LC060PermutationSequence solution = new LC060PermutationSequence();
        //System.out.println(solution.getPermutation(2, 1));
        System.out.println(solution.getPermutation(3, 2));
    }
}
