package leetcode.lc374_guess_number_higher_or_lower;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC374GuessNumberHigherOrLowerTests {
    @Test
    public void test2In10() throws Exception {
        LC374GuessNumberHigherOrLower solution = new LC374GuessNumberHigherOrLower();
        solution.x = 2;
        assertEquals(2, solution.guessNumber(10));
    }

    @Test
    public void test8In10() throws Exception {
        LC374GuessNumberHigherOrLower solution = new LC374GuessNumberHigherOrLower();
        solution.x = 8;
        assertEquals(8, solution.guessNumber(10));
    }

    @Test
    public void test65789() throws Exception {
        LC374GuessNumberHigherOrLower solution = new LC374GuessNumberHigherOrLower();
        solution.x = 65789;
        assertEquals(solution.x, solution.guessNumber(Integer.MAX_VALUE));
    }

    @Test
    public void test1() throws Exception {
        LC374GuessNumberHigherOrLower solution = new LC374GuessNumberHigherOrLower();
        solution.x = 1;
        assertEquals(solution.x, solution.guessNumber(1));
    }
}
