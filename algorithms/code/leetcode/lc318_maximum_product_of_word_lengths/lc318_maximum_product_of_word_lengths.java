package leetcode.lc318_maximum_product_of_word_lengths;

/**
 * https://leetcode.com/problems/maximum-product-of-word-lengths/
 * #medium
 */
public final class LC318MaximumProductOfWordLengths {
    public int maxProduct(final String[] words) {
        int[] signature = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (char character : words[i].toCharArray()) {
                signature[i] |= 1 << (character - 'a');
            }
        }
        int maximum = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((signature[i] & signature[j]) == 0) {
                    maximum = Math.max(maximum, words[i].length() * words[j].length());
                }
            }
        }
        return maximum;
    }
}
package leetcode.lc318_maximum_product_of_word_lengths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC318MaximumProductOfWordLengthsTests {
    @Test
    public void testExample1() throws Exception {
        String[] input = { "abcw", "baz", "foo", "bar", "xtfn", "abcdef" };
        assertEquals(16, new LC318MaximumProductOfWordLengths().maxProduct(input));
    }

    @Test
    public void testExample2() throws Exception {
        String[] input = { "a", "ab", "abc", "d", "cd", "bcd", "abcd" };
        assertEquals(4, new LC318MaximumProductOfWordLengths().maxProduct(input));
    }

    @Test
    public void testExample3() throws Exception {
        String[] input = { "a", "aa", "aaa", "aaaa" };
        assertEquals(0, new LC318MaximumProductOfWordLengths().maxProduct(input));
    }
}
```rust
struct MaximumProductOfWordLengths;

impl MaximumProductOfWordLengths {
    fn max_product(words: Vec<String>) -> i32 {
        let mut signatures: Vec<u32> = vec![0; words.len()];
        for (i, word) in words.iter().enumerate() {
            for c in word.chars() {
                signatures[i] |= 1 << (c as usize - 'a' as usize);
            }
        }

        let mut max_product = 0;
        for i in 0..words.len() - 1 {
            for j in (i + 1)..words.len() {
                if (signatures[i] & signatures[j]) == 0 {
                    max_product = max(max_product, words[i].len() as i32 * words[j].len() as i32);
                }
            }
        }

        max_product
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example1() {
        let input = vec!["abcw".to_string(), "baz".to_string(), "foo".to_string(), "bar".to_string(), "xtfn".to_string(), "abcdef".to_string()];
        assert_eq!(16, MaximumProductOfWordLengths::max_product(input));
    }

    #[test]
    fn test_example2() {
        let input = vec!["a".to_string(), "ab".to_string(), "abc".to_string(), "d".to_string(), "cd".to_string(), "bcd".to_string(), "abcd".to_string()];
        assert_eq!(4, MaximumProductOfWordLengths::max_product(input));
    }

    #[test]
    fn test_example3() {
        let input = vec!["a".to_string(), "aa".to_string(), "aaa".to_string(), "aaaa".to_string()];
        assert_eq!(0, MaximumProductOfWordLengths::max_product(input));
    }
}
```