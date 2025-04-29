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
package leetcode.lc347_top_k_frequent_elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC347TopKFrequentElementsTests {
    @Test
    public void testExample() throws Exception {
        int[] nums = { 1, 1, 1, 2, 2, 3 };
        int[] expected = { 1, 2 };
        assertArrayEquals(expected, new LC347TopKFrequentElements().topKFrequent(nums, 2));
    }
}
```rust
use std::collections::{HashMap, BinaryHeap};

struct FrequencyComparator {
    frequencies: HashMap<i32, i32>,
}

impl FrequencyComparator {
    fn new(frequencies: &HashMap<i32, i32>) -> Self {
        Self { frequencies: frequencies.clone() }
    }

    fn compare(&self, k1: i32, k2: i32) -> std::cmp::Ordering {
        if self.frequencies.get(&k1).unwrap_or(&0) < self.frequencies.get(&k2).unwrap_or(&0) {
            std::cmp::Ordering::Less
        } else if self.frequencies.get(&k1).unwrap_or(&0) > self.frequencies.get(&k2).unwrap_or(&0) {
            std::cmp::Ordering::Greater
        } else {
            std::cmp::Ordering::Equal
        }
    }
}

pub fn top_k_frequent(nums: Vec<i32>, k: i32) -> Vec<i32> {
    let mut frequencies = HashMap::new();
    for num in nums {
        *frequencies.entry(num).or_insert(0) += 1;
    }

    let mut selected = BinaryHeap::new();
    for (k, v) in frequencies {
        selected.push((v, k));
    }

    let mut result = Vec::with_capacity(k as usize);
    while !selected.is_empty() && result.len() < k {
        let (_, key) = selected.pop().unwrap();
        result.push(key);
    }
    result
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let nums = vec![1, 1, 1, 2, 2, 3];
        let expected = vec![1, 2];
        assert_eq!(top_k_frequent(nums, 2), expected);
    }
}
```