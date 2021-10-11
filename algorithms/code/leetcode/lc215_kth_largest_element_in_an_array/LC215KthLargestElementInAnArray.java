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
