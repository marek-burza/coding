package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/restore-ip-addresses/
 */
public class LC093RestoreIPAddresses {
    private void partial(String s, int count, List<String> ip, List<String> list) {
        if (s.length() < count || (s.charAt(0) == '0' && count > 1)) {
            return;
        }
        int part;
        String prefix;
        prefix = s.substring(0, count);
        part = Integer.parseInt(prefix);
        if (0 <= part && part <= 255) {
            ip.add(prefix);
            restore(s.substring(count), ip, list);
            ip.remove(ip.size() - 1);
        }
    }

    public void restore(String s, List<String> ip, List<String> list) {
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

    public List<String> restoreIpAddresses(String s) {
        List<String> ip = new ArrayList<>();
        List<String> list = new ArrayList<>();
        restore(s, ip, list);
        return list;
    }

    public static void main(String[] arguments) {
        LC093RestoreIPAddresses restorer = new LC093RestoreIPAddresses();
        for (String ip : restorer.restoreIpAddresses("25525511135")) {
            System.out.println(ip);
        }
    }
}
