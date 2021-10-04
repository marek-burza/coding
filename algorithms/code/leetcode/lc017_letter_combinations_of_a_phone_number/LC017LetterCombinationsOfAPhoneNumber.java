package leetcode.lc017_letter_combinations_of_a_phone_number;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public final class LC017LetterCombinationsOfAPhoneNumber {
    public List<String> letterCombinations(final String digits) {
        if (null == digits || digits.length() == 0) {
            return new ArrayList<>();
        }
        String[] mapping = { " ", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        List<String> mapped = new ArrayList<>();
        mapped.add("");
        for (char digit : digits.toCharArray()) {
            int index = digit - '0';
            List<String> longer = new ArrayList<>();
            for (Iterator<String> previous = mapped.iterator(); previous.hasNext();) {
                String prefix = previous.next();
                for (char suffix : mapping[index].toCharArray()) {
                    longer.add(prefix + suffix);
                }
            }
            mapped = longer;
        }
        return mapped;
    }
}
