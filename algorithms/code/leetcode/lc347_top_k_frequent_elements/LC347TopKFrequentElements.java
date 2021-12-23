package leetcode.lc347_top_k_frequent_elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 * #medium
 */
public final class LC347TopKFrequentElements {
    private static class FrequencyComparator implements Comparator<Integer>, Serializable {
        private final HashMap<Integer, Integer> frequencies;

        FrequencyComparator(final HashMap<Integer, Integer> frequenciesValue) {
            frequencies = frequenciesValue;
        }

        public int compare(final Integer k1, final Integer k2) {
            Integer v1 = frequencies.get(k1);
            Integer v2 = frequencies.get(k2);
            return v2 - v1;
        }
    }

    public int[] topKFrequent(final int[] nums, final int k) {
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
        for (int i = 0;; i++) {
            if (selected.size() >= k) {
                break;
            }
            selected.add(keys.get(i));
        }
        return selected.stream().mapToInt(x -> x).toArray();
    }
}
