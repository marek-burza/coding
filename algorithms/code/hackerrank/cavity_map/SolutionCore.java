package hackerrank.cavity_map;

/**
 * https://www.hackerrank.com/challenges/cavity-map
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static char[][] markCavities(final char[][] map) {
        int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
        int n = map.length;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                int depth = (map[i][j] & 0x7FFF) - '0';
                boolean deeper = true;
                for (int[] delta : deltas) {
                    int other = (map[i + delta[0]][j + delta[1]] & 0x7FFF) - '0';
                    if (other >= depth) {
                        deeper = false;
                    }
                }
                if (deeper) {
                    map[i][j] |= 0x1000;
                }
            }
        }
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if ((map[i][j] & 0x1000) != 0) {
                    map[i][j] = 'X';
                }
            }
        }
        return map;
    }
}
