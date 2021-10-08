package hackerrank.encryption;

/**
 * https://www.hackerrank.com/challenges/encryption
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static String encrypt(final String plain) {
        int l = plain.length();
        int ceil = (int) Math.ceil(Math.sqrt(l));
        int rows;
        int cols = ceil;
        // do {
        rows = (l / cols) + ((l % cols) > 0 ? 1 : 0);
        // } while (rows * cols-- < l);
        // cols++;
        StringBuilder result = new StringBuilder();
        for (int c = 0; c < cols; c++) {
            if (c != 0) {
                result.append(" ");
            }
            for (int r = 0; r < rows; r++) {
                int index = r * cols + c;
                if (index < l) {
                    result.append(plain.charAt(index));
                }
            }
        }
        return result.toString();
    }
}
