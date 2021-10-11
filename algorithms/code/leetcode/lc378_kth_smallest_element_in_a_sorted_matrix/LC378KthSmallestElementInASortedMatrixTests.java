package leetcode.lc378_kth_smallest_element_in_a_sorted_matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC378KthSmallestElementInASortedMatrixTests {
    @Test
    public void testExample() throws Exception {
        int[][] matrix = { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
        assertEquals(13, new LC378KthSmallestElementInASortedMatrix().kthSmallest(matrix, 8));
    }
}
