package leetcode.lc378_kth_smallest_element_in_a_sorted_matrix;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * #medium
 */
public final class LC378KthSmallestElementInASortedMatrix {
    public int kthSmallest(final int[][] matrix, final int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(k, Collections.reverseOrder());
        for (int[] row : matrix) {
            for (int cell : row) {
                heap.add(cell);
                if (heap.size() > k) {
                    heap.poll();
                }
            }
        }
        return heap.poll();
    }
}
