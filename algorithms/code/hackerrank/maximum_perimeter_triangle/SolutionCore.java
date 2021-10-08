package hackerrank.maximum_perimeter_triangle;

import java.util.Collections;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/maximum-perimeter-triangle
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static int[] pick(final List<Integer> sticks) {
        Collections.sort(sticks, Collections.reverseOrder());
        for (int i = 2; i < sticks.size(); i++) {
            for (int j = 0; j < i - 1; j++) {
                for (int k = j + 1; k < i; k++) {
                    int a = sticks.get(i);
                    int b = sticks.get(k);
                    int c = sticks.get(j);
                    if (a + b <= c /* || a + c <= b || b + c <= a */) {
                        continue;
                    }
                    return new int[] { a, b, c };
                }
            }
        }
        return null;
    }
}
