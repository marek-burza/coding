package leetcode.lc167_two_sum_ii_input_array_is_sorted;

/**
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/ #easy
 */
public final class LC167TwoSumIIInputArrayIsSorted {
    public int[] twoSum(final int[] numbers, final int target) {
        int[] indices = new int[2];
        if (numbers == null || numbers.length < 2) {
            return indices;
        }
        int a = 0;
        int z = numbers.length - 1;
        while (a < z) {
            int v = numbers[a] + numbers[z];
            if (v == target) {
                indices[0] = a + 1;
                indices[1] = z + 1;
                break;
            } else if (v > target) {
                z--;
            } else {
                a++;
            }
        }
        return indices;
    }
}
