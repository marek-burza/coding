package hackerrank.qheap1;

import hackerrank.SolutionTester;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SolutionTests {
    @Test
    public void testSearchForAbsent() throws Exception {
        List<Integer> heap = Arrays.stream(new int[] { 6, 3, 0, 5 }).boxed().collect(Collectors.toList());
        SolutionCore.heapBuild(heap);
        assertEquals(SolutionCore.heapSearch(heap, -1), -1);
    }

    @Test
    public void testDeleteSwapsOnSameLevel() throws Exception {
        List<Integer> heap = Arrays.stream(new int[] { 0, 10, 8, 13, 14, 9 }).boxed().collect(Collectors.toList());
        SolutionCore.heapDeleteIndex(heap, 4);
        assertNotEquals(heap.get(heap.size() - 1), 9);
    }

    @Test
    public void testExample() throws Exception {
        SolutionTester.test(Solution.class, "example");
    }

    @Test
    public void test00() throws Exception {
        SolutionTester.test(Solution.class, "00");
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
    public void test08() throws Exception {
        SolutionTester.test(Solution.class, "08");
    }
}
