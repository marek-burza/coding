package leetcode.lc093_restore_ip_addresses;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC093RestoreIPAddressesTests {
    private String[] freeze(final List<String> list) {
        String[] result = new String[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    @Test
    public void test25525511135() throws Exception {
        String[] expected = { "255.255.11.135", "255.255.111.35" };
        String[] result = freeze(new LC093RestoreIPAddresses().restoreIpAddresses("25525511135"));
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }

    @Test
    public void test101023() throws Exception {
        String[] expected = { "1.0.10.23", "1.0.102.3", "10.1.0.23", "10.10.2.3", "101.0.2.3" };
        String[] result = freeze(new LC093RestoreIPAddresses().restoreIpAddresses("101023"));
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }
}
