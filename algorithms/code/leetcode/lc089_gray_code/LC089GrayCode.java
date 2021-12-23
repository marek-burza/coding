package leetcode.lc089_gray_code;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/gray-code/
 * #medium
 */
public final class LC089GrayCode {
    public List<Integer> grayCode(final int bitsValue) {
        int bits = bitsValue;
        if (bits == 0) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(0);
            return list;
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        int shifted = 2;
        while (bits > 1) {
            bits--;
            int n = list.size();
            for (int i = n - 1; i >= 0; i--) {
                int value = list.get(i);
                list.add(shifted | value);
            }
            shifted <<= 1;
        }
        return list;
    }
}
