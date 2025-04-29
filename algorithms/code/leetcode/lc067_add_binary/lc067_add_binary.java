package leetcode.lc067_add_binary;

/**
 * https://leetcode.com/problems/add-binary/ #easy
 */
public final class LC067AddBinary {
    public String addBinary(final String a, final String b) {
        char[] ar = new StringBuilder(a).reverse().toString().toCharArray();
        char[] br = new StringBuilder(b).reverse().toString().toCharArray();
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int i = 0; i < ar.length || i < br.length; i++) {
            int sum = carry;
            sum += i < ar.length ? ar[i] - '0' : 0;
            sum += i < br.length ? br[i] - '0' : 0;
            carry = sum >> 1;
            result.append(((sum & 1) == 0) ? '0' : '1');
        }
        if (carry == 1) {
            result.append('1');
        }
        return result.reverse().toString();
    }
}
package leetcode.lc067_add_binary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC067AddBinaryTests {
    @Test
    public void testExample1() throws Exception {
        assertEquals("100", new LC067AddBinary().addBinary("11", "1"));
    }

    @Test
    public void testExample2() throws Exception {
        assertEquals("10101", new LC067AddBinary().addBinary("1010", "1011"));
    }

    @Test
    public void testExample1Reversed() throws Exception {
        assertEquals("100", new LC067AddBinary().addBinary("1", "11"));
    }

    @Test
    public void testNoCarry() throws Exception {
        assertEquals("1", new LC067AddBinary().addBinary("1", "0"));
    }
}
```rust
fn add_binary(a: &str, b: &str) -> String {
    let ar = a.chars().rev().collect::<Vec<char>>()[..].into_iter().map(|c| c as u8 - b'0' as u8).collect::<Vec<u8>>();
    let br = b.chars().rev().collect::<Vec<char>>()[..].into_iter().map(|c| c as u8 - b'0' as u8).collect::<Vec<u8>>();

    let mut result = Vec::new();
    let mut carry = 0;

    for i in 0..ar.len().max(br.len()) {
        let sum: u8 = match (i < ar.len(), i < br.len()) {
            (true, true) => ar[i] + br[i],
            (true, false) => ar[i],
            (false, true) => br[i],
            _ => 0,
        };
        carry = sum / 2;
        result.push((sum % 2) as char);
    }

    if carry > 0 {
        result.push(('1' as u8 - b'0' as u8) as char);
    }

    String::from_utf8(result).unwrap()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        assert_eq!(add_binary("11", "1"), "100");
    }

    #[test]
    fn test_example_2() {
        assert_eq!(add_binary("1010", "1011"), "10101");
    }

    #[test]
    fn test_example_1_reversed() {
        assert_eq!(add_binary("1", "11"), "100");
    }

    #[test]
    fn test_no_carry() {
        assert_eq!(add_binary("1", "0"), "1");
    }
}
```