package leetcode.lc077_combinations;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combinations/
 * #medium
 */
public final class LC077Combinations {
    public void combine(final int m, final int n, final int k, final List<Integer> prefix,
            final List<List<Integer>> found) {
        for (int i = m; i <= n - (k - 1) + prefix.size(); i++) {
            prefix.add(i);
            if (prefix.size() == k) {
                found.add(new ArrayList<>(prefix));
            } else {
                combine(i + 1, n, k, prefix, found);
            }
            prefix.remove(prefix.size() - 1);
        }
    }

    public List<List<Integer>> combine(final int n, final int k) {
        List<List<Integer>> found = new ArrayList<>();
        combine(1, n, k, new ArrayList<>(), found);
        return found;
    }
}
package leetcode.lc077_combinations;

import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC077CombinationsTests {
    private static class IntegerListComparator implements Comparator<List<Integer>>, Serializable {
        @Override
        public int compare(final List<Integer> l1, final List<Integer> l2) {
            if (l1.size() < l2.size()) {
                return -1;
            }
            if (l1.size() > l2.size()) {
                return 1;
            }
            for (int i = 0; i < l1.size(); i++) {
                if (l1.get(i) < l2.get(i)) {
                    return -1;
                }
                if (l1.get(i) > l2.get(i)) {
                    return 1;
                }
            }
            return 0;
        }
    }

    @Test
    public void testExample() throws Exception {
        int[][] expected = { { 1, 2 }, { 1, 3 }, { 1, 4 }, { 2, 3 }, { 2, 4 }, { 3, 4 } };
        List<List<Integer>> result = new LC077Combinations().combine(4, 2);
        Collections.sort(result, new IntegerListComparator());
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
fn combine(m: i32, n: i32, k: i32, prefix: &mut Vec<i32>, found: &mut Vec<Vec<i32>>) {
    for i in m..=n - (k - 1) + prefix.len() {
        prefix.push(i);
        if prefix.len() == k {
            found.push(prefix.clone());
        } else {
            combine(i + 1, n, k, prefix, found);
        }
        prefix.pop();
    }
}

fn combine_test(n: i32, k: i32) -> Vec<Vec<i32>> {
    let mut found = Vec::new();
    combine(1, n as i32, k, &mut Vec::new(), &mut found);
    found
}

#[test]
fn test_example() {
    let expected = vec![
        vec![1, 2],
        vec![1, 3],
        vec![1, 4],
        vec![2, 3],
        vec![2, 4],
        vec![3, 4],
    ];
    let result = combine_test(4, 2);
    assert_eq!(expected.len(), result.len());
    for (i, expected) in expected.iter().enumerate() {
        assert_eq!(expected.len(), result[i].len());
        for (j, element) in expected.iter().enumerate() {
            assert_eq!(*element, result[i][j] as i32);
        }
    }
}
```