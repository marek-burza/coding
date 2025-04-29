package leetcode.lc292_nim_game;

/**
 * https://leetcode.com/problems/nim-game/ #easy
 */
public final class LC292NimGame {
    public boolean canWinNim(final int n) {
        return n % 4 != 0;
    }
}
package leetcode.lc292_nim_game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC292NimGameTests {
    @Test
    public void test1To10() throws Exception {
        assertTrue(new LC292NimGame().canWinNim(1)); // + 1 o
        assertTrue(new LC292NimGame().canWinNim(2)); // + 2 o o
        assertTrue(new LC292NimGame().canWinNim(3)); // + 3 o o o
        assertFalse(new LC292NimGame().canWinNim(4)); // - 4 o ? ? x
        assertTrue(new LC292NimGame().canWinNim(5)); // + 5 o x ? ? o
        assertTrue(new LC292NimGame().canWinNim(6)); // + 6 o o x ? ? o
        assertTrue(new LC292NimGame().canWinNim(7)); // + 7 o o o x ? ? o
        assertFalse(new LC292NimGame().canWinNim(8)); // - 8 o ? ? . . . . x (leads to 7, 6 or 5)
        assertTrue(new LC292NimGame().canWinNim(9)); // + 9 o x ? ? . . . . o (leads to 8)
        assertTrue(new LC292NimGame().canWinNim(10)); // + 10 o o x ? ? . . . . o (leads to 8)
    }
}
```rust
// Import the necessary crate for unit testing
extern crate assert_cmd;

use super::*;

#[test]
fn test_can_win_nim() {
    let mut lc292_nim_game = LC292NimGame {};

    // Test cases from problem description
    assert!(lc292_nim_game.can_win_nim(1));
    assert!(lc292_nim_game.can_win_nim(2));
    assert!(lc292_nim_game.can_win_nim(3));

    assert!(!lc292_nim_game.can_win_nim(4));
    assert!(lc292_nim_game.can_win_nim(5));
    assert!(lc292_nim_game.can_win_nim(6));
    assert!(lc292_nim_game.can_win_nim(7));

    assert!(!lc292_nim_game.can_win_nim(8));
    assert!(lc292_nim_game.can_win_nim(9));
    assert!(lc292_nim_game.can_win_nim(10));
}
```