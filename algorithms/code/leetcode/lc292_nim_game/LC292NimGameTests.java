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
