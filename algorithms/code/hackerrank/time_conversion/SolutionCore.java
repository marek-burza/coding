package hackerrank.time_conversion;

/**
 * https://www.hackerrank.com/challenges/time-conversion
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static String toMilitary(final String ampm) {
        boolean afternoon = ampm.charAt(8) == 'P';
        String hour = ampm.substring(0, 2);
        afternoon = hour.equals("12") ? !afternoon : afternoon;
        hour = String.format("%02d", (Integer.parseInt(hour) + (afternoon ? 12 : 0)) % 24);
        return hour + ampm.substring(2, 8);
    }
}
