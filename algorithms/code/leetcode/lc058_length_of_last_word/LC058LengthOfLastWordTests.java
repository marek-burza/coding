package leetcode.lc058_length_of_last_word;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC058LengthOfLastWordTests {
    @Test
    public void testHelloWorld() throws Exception {
        assertEquals(5, new LC058LengthOfLastWord().lengthOfLastWord("Hello World"));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new LC058LengthOfLastWord().lengthOfLastWord(null));
        assertEquals(0, new LC058LengthOfLastWord().lengthOfLastWord(""));
    }

    @Test
    public void testAlmostNothing() throws Exception {
        assertEquals(0, new LC058LengthOfLastWord().lengthOfLastWord(" "));
    }

    @Test
    public void testTrailingSpace() throws Exception {
        assertEquals(5, new LC058LengthOfLastWord().lengthOfLastWord("Hello World  "));
    }

    @Test
    public void testSingleWord() throws Exception {
        assertEquals(10, new LC058LengthOfLastWord().lengthOfLastWord("HelloWorld"));
    }
}
