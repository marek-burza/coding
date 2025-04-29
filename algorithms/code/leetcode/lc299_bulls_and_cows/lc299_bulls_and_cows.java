package leetcode.lc299_bulls_and_cows;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/bulls-and-cows/
 * #medium
 */
public final class LC299BullsAndCows {
    public String getHint(final String secret, final String guess) {
        HashMap<Character, Integer> countKnown = new HashMap<>();
        HashMap<Character, Integer> countAsked = new HashMap<>();
        int bulls = 0;
        int cows = 0;
        int count;
        for (int i = 0; i < Math.min(secret.length(), guess.length()); i++) {
            // Count characters of each type
            char known = secret.charAt(i);
            char asked = guess.charAt(i);
            count = 0;
            if (countKnown.containsKey(known)) {
                count = countKnown.get(known);
            }
            countKnown.put(known, count + 1);
            count = 0;
            if (countAsked.containsKey(asked)) {
                count = countAsked.get(asked);
            }
            countAsked.put(asked, count + 1);
            // Check for a bull
            if (known == asked) {
                bulls++;
            }
        }
        // Count the cows
        for (char asked : countAsked.keySet()) {
            if (countKnown.containsKey(asked)) {
                cows += Math.min(countKnown.get(asked), countAsked.get(asked));
            }
        }
        // Remove the bulls from the cows
        cows -= bulls;
        return "" + bulls + "A" + cows + "B";
        // It would have been faster to have one lookup table and update cows up or down
        // accordingly
    }
}
package leetcode.lc299_bulls_and_cows;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC299BullsAndCowsTests {
    @Test
    public void test1807And7810() throws Exception {
        assertEquals("1A3B", new LC299BullsAndCows().getHint("1807", "7810"));
    }

    @Test
    public void test1123And0111() throws Exception {
        assertEquals("1A1B", new LC299BullsAndCows().getHint("1123", "0111"));
    }

    @Test
    public void test1122And2211() throws Exception {
        assertEquals("0A4B", new LC299BullsAndCows().getHint("1122", "2211"));
    }

    @Test
    public void test11And10() throws Exception {
        assertEquals("1A0B", new LC299BullsAndCows().getHint("11", "10"));
    }
}
```rust
use std::collections::HashMap;

pub struct LC299BullsAndCows {
    pub hint: String,
}

impl LC299BullsAndCows {
    pub fn get_hint(&self, secret: &str, guess: &str) -> String {
        let mut count_known = HashMap::<char, i32>::new();
        let mut count_asked = HashMap::<char, i32>::new();
        let mut bulls = 0;
        let mut cows = 0;

        for (i, known) in secret.chars().enumerate() {
            if i >= guess.len() {
                break;
            }

            let asked = guess.chars().nth(i).unwrap();

            *count_known.entry(known).or_insert(0) += 1;
            *count_asked.entry(asked).or_insert(0) += 1;

            if known == asked {
                bulls += 1;
            } else {
                cows += 1;
            }
        }

        for (i, count) in count_asked.into_iter() {
            let count_known = *count_known.get(&i).unwrap_or(&0);
            cows += std::cmp::min(count, count_known);
        }

        format!("{bulls}A{cows}B", bulls=bulls, cows=cows)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1807_and_7810() -> Result<(), Box<dyn std::error::Error>> {
        let hint = LC299BullsAndCows { hint: String::new() };
        assert_eq!(hint.get_hint("1807", "7810"), "1A3B");
        Ok(())
    }

    #[test]
    fn test_1123_and_0111() -> Result<(), Box<dyn std::error::Error>> {
        let hint = LC299BullsAndCows { hint: String::new() };
        assert_eq!(hint.get_hint("1123", "0111"), "1A1B");
        Ok(())
    }

    #[test]
    fn test_1122_and_2211() -> Result<(), Box<dyn std::error::Error>> {
        let hint = LC299BullsAndCows { hint: String::new() };
        assert_eq!(hint.get_hint("1122", "2211"), "0A4B");
        Ok(())
    }

    #[test]
    fn test_11_and_10() -> Result<(), Box<dyn std::error::Error>> {
        let hint = LC299BullsAndCows { hint: String::new() };
        assert_eq!(hint.get_hint("11", "10"), "1A0B");
        Ok(())
    }
}
```