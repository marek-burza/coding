package leetcode.lc334_increasing_triplet_subsequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC334IncreasingTripletSubsequenceTests {
    @Test
    public void testEmpty() throws Exception {
        assertFalse(new LC334IncreasingTripletSubsequence().increasingTriplet(new int[] {}));
    }

    @Test
    public void testExample1() throws Exception {
        assertTrue(new LC334IncreasingTripletSubsequence().increasingTriplet(new int[] { 1, 2, 3, 4, 5 }));
    }

    @Test
    public void testExample2() throws Exception {
        assertFalse(new LC334IncreasingTripletSubsequence().increasingTriplet(new int[] { 5, 4, 3, 2, 1 }));
    }

    @Test
    public void testOther() throws Exception {
        assertTrue(new LC334IncreasingTripletSubsequence().increasingTriplet(new int[] { 1, 2, 3, 1, 2, 1 }));
    }

    @Test
    public void testNothing() throws Exception {
        assertFalse(new LC334IncreasingTripletSubsequence().increasingTriplet(null));
        assertFalse(new LC334IncreasingTripletSubsequence().increasingTriplet(new int[] { 0, 1 }));
    }

    @Test
    public void test516() throws Exception {
        assertFalse(new LC334IncreasingTripletSubsequence().increasingTriplet(new int[] { 5, 1, 6 }));
    }

    @Test
    public void test24Minus2Minus3() throws Exception {
        assertFalse(new LC334IncreasingTripletSubsequence().increasingTriplet(new int[] { 2, 4, -2, -3 }));
    }
}
