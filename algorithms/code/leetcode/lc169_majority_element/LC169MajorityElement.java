package leetcode.lc169_majority_element;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * https://leetcode.com/problems/majority-element/ #easy
 */
public final class LC169MajorityElement {
    public int majorityElement(final int[] nums) {
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
        for (Entry<Integer, Integer> entry : frequencies.entrySet()) {
            int value = entry.getKey();
            int other = entry.getValue();
            if (count <= other) {
                result = value;
                count = other;
            }
        }
        return result;
    }
}
