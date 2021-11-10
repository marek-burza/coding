package leetcode.lc345_reverse_vowels_of_a_string;

/**
 * https://leetcode.com/problems/reverse-vowels-of-a-string/ #easy
 */
public final class LC345ReverseVowelsOfAString {
    private boolean isVowel(final char letter) {
        char[] vowels = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };
        for (char vowel : vowels) {
            if (vowel == letter) {
                return true;
            }
        }
        return false;
    }

    public String reverseVowels(final String s) {
        char[] text = s.toCharArray();
        for (int a = 0, z = text.length - 1; a < z;) {
            while (a < text.length && !isVowel(text[a])) {
                a++;
            }
            while (z >= 0 && !isVowel(text[z])) {
                z--;
            }
            if (a < z) {
                char exchange = text[a];
                text[a] = text[z];
                text[z] = exchange;
                a++;
                z--;
            }
        }
        return new String(text);
    }
}
