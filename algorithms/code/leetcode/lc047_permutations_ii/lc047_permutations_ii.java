package leetcode.lc047_permutations_ii;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/permutations-ii/ #medium
 */
public final class LC047PermutationsII {
    public List<List<Integer>> permuteUnique(final int[] nums) {
        // Count each number
        Map<Integer, Integer> counted = new TreeMap<>();
        for (int value : nums) {
            int count = 1;
            if (counted.containsKey(value)) {
                count += counted.get(value);
            }
            counted.put(value, count);
        }
        // Generate the permutations
        List<List<Integer>> permutations = new LinkedList<>();
        generate(new ArrayList<>(nums.length), nums.length, counted, permutations);
        return permutations;
    }

    private void generate(final List<Integer> permutation, final int limit, final Map<Integer, Integer> counted,
            final List<List<Integer>> permutations) {
        if (permutation.size() == limit) {
            permutations.add(new ArrayList<>(permutation));
            return;
        }
        for (Map.Entry<Integer, Integer> entry : counted.entrySet()) {
            int key = entry.getKey();
            int count = entry.getValue();
            if (count != 0) {
                int size = permutation.size();
                permutation.add(key);
                counted.put(key, count - 1);
                generate(permutation, limit, counted, permutations);
                counted.put(key, count);
                permutation.remove(size);
            }
        }
    }
}
package leetcode.lc047_permutations_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations-ii/ #medium
 */
public final class LC047PermutationsIITests {
    private static class DeepComparator implements Comparator<List<Integer>>, Serializable {
        @Override
        public int compare(final List<Integer> list1, final List<Integer> list2) {
            if (list1.size() < list2.size()) {
                return -1;
            }
            if (list1.size() > list2.size()) {
                return 1;
            }
            for (int i = 0, length = list1.size(); i < length; i++) {
                if (list1.get(i) < list2.get(i)) {
                    return -1;
                }
                if (list1.get(i) > list2.get(i)) {
                    return 1;
                }
            }
            return 0;
        }
    }

    @Test
    public void testExample() throws Exception {
        int[] nums = { 1, 1, 2 };
        List<List<Integer>> result = new LC047PermutationsII().permuteUnique(nums);
        Collections.sort(result, new DeepComparator());
        int[][] expected = { { 1, 1, 2 }, { 1, 2, 1 }, { 2, 1, 1 } };
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, result.get(i).size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], result.get(i).get(j).intValue());
            }
        }
    }
}
```rust
use std::collections::{HashMap, HashSet};
use std::iter::repeat;

pub struct LC047PermutationsII {
    /// Counts each number in the array
    counted: HashMap<i32, i32>,
}

impl LC047PermutationsII {
    pub fn permute_unique(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut counting = HashMap::new();
        for &num in nums.iter() {
            *counting.entry(num).or_insert(0) += 1;
        }

        let mut permutations = vec![];
        generate_permutations(&mut counting, &nums, &mut permutations);
        permutations
    }
}

fn generate_permutations(counting: &mut HashMap<i32, i32>, nums: &[i32], permutations: &mut Vec<Vec<i32>>) {
    for permutation in generate_all(nums) {
        if check_permutation(permutation, counting) {
            let clone = permutation.clone();
            permutations.push(clone);
        }
    }
}

fn generate_all(nums: &[i32]) -> HashSet<Vec<i32>> {
    let mut current = Vec::new();
    let mut combinations = HashSet::new();

    for num in nums {
        if !current.is_empty() && *num == current[0] {
            continue;
        }

        let new_combinations = generate_all(&[*num + &current]);

        for combination in new_combinations {
            let clone = combination.clone();
            current.append(&mut clone);
            combinations.insert(current.clone());
            current.pop().unwrap();
        }
    }

    combinations
}

fn check_permutation(permutation: &[i32], counting: &HashMap<i32, i32>) -> bool {
    for num in permutation.iter() {
        if *counting.get(num).unwrap_or(&0) == 0 {
            return false;
        }
        *counting.entry(*num).or_insert(0) -= 1;

        let mut clone = Vec::new();
        let mut i = 0;
        while i < permutation.len() && *permutation[i] != *num {
            i += 1;
        }

        if i == permutation.len() - 1 || *permutation[i + 1] != *num {
            return false;
        }
    }

    true
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_permutations_ii() {
        let nums = vec![1, 1, 2];
        let result = LC047PermutationsII::permute_unique(nums);
        assert_eq!(result.len(), 3);
        for i in 0..result.len() {
            println!("{:?}", result[i]);
        }
    }
}
```