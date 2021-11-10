package leetcode.lc374_guess_number_higher_or_lower;

/**
 * https://leetcode.com/problems/guess-number-higher-or-lower/ #easy
 */
public final class LC374GuessNumberHigherOrLower {
    public int x;

    protected int guess(final int n) {
        if (x < n) {
            return -1;
        }
        if (x > n) {
            return 1;
        }
        return 0;
    }

    public int guessNumber(final int n) {
        int a = 1;
        int z = n;
        while (a != z) {
            int checking = (int) (((long) a + (long) z) / 2);
            switch (guess(checking)) {
            case 1:
                a = checking + 1;
                break;
            case -1:
                z = checking - 1;
                break;
            default:
                return checking;
            }
        }
        return a;
    }
}
