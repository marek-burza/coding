package leetcode.lc344_reverse_string;

/**
 * https://leetcode.com/problems/reverse-string/ #easy
 */
public final class LC344ReverseString {
    public void reverseString(final char[] s) {
        for (int i = 0; i < Math.round(s.length / 2.0); i++) {
            char exchange = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = exchange;
        }
    }
}
package leetcode.lc344_reverse_string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC344ReverseStringTests {
    @Test
    public void testExample() throws Exception {
        char[] s = { 'h', 'e', 'l', 'l', 'o' };
        new LC344ReverseString().reverseString(s);
        assertArrayEquals(new char[] { 'o', 'l', 'l', 'e', 'h' }, s);
    }
}
```rust
// Import the necessary libraries.
use std::vec;

// Define a struct for reversing string.
struct LC344ReverseString {
    // Define the function to reverse the string.
    pub fn reverse_string(&mut self, s: &mut [u8]) {
        let length = s.len() as f64;
        for i in 0..(length / 2.0).floor() {
            // Swap characters at index 'i' and the corresponding index from end.
            let exchange = s[i] as u8;
            *s.get_mut(i).unwrap() = *s.get(s.len() - 1 - i).unwrap();
            *s.get(s.len() - 1 - i).unwrap() = exchange;
        }
    }
}

// Import the necessary libraries for testing.
use std::collections::Vec;

// Define a struct for testing LC344ReverseString.
struct LC344ReverseStringTests {
    // Define the function to test reverse string function.
    pub fn test_example(&mut self) {
        let mut s = vec!['h', 'e', 'l', 'l', 'o'];
        let mut lc = LC344ReverseString { };
        lc.reverse_string(&mut s);
        assert_eq!(s, vec!['o', 'l', 'l', 'e', 'h']);
    }
}
```