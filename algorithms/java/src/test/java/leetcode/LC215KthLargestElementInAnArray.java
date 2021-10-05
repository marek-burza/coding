package leetcode;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * #medium
 */
public class LC215KthLargestElementInAnArray {
    public class Solution {
        public int findKthLargest(int[] nums, int k) {
            Queue<Integer> heap = new PriorityQueue();
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

    @Test
    public void test_example_1() throws Exception {
        int[] nums = {3, 2, 1, 5, 6, 4};
        assertEquals(5, new Solution().findKthLargest(nums, 2));
    }

    @Test
    public void test_example_2() throws Exception {
        int[] nums = {2, 1};
        assertEquals(1, new Solution().findKthLargest(nums, 2));
    }
}
