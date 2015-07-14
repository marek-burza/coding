package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/contains-duplicate-ii/
 */
public class LC219ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        Queue<Integer> ordered = new LinkedList<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            ordered.add(num);
            set.add(num);
            if (ordered.size() > k) {
                set.remove(ordered.poll());
            }
        }
        return false;
    }

    public static void main(String[] arguments) {
        LC219ContainsDuplicateII solution = new LC219ContainsDuplicateII();
        System.out.println(solution.containsNearbyDuplicate(new int[]{0, 5, 7}, 2));
        System.out.println(solution.containsNearbyDuplicate(new int[]{0, 5, 7, 5}, 2));
        System.out.println(solution.containsNearbyDuplicate(new int[]{0, 5, 7, 10, 5}, 2));
    }
}
