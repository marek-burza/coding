package leetcode.lc038_count_and_say;

/**
 * https://leetcode.com/problems/count-and-say/
 * #medium
 */
public final class LC038CountAndSay {
    public String countAndSay(final int nValue) {
        int n = nValue;
        if (n < 1) {
            return null;
        }
        String result = "1";
        while (n > 1) {
            StringBuilder current = new StringBuilder();
            char check = '0';
            int count = 0;
            for (char character : result.toCharArray()) {
                if (check != character) {
                    if (count > 0) {
                        current.append(count);
                        current.append(check);
                    }
                    count = 1;
                    check = character;
                } else {
                    count++;
                }
            }
            current.append(count);
            current.append(check);
            n--;
            result = current.toString();
        }
        return result;
    }
}
