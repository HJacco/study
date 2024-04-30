package com.study.codetop.first;

/**
 * 给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
 *
 * 有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
 *
 * 一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
 *
 * 1 <= xi.length <= 4
 * xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
 * 在 xi 中允许前导零。
 * 例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/validate-ip-address
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc468_ValidationIp {

    public static void main(String[] args) {
        new Lc468_ValidationIp().validIPAddress("12..33.4");
    }

    public String validIPAddress(String queryIP) {
        if (null == queryIP || queryIP.length() == 0) {
            return "Neither";
        }
        if (queryIP.indexOf(".") > -1 && queryIP.indexOf(":") > -1) {
            return "Neither";
        }
        if (validateIp4(queryIP)) {
            return "IPv4";
        }
        if (validateIp6(queryIP)) {
            return "IPv6";
        }
        return "Neither";
    }

    public boolean validateIp6(String ip) {
        String[] segments = ip.split(":");
        if (count(ip, ":") !=7 || segments.length != 8) {
            return false;
        }
        for (int i = 0; i < segments.length; i ++) {
            String seg = segments[i];
            if (seg.length() < 1 || seg.length() > 4) {
                return false;
            }
            for (int j = 0; j < seg.length(); j ++) {
                char ch = seg.charAt(j);
                if (!
                        (
                                (ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'f') || (ch >= 'A' && ch <= 'F')
                        )
                ) {
                    return false;
                }
            }
        }
        return true;
    }
    public int count(String str, String s) {
        int o = str.length();
        str = str.replaceAll(s, "");
        return o - str.length();
    }

    public boolean validateIp4(String ip) {
        String[] segments = ip.split("\\.");
        if (count(ip, "\\.") != 3 || segments.length != 4) {
            return false;
        }
        for (int i = 0; i < segments.length; i ++) {
            String seg = segments[i];
            if (seg.startsWith("0") && seg.length() > 1) {
                return false;
            }
            if (seg.length() == 0) {
                return false;
            }
            int n = 0;
            for (int j = 0; j < seg.length(); j ++) {
                char ch = seg.charAt(j);
                if (ch < '0' || ch > '9') {
                    return false;
                }
                n = n * 10 + (ch - '0');
                if (n > 255) {
                    return false;
                }
            }
        }
        return true;
    }
}
