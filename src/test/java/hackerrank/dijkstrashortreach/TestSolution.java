package hackerrank.dijkstrashortreach;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/dijkstrashortreach
 */
public class TestSolution {
    @Test
    public void test_00() throws Exception {
        Class solution = Solution.class;
        String inName = "input00.txt";
        String outName = "output00.txt";
        test(solution, inName, outName);
    }

    @Test
    public void test_01() throws Exception {
        Class solution = Solution.class;
        String inName = "input01.txt";
        String outName = "output01.txt";
        test(solution, inName, outName);
    }

    @Test
    public void test_03() throws Exception {
        Class solution = Solution.class;
        String inName = "input03.txt";
        String outName = "output03.txt";
        test(solution, inName, outName);
    }

    @Test
    public void test_04() throws Exception {
        Class solution = Solution.class;
        String inName = "input04.txt";
        String outName = "output04.txt";
        test(solution, inName, outName);
    }

    @Test
    public void test_07() throws Exception {
        Class solution = Solution.class;
        String inName = "input07.txt";
        String outName = "output07.txt";
        test(solution, inName, outName);
    }

    private void test(Class solution, String inName, String outName) throws Exception {
        Field modifiers = Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        Field inOverride = solution.getDeclaredField("inOverride");
        inOverride.set(null, getClass().getResourceAsStream(inName));
        Field outOverride = solution.getDeclaredField("outOverride");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        outOverride.set(null, new PrintStream(output));
        Method main = solution.getDeclaredMethod("main", String[].class);
        main.invoke(null, new Object[]{new String[]{}});
        BufferedReader expected = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(outName)));
        BufferedReader result = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(output.toByteArray())));
        while (expected.ready() && result.ready()) {
            assertEquals(expected.readLine().trim(), result.readLine().trim());
        }
        assertEquals(expected.ready(), result.ready());
    }
}
