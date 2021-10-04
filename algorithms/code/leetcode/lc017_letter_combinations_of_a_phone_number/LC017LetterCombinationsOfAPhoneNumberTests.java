package leetcode.lc017_letter_combinations_of_a_phone_number;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class LC017LetterCombinationsOfAPhoneNumberTests {
    @Test
    public void testNothing() throws Exception {
        LC017LetterCombinationsOfAPhoneNumber solution;
        solution = new LC017LetterCombinationsOfAPhoneNumber();
        List<String> result = solution.letterCombinations(null);
        assertEquals(0, result.size());
    }

    @Test
    public void testEmpty() throws Exception {
        LC017LetterCombinationsOfAPhoneNumber solution;
        solution = new LC017LetterCombinationsOfAPhoneNumber();
        List<String> result = solution.letterCombinations("");
        assertNotEquals(null, result);
        assertEquals(0, result.size());
    }

    @Test
    public void testExample() throws Exception {
        LC017LetterCombinationsOfAPhoneNumber solution;
        solution = new LC017LetterCombinationsOfAPhoneNumber();
        String[] expected = { "ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf" };
        Arrays.sort(expected);
        List<String> result = solution.letterCombinations("23");
        Collections.sort(result);
        assertNotEquals(null, result);
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.get(i));
        }
    }
}
