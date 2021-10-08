package hackerrank.the_grid_search;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * https://www.hackerrank.com/challenges/the-grid-search
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static boolean checkGrid(final String[] big, final String[] small) {
        String all = Arrays.stream(big).collect(Collectors.joining(""));
        int at = -1;
        while (true) {
            at = all.indexOf(small[0], at + 1);
            if ((at % big[0].length()) + small[0].length() > big[0].length()) {
                continue;
            }
            if (-1 == at) {
                break;
            }
            int offset = at;
            boolean ok = true;
            for (String chunk : small) {
                if (!all.substring(offset, offset + chunk.length()).equals(chunk)) {
                    ok = false;
                    break;
                }
                offset += big[0].length();
            }
            if (ok) {
                return true;
            }
        }
        return false;
    }
}
