package leetcode.lc093_restore_ip_addresses;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/restore-ip-addresses/
 * #medium
 */
public final class LC093RestoreIPAddresses {
    private void partial(final String s, final int count, final List<String> ip, final List<String> list) {
        if (s.length() < count || (s.charAt(0) == '0' && count > 1)) {
            return;
        }
        int part;
        String prefix;
        prefix = s.substring(0, count);
        part = Integer.parseInt(prefix);
        if (/* 0 <= part && */part <= 255) {
            ip.add(prefix);
            restore(s.substring(count), ip, list);
            ip.remove(ip.size() - 1);
        }
    }

    public void restore(final String s, final List<String> ip, final List<String> list) {
        if (ip.size() >= 4) {
            if (s.length() == 0) {
                StringBuilder string = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    if (0 < i) {
                        string.append(".");
                    }
                    string.append(ip.get(i));
                }
                list.add(string.toString());
            }
        } else {
            partial(s, 1, ip, list);
            partial(s, 2, ip, list);
            partial(s, 3, ip, list);
        }
    }

    public List<String> restoreIpAddresses(final String s) {
        List<String> ip = new ArrayList<>();
        List<String> list = new ArrayList<>();
        restore(s, ip, list);
        return list;
    }
}
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
