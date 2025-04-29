package leetcode.lc060_permutation_sequence;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutation-sequence/ #hard
 */
public final class LC060PermutationSequence {
    public String getPermutation(final int n, final int kValue) {
        int k = kValue;
        if (n < 0 || k < 0) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        List<Integer> remaining = new ArrayList<>();
        List<Integer> factorials = new ArrayList<>();
        factorials.add(0);
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
            factorials.add(factorial);
            remaining.add(i);
        }
        for (int i = 1; i < n; i++) {
            int block = factorials.get(n - i);
            int index = (k - 1) / block;
            result.append(remaining.remove(index));
            k -= index * block;
        }
        result.append(remaining.remove(0));
        return result.toString();
    }
}
package leetcode.lc060_permutation_sequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC060PermutationSequenceTests {
    @Test
    public void test2And1() throws Exception {
        assertEquals("12", new LC060PermutationSequence().getPermutation(2, 1));
    }

    @Test
    public void test3And2() throws Exception {
        assertEquals("132", new LC060PermutationSequence().getPermutation(3, 2));
    }

    @Test
    public void testNothing() throws Exception {
        assertNull(new LC060PermutationSequence().getPermutation(1, -1));
        assertNull(new LC060PermutationSequence().getPermutation(-1, 1));
    }
}
```rust
// Define the permutation sequence solver
pub struct PermutationSequenceSolver;

impl PermutationSequenceSolver {
    // Generate the next permutation in sequence for a given n and k value
    pub fn get_permutation(&self, n: usize, mut k_value: usize) -> Option<String> {
        if n < 0 || k_value < 0 {
            return None;
        }

        let mut result = String::new();
        let mut remaining = vec![1; n + 1];
        let factorials = vec![1];
        for i in 1..=n {
            factorials.push(factorials[i - 1] * i as u32);
            remaining[i] = i;
        }

        for i in (0..n).rev() {
            let block = factorials[n - i];
            let index = (k_value - 1) / block;
            result.push_str(&remaining[index].to_string());
            k_value -= index * block;
            if remaining.is_empty() {
                break;
            }
            remaining.remove(index);
        }

        // Append the last number
        result.push_str(&remaining[0].to_string());

        Some(result)
    }
}

// Define tests for the permutation sequence solver
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_2_and_1() -> String {
        let mut solver = PermutationSequenceSolver;
        solver.get_permutation(2, 1).unwrap()
    }

    #[test]
    fn test_3_and_2() -> String {
        let mut solver = PermutationSequenceSolver;
        solver.get_permutation(3, 2).unwrap()
    }

    #[test]
    fn test_nothing() -> Option<String> {
        PermutationSequenceSolver::get_permutation(1, -1)
    }
}
```