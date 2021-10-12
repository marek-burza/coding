package leetcode;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/gray-code/
 * #medium
 */
public final class LC089GrayCode {
    public final class Solution {
        public List<Integer> grayCode(final int bits) {
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

    private int[] freeze(final List<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    @Test
    public void test_4() throws Exception {
        int[] expected = {0, 1, 3, 2, 6, 7, 5, 4, 12, 13, 15, 14, 10, 11, 9, 8};
        assertArrayEquals(expected, freeze(new Solution().grayCode(4)));
    }
}
