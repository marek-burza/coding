package leetcode;

import org.junit.Test;

import java.util.Collections;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 */
public class LC378KthSmallestElementInASortedMatrix {
    public class Solution {
        public int kthSmallest(int[][] matrix, int k) {
            PriorityQueue<Integer> heap = new PriorityQueue(k, Collections.reverseOrder());
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

    @Test
    public void test_example() throws Exception {
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        assertEquals(13, new Solution().kthSmallest(matrix, 8));
    }
}
