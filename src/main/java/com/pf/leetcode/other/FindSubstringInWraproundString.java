package com.pf.leetcode.other;

public class FindSubstringInWraproundString {
    public static void main(String[] args) {
        FindSubstringInWraproundString findSubstringInWraproundString = new FindSubstringInWraproundString();
        findSubstringInWraproundString.findSubstringInWraproundString("zab");

    }
    public int findSubstringInWraproundString(String p) {
        int[] count = new int[26];
        int left = 0;
        int right = 0;
        while (right < p.length()) {
            int now = p.charAt(right) - 'a';
            if (left == right) {
                count[now] = Math.max(count[now], 1);
                right++;
                continue;
            }
            int pre = p.charAt(right - 1) - 'a';
            // 后面一个可以加上就继续
            if (now == pre + 1 || now == (pre + 1) % 26) {
                right++;
                continue;
            } else {
                // 不包含right,因为不满足
                while (left < right) {
                    now = p.charAt(left) - 'a';
                    count[now] = Math.max(count[now], right - left);
                    left++;
                }
            }
        }
        // 不包含right,因为不满足
        while (left < right) {
            int now = p.charAt(left) - 'a';
            count[now] = Math.max(count[now], right - left);
            left++;
        }
        int ans = 0;
        for (int i = 0; i < count.length; i++) {
            ans += count[i];
        }
        return ans;
    }
}
