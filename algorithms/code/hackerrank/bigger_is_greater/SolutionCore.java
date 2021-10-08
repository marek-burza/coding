package hackerrank.bigger_is_greater;

import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/bigger-is-greater
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static String biggerIsGreater(final String s) {
        char[] array = s.toCharArray();
        for (int i = array.length - 1; 0 < i; i--) {
            if (array[i - 1] < array[i]) {
                Arrays.sort(array, i, array.length);
                for (int j = i; true/* j < array.length */; j++) {
                    if (array[i - 1] < array[j]) {
                        char exchange = array[i - 1];
                        array[i - 1] = array[j];
                        array[j] = exchange;
                        Arrays.sort(array, i, array.length);
                        return new String(array);
                    }
                }
            }
        }
        return "no answer";
    }
}
