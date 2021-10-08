package hackerrank.maximum_perimeter_triangle;

import hackerrank.SolutionTester;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/maximum-perimeter-triangle
 */
public class TestSolution {
    @Test
    public void testExample() throws Exception {
        SolutionTester.test(Solution.class, "_example");
    }

    @Test
    public void test02() throws Exception {
        SolutionTester.test(Solution.class, "02");
    }

    @Test
    public void testDegenerate() throws Exception {
        assertNull(SolutionCore.pick(new ArrayList<>()));
    }

    @Test
    public void testAcb() throws Exception {
        assertNull(SolutionCore.pick(Arrays.stream(new int[] { 0, 1, 1 }).boxed().collect(Collectors.toList())));
    }
}
