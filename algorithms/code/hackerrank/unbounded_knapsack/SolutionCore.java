package hackerrank.unbounded_knapsack;

import java.util.List;

public final class SolutionCore {
    private SolutionCore() {
    }

    public static int unboundedKnapsack(final int w, final List<Integer> values) {
        List<Integer> weights = values; // Special case
        int n = values.size();
        int[] m = new int[w + 1];
        for (int wi = 0; wi < w + 1; wi++) {
            for (int vi = 0; vi < n; vi++) {
                if (weights.get(vi) <= wi) {
                    int without = m[wi];
                    int with = m[wi - weights.get(vi)] + values.get(vi);
                    m[wi] = Math.max(without, with);
                }
            }
        }
        return m[w];
    }
}
