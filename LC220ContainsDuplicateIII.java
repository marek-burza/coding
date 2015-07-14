package leetcode;

import java.util.LinkedList;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/contains-duplicate-iii/
 */
public class LC220ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        NavigableSet<Long> sorted = new TreeSet<>();
        Queue<Long> ordered = new LinkedList<>();
        for (int num : nums) {
            Long ceiling = sorted.ceiling((long) num);
            Long floor = sorted.floor((long) num);
            if ((ceiling != null && ceiling - num <= t) || (floor != null && num - floor <= t)) {
                return true;
            }
            ordered.add((long) num);
            sorted.add((long) num);
            if (ordered.size() > k) {
                sorted.remove(ordered.poll());
            }
        }
        return false;
    }

    public static void main(String[] arguments) {
        LC220ContainsDuplicateIII solution = new LC220ContainsDuplicateIII();
        int[] nums1 = {1, 10, 20, 2};
        System.out.println(solution.containsNearbyAlmostDuplicate(nums1, 3, 2));
        int[] nums2 = {1, 10, 20, 4};
        System.out.println(solution.containsNearbyAlmostDuplicate(nums2, 3, 2));
        int[] nums3 = {1, 10, 20, 30, 2};
        System.out.println(solution.containsNearbyAlmostDuplicate(nums3, 3, 2));
    }
}
