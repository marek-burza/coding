package hackerrank;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Generic testing framework for HackerRank solutions
 */
public final class SolutionTester {
    private SolutionTester() {
    }

    public static void test(final Class<?> solution, final String name) throws Exception {
        String inName = String.format("input%s.txt", name);
        String outName = String.format("output%s.txt", name);
        InputStream input = solution.getResourceAsStream(inName);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Reader inValue = new InputStreamReader(input, "UTF-8");
        Writer outValue = new OutputStreamWriter(output, "UTF-8");
        Field inOverride = solution.getDeclaredField("inOverride");
        inOverride.setAccessible(true);
        inOverride.set(null, inValue);
        Field outOverride = solution.getDeclaredField("outOverride");
        outOverride.setAccessible(true);
        outOverride.set(null, outValue);
        Method main = solution.getDeclaredMethod("main", String[].class);
        main.setAccessible(true);
        main.invoke(null, new Object[] { new String[] {} });
        InputStream expectedStream = solution.getResourceAsStream(outName);
        Reader expectedReader = new InputStreamReader(expectedStream, "UTF-8");
        BufferedReader expected = new BufferedReader(expectedReader);
        byte[] resultByteArray = output.toByteArray();
        InputStream resultStream = new ByteArrayInputStream(resultByteArray);
        Reader resultReader = new InputStreamReader(resultStream, "UTF-8");
        BufferedReader result = new BufferedReader(resultReader);
        while (expected.ready() && result.ready()) {
            assertEquals(expected.readLine().trim(), result.readLine().trim());
        }
        assertEquals(expected.ready(), result.ready());
        inValue.close();
        outValue.close();
        expected.close();
        result.close();
    }
}
