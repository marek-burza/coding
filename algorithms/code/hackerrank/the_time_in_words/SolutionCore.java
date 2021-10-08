package hackerrank.the_time_in_words;

/**
 * https://www.hackerrank.com/challenges/the-time-in-words
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    private static final String[] LUT = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight",
            "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
            "nineteen", "twenty", "twenty one", "twenty two", "twenty three", "twenty four", "twenty five",
            "twenty six", "twenty seven", "twenty eight", "twenty nine", "thirty" };

    protected static String sayTime(final int hValue, final int mValue) {
        int h = hValue;
        int m = mValue;
        StringBuilder result = new StringBuilder();
        if (m != 0) {
            if (m == 30) {
                result.append("half past ");
            } else if (m == 15) {
                result.append("quarter past ");
            } else if (m == 45) {
                result.append("quarter to ");
                h = (h + 1) % 12;
            } else if (m < 30) {
                result.append(LUT[m]);
                if (m == 1) {
                    result.append(" minute past ");
                } else {
                    result.append(" minutes past ");
                }
            } else {
                m = 60 - m;
                result.append(LUT[m]);
                if (m == 1) {
                    result.append(" minute to ");
                } else {
                    result.append(" minutes to ");
                }
                h = (h + 1) % 12;
            }
        }
        result.append(LUT[h]);
        if (m == 0) {
            result.append(" o' clock");
        }
        return result.toString();
    }
}
