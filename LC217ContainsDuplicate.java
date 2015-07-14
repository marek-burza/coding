package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/contains-duplicate/
 */
public class LC217ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }

    public static void main(String[] arguments) {
        LC217ContainsDuplicate solution = new LC217ContainsDuplicate();
        System.out.println(solution.containsDuplicate(new int[]{0, 5, 7}));
        System.out.println(solution.containsDuplicate(new int[]{0, 5, 7, 5, 10}));
    }
}
