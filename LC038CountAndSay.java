package leetcode;

/**
 * https://leetcode.com/problems/count-and-say/
 */
public class LC038CountAndSay {
    public String countAndSay(int n) {
        if (n < 1) return null;
        String result = "1";
        while (n > 1) {
            StringBuffer current = new StringBuffer();
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
            if (count > 0) {
                current.append(count);
                current.append(check);
            }
            n--;
            result = current.toString();
        }
        return result;
    }

    public static void main(String[] arguments) {
        LC038CountAndSay solution = new LC038CountAndSay();
        System.out.println(solution.countAndSay(1));
        System.out.println(solution.countAndSay(2));
        System.out.println(solution.countAndSay(3));
        System.out.println(solution.countAndSay(4));
        System.out.println(solution.countAndSay(5));
    }
}
