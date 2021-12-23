package leetcode.lc165_compare_version_numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC165CompareVersionNumbersTests {
    @Test
    public void test1And1() throws Exception {
        assertEquals(0, new LC165CompareVersionNumbers().compareVersion("1", "1"));
    }

    @Test
    public void test1And1Point0() throws Exception {
        assertEquals(0, new LC165CompareVersionNumbers().compareVersion("1", "1.0"));
    }

    @Test
    public void test2And1() throws Exception {
        assertEquals(1, new LC165CompareVersionNumbers().compareVersion("2", "1"));
    }

    @Test
    public void test1And13Point1() throws Exception {
        assertEquals(-1, new LC165CompareVersionNumbers().compareVersion("1", "13.1"));
    }

    @Test
    public void test1Point0Point1And1() throws Exception {
        assertEquals(1, new LC165CompareVersionNumbers().compareVersion("1.0.1", "1"));
    }
}
