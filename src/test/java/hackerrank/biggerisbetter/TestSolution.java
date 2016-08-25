package hackerrank.biggerisbetter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/bigger-is-greater
 */
public class TestSolution {
    @Test
    public void test_ab() {
        assertEquals("ba", Solution.advance("ab"));
    }

    @Test
    public void test_bb() {
        assertEquals("no answer", Solution.advance("bb"));
    }

    @Test
    public void test_hefg() {
        assertEquals("hegf", Solution.advance("hefg"));
    }

    @Test
    public void test_dhck() {
        assertEquals("dhkc", Solution.advance("dhck"));
    }

    @Test
    public void test_dkhc() {
        assertEquals("hcdk", Solution.advance("dkhc"));
    }
}
