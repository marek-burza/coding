package hackerrank;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

/**
 * Generic testing framework for Hacker Rank solutions
 */
public class SolutionTester {
    public static void test(Class<?> solution, String inName, String outName) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream outValue = new PrintStream(output);
        InputStream inValue = solution.getResourceAsStream(inName);
        Field inOverride = solution.getDeclaredField("inOverride");
        inOverride.setAccessible(true);
        inOverride.set(null, inValue);
        Field outOverride = solution.getDeclaredField("outOverride");
        outOverride.setAccessible(true);
        outOverride.set(null, outValue);
        Method main = solution.getDeclaredMethod("main", String[].class);
        main.setAccessible(true);
        main.invoke(null, new Object[]{new String[]{}});
        BufferedReader expected = new BufferedReader(
                new InputStreamReader(solution.getResourceAsStream(outName))
        );
        BufferedReader result = new BufferedReader(
                new InputStreamReader(new ByteArrayInputStream(output.toByteArray()))
        );
        while (expected.ready() && result.ready()) {
            assertEquals(expected.readLine().trim(), result.readLine().trim());
        }
        assertEquals(expected.ready(), result.ready());
    }
}
