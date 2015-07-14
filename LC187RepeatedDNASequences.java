package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/repeated-dna-sequences/
 */
public class LC187RepeatedDNASequences {
    public int compress(char nucleotide) {
        switch (nucleotide) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
        }
        return -1;
    }

    public int encode(int sequence, int compressed) {
        return (compressed << 18) | (sequence >> 2);
    }

    public String decode(int sequence) {
        StringBuffer decoded = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            int nucleotide = sequence & 0x3;
            switch (nucleotide) {
                case 0:
                    decoded.append('A');
                    break;
                case 1:
                    decoded.append('C');
                    break;
                case 2:
                    decoded.append('G');
                    break;
                case 3:
                    decoded.append('T');
                    break;
            }
            sequence >>= 2;
        }
        return decoded.toString();
    }

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> list = new ArrayList<>();
        if (s == null || s.length() < 10) {
            return list;
        }
        Set<Integer> seen = new HashSet<>();
        int now = 0;
        for (int i = 0; i < 9; i++) {
            now = encode(now, compress(s.charAt(i)));
        }
        for (int i = 9; i < s.length(); i++) {
            now = encode(now, compress(s.charAt(i)));
            if (seen.contains(now | 0xFFF00000)) {
                continue;
            }
            if (seen.contains(now)) {
                seen.remove(now);
                seen.add(now | 0xFFF00000);
                continue;
            }
            seen.add(now);
        }
        for (int sequence : seen) {
            if (sequence < 0) {
                list.add(decode(sequence & 0xFFFFF));
            }
        }
        return list;
    }

    public static void main(String[] arguments) {
        LC187RepeatedDNASequences solution = new LC187RepeatedDNASequences();
        System.out.println(solution.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
}
