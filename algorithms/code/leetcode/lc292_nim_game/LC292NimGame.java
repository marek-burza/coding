package leetcode.lc292_nim_game;

/**
 * https://leetcode.com/problems/nim-game/ #easy
 */
public final class LC292NimGame {
    public boolean canWinNim(final int n) {
        return n % 4 != 0;
    }
}
