package com.pf.leetcode.contest.leecode;

import java.util.PriorityQueue;

public class Contest288 {
    public static void main(String[] args) {
        Contest288 contest288 = new Contest288();
//        System.out.println(contest288.minimizeResult("247+38"));
        System.out.println(contest288.maximumProduct(new int[]{0,4}, 5));
    }

    // res = a * other => Math.max(（ai + 1） * res / ai)
    // ==>  Math.max(res + res / ai); ai 越小越好
    static int mode = (int)1e9 + 7;
    public int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> que = new PriorityQueue();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i]++;
                k--;
            }
            que.offer(nums[i]);
        }
        if (k < 0) {
            return 0;
        }

        while (k > 0) {
            int min = que.poll();
            que.offer(min + 1);
            k--;
        }
        long sum = 1;
        while(que.isEmpty()) {
            sum *= que.poll();
            sum %= mode;
        }
        return (int)sum;
    }

    public String minimizeResult(String expression) {
        int ind = expression.indexOf('+');
        int left = ind - 1;
        int right = ind + 1;
        int min = Integer.MAX_VALUE;
        String res = "";
        for (int i = left; i >= 0; i--) {
            int pre1 = left == 0 ? 1 : Integer.valueOf(expression.substring(0, left));
            int pre2 = Integer.valueOf(expression.substring(left, ind));
            for (int j = right; j < expression.length(); j++) {
                int after1 =Integer.valueOf(expression.substring(ind + 1, right + 1));
                int after2 = right == expression.length() - 1 ? 1 : Integer.valueOf(expression.substring(right + 1, expression.length()));
                int val = pre1 * (after1 + pre2) * after2;
                if (val < min) {
                    min = val;
                    String tmp1 = left == 0 ? "" : expression.substring(0, left);
                    String tmp2 = expression.substring(left, right + 1);
                    String tmp3 = right == expression.length() - 1 ? "" : expression.substring(right + 1, expression.length());
                    res = tmp1 + "(" + tmp2 + ")" + tmp3;
                }
            }
        }
        return res;
    }
    public int largestInteger(int num) {
        String str = num + "";
        int[] count = new int[10];
        int tmp = num;
        char[] chs = str.toCharArray();
        while (tmp > 0) {
            count[tmp % 10]++;
            tmp /= 10;
        }
        int[] inds = new int[]{8, 9};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chs.length; i++) {
            int ind = chs[i] - '0' & 1;
            while (inds[ind] >=0 && count[inds[ind]] == 0) {
                inds[ind] -= 2;
            }
            sb.append(inds[ind]);
            count[inds[ind]]--;
        }
        return Integer.valueOf(sb.toString());
    }
}
