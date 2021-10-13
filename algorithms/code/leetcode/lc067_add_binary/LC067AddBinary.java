package leetcode.lc067_add_binary;

/**
 * https://leetcode.com/problems/add-binary/ #easy
 */
public final class LC067AddBinary {
    public String addBinary(final String a, final String b) {
        char[] ar = new StringBuilder(a).reverse().toString().toCharArray();
        char[] br = new StringBuilder(b).reverse().toString().toCharArray();
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int i = 0; i < ar.length || i < br.length; i++) {
            int sum = carry;
            sum += i < ar.length ? ar[i] - '0' : 0;
            sum += i < br.length ? br[i] - '0' : 0;
            carry = sum >> 1;
            result.append(((sum & 1) == 0) ? '0' : '1');
        }
        if (carry == 1) {
            result.append('1');
        }
        return result.reverse().toString();
    }
}
