package leetcode.lc060_permutation_sequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC060PermutationSequenceTests {
    @Test
    public void test2And1() throws Exception {
        assertEquals("12", new LC060PermutationSequence().getPermutation(2, 1));
    }

    @Test
    public void test3And2() throws Exception {
        assertEquals("132", new LC060PermutationSequence().getPermutation(3, 2));
    }

    @Test
    public void testNothing() throws Exception {
        assertNull(new LC060PermutationSequence().getPermutation(1, -1));
        assertNull(new LC060PermutationSequence().getPermutation(-1, 1));
    }
}
