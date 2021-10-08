package hackerrank.bigger_is_greater;

import org.junit.jupiter.api.Test;

import hackerrank.SolutionTester;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.hackerrank.com/challenges/bigger-is-greater
 */
public class TestSolution {
    @Test
    public void testAb() {
        assertEquals("ba", SolutionCore.biggerIsGreater("ab"));
    }

    @Test
    public void testBb() {
        assertEquals("no answer", SolutionCore.biggerIsGreater("bb"));
    }

    @Test
    public void testHefg() {
        assertEquals("hegf", SolutionCore.biggerIsGreater("hefg"));
    }

    @Test
    public void testDhck() {
        assertEquals("dhkc", SolutionCore.biggerIsGreater("dhck"));
    }

    @Test
    public void testDkhc() {
        assertEquals("hcdk", SolutionCore.biggerIsGreater("dkhc"));
    }

    @Test
    public void test01() throws Exception {
        SolutionTester.test(Solution.class, "01");
    }

    @Test
    public void test02() throws Exception {
        SolutionTester.test(Solution.class, "02");
    }

    @Test
    public void test03() throws Exception {
        SolutionTester.test(Solution.class, "03");
    }
}
