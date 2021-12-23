package leetcode.lc198_house_robber;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC198HouseRobberTests {
    @Test
    public void test664843310() throws Exception {
        int[] nums = { 6, 6, 4, 8, 4, 3, 3, 10 };
        assertEquals(27, new LC198HouseRobber().rob(nums));
    }
}
