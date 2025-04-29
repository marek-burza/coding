package leetcode.lc241_different_ways_to_add_parentheses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/different-ways-to-add-parentheses/ #medium
 * #dynamic-programing
 */
public final class LC241DifferentWaysToAddParentheses {
    private static long key(final int a, final int z) {
        return (((long) a) << 32) | (long) z;
    }

    private List<Integer> traverse(final String[] items, final int a, final int z,
            final HashMap<Long, List<Integer>> cache) {
        List<Integer> result = cache.get(key(a, z));
        if (result == null) {
            result = new ArrayList<>();
        } else {
            return result;
        }
        if (a == z) {
            result.add(Integer.parseInt(items[a]));
        } else {
            for (int operator = a + 1; operator < z; operator += 2) {
                List<Integer> before = traverse(items, a, operator - 1, cache);
                List<Integer> after = traverse(items, operator + 1, z, cache);
                for (int ante : before) {
                    for (int post : after) {
                        switch (items[operator]) {
                            case "+":
                                result.add(ante + post);
                                break;
                            case "-":
                                result.add(ante - post);
                                break;
                            default: // "*"
                                result.add(ante * post);
                                break;
                        }
                    }
                }
            }
        }
        cache.put(key(a, z), result);
        return result;
    }

    public List<Integer> diffWaysToCompute(final String input) {
        String[] items = input.split("((?<=[+\\-*])|(?=[+\\-*]))");
        List<Integer> result = traverse(items, 0, items.length - 1, new HashMap<>());
        return result;
    }
}
package leetcode.lc241_different_ways_to_add_parentheses;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/different-ways-to-add-parentheses/ #medium
 */
public final class LC241DifferentWaysToAddParenthesesTests {
    private void test(final int[] expected, final List<Integer> result) throws Exception {
        assertEquals(expected.length, result.size());
        Collections.sort(result);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.get(i).intValue());
        }
    }

    @Test
    public void testExample1() throws Exception {
        int[] expected = { 0, 2 };
        test(expected, new LC241DifferentWaysToAddParentheses().diffWaysToCompute("2-1-1"));
    }

    @Test
    public void testExample2() throws Exception {
        int[] expected = { -34, -14, -10, -10, 10 };
        test(expected, new LC241DifferentWaysToAddParentheses().diffWaysToCompute("2*3-4*5"));
    }

    @Test
    public void testOther() throws Exception {
        int[] expected = { 7 };
        test(expected, new LC241DifferentWaysToAddParentheses().diffWaysToCompute("3+4"));
    }
}
```rust
use std::collections::HashMap;
use std::iter::Map;

enum Operator {
    Add,
    Subtract,
    Multiply,
}

fn key(a: usize, z: usize) -> u64 {
    (((a as u64) << 32) | (z as u64))
}

fn traverse(items: &[&str], a: usize, z: usize, cache: &mut HashMap<u64, Vec<i64>>) -> Vec<i64> {
    let key_a_z = key(a, z);
    match cache.get(&key_a_z) {
        Some(result) => result.clone(),
        None => {
            let mut result = vec![];
            if a == z {
                for s in items[a].split("+").map(str::parse).flatten() {
                    result.push(s);
                }
            } else {
                for operator in (a + 1)..=z {
                    let before = traverse(items, a, operator - 1, cache);
                    let after = traverse(items, operator + 1, z, cache);

                    for ante in before {
                        for post in after {
                            match items[operator] {
                                "+" => result.push(ante + post),
                                "-" => result.push(ante - post),
                                "*" => result.push(ante * post),
                                _ => unreachable!(),
                            }
                        }
                    }
                }
            }

            cache.insert(key_a_z, result);
            result
        }
    }
}

fn diff_ways_to_compute(input: &str) -> Vec<i64> {
    let items: Vec<_> = input.split("([+\\-*])").map(|s| s.trim()).collect();
    traverse(&items, 0, items.len() - 1, &mut HashMap::new())
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example1() {
        let input = "2-1-1";
        assert_eq!(vec![0, 2], diff_ways_to_compute(input));
    }

    #[test]
    fn test_example2() {
        let input = "2*3-4*5";
        assert_eq!(
            vec![-34, -14, -10, -10, 10],
            diff_ways_to_compute(input)
        );
    }

    #[test]
    fn test_other() {
        let input = "3+4";
        assert_eq!(vec![7], diff_ways_to_compute(input));
    }
}
```