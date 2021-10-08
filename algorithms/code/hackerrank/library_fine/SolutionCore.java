package hackerrank.library_fine;

/**
 * https://www.hackerrank.com/challenges/library-fine
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static int libraryFine(final int da, final int ma, final int ya, final int de, final int me,
            final int ye) {
        if (ya > ye) {
            return 10000;
        } else if (ya == ye) {
            if (ma > me) {
                return (ma - me) * 500;
            } else if (ma == me) {
                if (da > de) {
                    return (da - de) * 15;
                }
            }
        }
        return 0;
    }
}
