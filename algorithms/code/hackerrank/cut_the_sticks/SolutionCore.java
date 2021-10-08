package hackerrank.cut_the_sticks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/cut-the-sticks
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static List<Integer> countCuts(final int[] a) {
        Arrays.sort(a);
        List<Integer> cuts = new ArrayList<>();
        int cut = 0;
        int n = a.length;
        for (int i = 0; i < n;) {
            cuts.add(n - i);
            cut = a[i];
            while (i < n && a[i] - cut <= 0) {
                i++;
            }
        }
        return cuts;
    }
}
