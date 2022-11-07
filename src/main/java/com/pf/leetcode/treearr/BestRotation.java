package com.pf.leetcode.treearr;

import java.util.ArrayList;
import java.util.List;

public class BestRotation {

    public static void main(String[] args) {
        BestRotation bestRotation = new BestRotation();
//        System.out.println(bestRotation.bestRotation(new int[]{2,3,1,4,0}));
        System.out.println(bestRotation.bestRotation(new int[]{1,3,0,2,4}));

    }

    public int bestRotation(int[] nums) {
        int len = nums.length;
        int[] bit = new int[len + 2];
        List<Integer>[] list = new ArrayList[2];

        for (int i = 0; i < len; i++) {
            int a = i - nums[i];

            if (a >= 0) {
                System.out.println("i:" + i +"[" + 0 + "," + (a + 1) + "]");
                update(bit, 0 + 1, 1);
                update(bit, a + 1 + 1, -1);
            }

            a = len + i - nums[i];
            a = Math.min(a, len - 1);
            if (a >= i + 1) {
                update(bit, i + 1 + 1, 1);
                update(bit, a + 1 + 1, -1);
                System.out.println("aaa i:" + i +"[" + (i + 1 + 1) + "," + (a + 1 + 1) + "]");
            }
        }

        int max = -1;
        int maxInd = -1;
        for (int i = 1; i < bit.length; i++) {
            int now = query(bit, i);
            if (now > max) {
                max = now;
                maxInd = i;
            }
        }
        return maxInd - 1;
    }

    /**
     * 更新树状数组的值
     * @param bit
     * @param x
     * @param val
     */
    private void update(int[] bit, int x, int val) {
        while (x < bit.length) {
            bit[x] += val;
            x += lowbit(x);
        }
    }

    /**
     * 查询树状数组的和
     * @param bit
     * @param x
     * @return
     */
    private int query(int[] bit, int x) {
        int sum = 0;
        while (x > 0) {
            sum += bit[x];
            x -= lowbit(x);
        }
        return sum;
    }
    /**
     * 计算偏移
     * @param x
     * @return
     */
    int lowbit(int x) {
        return x & (-x);
    }
}
