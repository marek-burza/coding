package leetcode.lc383_ransom_note;

/**
 * https://leetcode.com/problems/ransom-note/ #easy
 */
public final class LC383RansomNote {
    public boolean canConstruct(final String ransomNote, final String magazine) {
        int[] counts = new int[256];
        for (char letter : magazine.toCharArray()) {
            counts[letter]++;
        }
        for (char letter : ransomNote.toCharArray()) {
            if (--counts[letter] < 0) {
                return false;
            }
        }
        return true;
    }
}
