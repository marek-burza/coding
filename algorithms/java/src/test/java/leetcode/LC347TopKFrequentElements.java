package leetcode;

import org.junit.jupiter.api.Test;


import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 * #medium
 */
public class LC347TopKFrequentElements {
    public class Solution {
        private class FrequencyComparator implements Comparator<Integer> {
            private final HashMap<Integer, Integer> frequencies;

            public FrequencyComparator(HashMap<Integer, Integer> frequencies) {
                this.frequencies = frequencies;
            }

            public int compare(Integer k1, Integer k2) {
                Integer v1 = frequencies.get(k1);
                Integer v2 = frequencies.get(k2);
                if (v1 < v2) {
                    return 1;
                }
                if (Objects.equals(v1, v2)) {
                    return 0;
                }
                if (v1 > v2) {
                    return -1;
                }
                return 0;
            }

            public boolean equals(Object object) {
                return false;
            }
        }

        public List<Integer> topKFrequent(int[] nums, int k) {
            HashMap<Integer, Integer> frequencies = new HashMap<>();
            for (int value : nums) {
                if (frequencies.containsKey(value)) {
                    frequencies.put(value, frequencies.get(value) + 1);
                } else {
                    frequencies.put(value, 1);
                }
            }
            List<Integer> keys = new ArrayList<>(frequencies.keySet());
            Collections.sort(keys, new FrequencyComparator(frequencies));
            List<Integer> selected = new ArrayList<>();
            for (Integer value : keys) {
                if (selected.size() >= k) {
                    break;
                }
                selected.add(value);
            }
            return selected;
        }
    }

    @Test
    public void test_example() throws Exception {
        int[] nums = {1, 1, 1, 2, 2, 3};
        Integer[] expected = {1, 2};
        assertArrayEquals(expected, new Solution().topKFrequent(nums, 2).toArray());
    }
}
