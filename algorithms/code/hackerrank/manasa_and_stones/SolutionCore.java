package hackerrank.manasa_and_stones;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/manasa-and-stones
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static List<BigInteger> manasaAndStones(final int n, final int aValue, final int bValue) {
        int a = aValue;
        int b = bValue;
        List<BigInteger> result = new ArrayList<>();
        if (a > b) {
            int exchange = a;
            a = b;
            b = exchange;
        }
        BigInteger current = BigInteger.valueOf((long) a * (long) (n - 1));
        BigInteger delta = BigInteger.valueOf(b - a);
        result.add(current);
        if (a != b) {
            for (int i = 0; i < n - 1; i++) {
                current = current.add(delta);
                result.add(current);
            }
        }
        return result;
    }
}
