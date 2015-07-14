package leetcode;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/majority-element/
 */
public class LC169MajorityElement {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> frequencies = new HashMap<>();
        for (int value : nums) {
            Integer count = frequencies.get(value);
            if (count == null) {
                count = 0;
            }
            frequencies.put(value, count + 1);
        }
        int result = Integer.MIN_VALUE;
        int count = Integer.MIN_VALUE;
        for (int value : frequencies.keySet()) {
            int other = frequencies.get(value);
            if (count <= other) {
                result = value;
                count = other;
            }
        }
        return result;
    }

    public static void main(String[] arguments) {
        int[] nums = {1, 2, 3, 1, 5, 1, 6, 1};
        System.out.println(new LC169MajorityElement().majorityElement(nums));
    }
}
