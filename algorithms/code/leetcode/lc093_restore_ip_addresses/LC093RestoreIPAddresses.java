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
