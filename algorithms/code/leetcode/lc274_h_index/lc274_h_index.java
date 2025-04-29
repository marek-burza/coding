package leetcode.lc274_h_index;

/**
 * https://leetcode.com/problems/h-index/
 * #medium
 */
public final class LC274HIndex {
    public int hIndex(final int[] citations) {
        int n = citations.length;
        int[] counts = new int[n + 1];
        for (int citation : citations) {
            if (citation > n) {
                counts[n]++;
            } else {
                counts[citation]++;
            }
        }
        int counted = 0;
        for (int i = n;; i--) {
            counted += counts[i];
            if (counted >= i) {
                return i;
            }
        }
    }
}
package leetcode.lc274_h_index;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC274HIndexTests {
    @Test
    public void testExample() throws Exception {
        int[] citations = { 3, 0, 6, 1, 5 };
        assertEquals(3, new LC274HIndex().hIndex(citations));
    }

    @Test
    public void testNone() throws Exception {
        int[] citations = { 0, 0, 0, 0, 0 };
        assertEquals(0, new LC274HIndex().hIndex(citations));
    }

    @Test
    public void test100() throws Exception {
        int[] citations = { 100 };
        assertEquals(1, new LC274HIndex().hIndex(citations));
    }
}
```rust
fn h_index(citations: &Vec<i32>) -> i32 {
    let n = citations.len();
    let mut counts = vec![0; n + 1];
    for citation in citations.iter() {
        if *citation > n {
            counts[n] += 1;
        } else {
            counts[*citation as usize] += 1;
        }
    }

    let mut counted = 0;
    for i in (0..=n).rev() {
        counted += counts[i];
        if counted >= i as i32 {
            return i;
        }
    }

    0
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let citations = vec![3, 0, 6, 1, 5];
        assert_eq!(h_index(&citations), 3);
    }

    #[test]
    fn test_none() {
        let citations = vec![0, 0, 0, 0, 0];
        assert_eq!(h_index(&citations), 0);
    }

    #[test]
    fn test_100() {
        let citations = vec![100];
        assert_eq!(h_index(&citations), 1);
    }
}
```