package leetcode.lc078_subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/
 * #medium
 */
public final class LC078Subsets {
    public void subsets(final int[] nums, final int offset, final List<Integer> current,
            final List<List<Integer>> list) {
        list.add(new ArrayList<>(current));
        for (int i = offset; i < nums.length; i++) {
            current.add(nums[i]);
            subsets(nums, i + 1, current, list);
            current.remove(current.size() - 1);
        }
    }

    public List<List<Integer>> subsets(final int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        subsets(nums, 0, new ArrayList<Integer>(), list);
        return list;
    }
}
package leetcode.lc078_subsets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class LC078SubsetsTests {
    private static class OrderlyComparator implements Comparator<List<Integer>>, Serializable {
        @Override
        public int compare(final List<Integer> l1, final List<Integer> l2) {
            int difference = l1.size() - l2.size();
            if (0 != difference) {
                return difference;
            } else {
                for (int i = 0; i < l1.size(); i++) {
                    difference = l1.get(i).compareTo(l2.get(i));
                    if (0 != difference) {
                        return difference;
                    }
                }
                return 0;
            }
        }
    }

    public void test(final int[][] expected, final List<List<Integer>> result) throws Exception {
        Collections.sort(result, new OrderlyComparator());
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, result.get(i).size());
            Collections.sort(result.get(i));
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], result.get(i).get(j).intValue());
            }
        }
    }

    @Test
    public void test123() throws Exception {
        List<List<Integer>> list = new LC078Subsets().subsets(new int[] { 1, 2, 3 });
        int[][] expected = {
                {},
                { 1 }, { 2 }, { 3 },
                { 1, 2 }, { 1, 3 }, { 2, 3 },
                { 1, 2, 3 }
        };
        test(expected, list);
    }
}
```rust
pub fn subsets(nums: Vec<i32>, offset: usize, current: &mut Vec<i32>, result: &mut Vec<Vec<i32>>) {
    result.push(current.clone());
    for i in offset..nums.len() {
        current.push(nums[i]);
        subsets(nums, i + 1, current, result);
        current.pop();
    }
}

pub fn subsets(nums: Vec<i32>) -> Vec<Vec<i32>> {
    nums.sort_unstable();
    let mut result = Vec::new();
    subsets(nums, 0, &mut Vec::new(), &mut result);
    result
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test123() {
        let list = subsets(vec![1, 2, 3]);
        let expected = vec![
            vec![], 
            vec![1], 
            vec![2], 
            vec![3],
            vec![1, 2], 
            vec![1, 3],
            vec![2, 3],
            vec![1, 2, 3]
        ];
        assert_eq!(list.len(), expected.len());
        for i in 0..expected.len() {
            let mut sorted_list = list[i].clone();
            sorted_list.sort_unstable();
            let mut sorted_expected = expected[i].clone();
            sorted_expected.sort_unstable();
            assert_eq!(sorted_list, sorted_expected);
        }
    }
}
```