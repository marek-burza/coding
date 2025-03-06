package leetcode.lc215_kth_largest_element_in_an_array;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/ #medium
 */
public final class LC215KthLargestElementInAnArray {
    public int findKthLargest(final int[] nums, final int k) {
        Queue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            if (heap.size() < k || num > heap.peek()) {
                heap.add(num);
                if (heap.size() > k) {
                    heap.poll();
                }
            }
        }
        return heap.poll();
    }
}
package leetcode.lc215_kth_largest_element_in_an_array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC215KthLargestElementInAnArrayTests {
    @Test
    public void testExample1() throws Exception {
        int[] nums = { 3, 2, 1, 5, 6, 4 };
        assertEquals(5, new LC215KthLargestElementInAnArray().findKthLargest(nums, 2));
    }

    @Test
    public void testExample2() throws Exception {
        int[] nums = { 2, 1 };
        assertEquals(1, new LC215KthLargestElementInAnArray().findKthLargest(nums, 2));
    }
}
