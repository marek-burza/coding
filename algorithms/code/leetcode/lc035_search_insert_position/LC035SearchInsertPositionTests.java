package leetcode.lc035_search_insert_position;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC035SearchInsertPositionTests {
    @Test
    public void testEmptyAnd5() throws Exception {
        int[] nums = {};
        assertEquals(0, new LC035SearchInsertPosition().searchInsert(nums, 5));
    }

    @Test
    public void test1356And5() throws Exception {
        int[] nums = { 1, 3, 5, 6 };
        assertEquals(2, new LC035SearchInsertPosition().searchInsert(nums, 5));
    }

    @Test
    public void test1356And2() throws Exception {
        int[] nums = { 1, 3, 5, 6 };
        assertEquals(1, new LC035SearchInsertPosition().searchInsert(nums, 2));
    }

    @Test
    public void test1356And7() throws Exception {
        int[] nums = { 1, 3, 5, 6 };
        assertEquals(4, new LC035SearchInsertPosition().searchInsert(nums, 7));
    }

    @Test
    public void test1356And0() throws Exception {
        int[] nums = { 1, 3, 5, 6 };
        assertEquals(0, new LC035SearchInsertPosition().searchInsert(nums, 0));
    }

    @Test
    public void test1356And1() throws Exception {
        int[] nums = { 1, 3, 5, 6 };
        assertEquals(0, new LC035SearchInsertPosition().searchInsert(nums, 1));
    }
}
