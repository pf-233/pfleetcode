package com.pf.leetcode.contest.leecode;

import sun.reflect.generics.tree.Tree;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * author：panf
 * date：2023/12/17
 * Description:
 */
public class Contest376 {
    public static void main(String[] args) {
        Contest376 contest376 = new Contest376();
        int[] nums = new int[]{1,2,3,4,5,6};
        System.out.println(contest376.minimumCost(nums));
//        int[] nums;
//        int k;
//        nums = new int[] {1,3,4,8,7,9,3,5,1};
//        k = 2;
//        int[][] ans = contest376.divideArray(nums, k);
//        System.out.println("=======");
    }
    int[] a;
    int n;

    private boolean f(int x) {
        if (x < 0) {
            return false;
        }
        int temp = x;
        int reverse_x = 0;
        while (temp > 0) {
            reverse_x = reverse_x * 10 + temp % 10;
            temp /= 10;
        }
        return x == reverse_x;
    }

    public long minimumCost(int[] a) {
        this.a = a;
        int n = a.length;
        this.n = n;
        Arrays.sort(a);
        long res = 0;
        int m = 0;
        if (n % 2 == 1) m = a[n / 2];
        else    m = a[n / 2] + a[n / 2] >> 1;

        if (f(m)) {
            for (int i = 0; i < n; i ++)
                res += Math.abs(a[i] - m);
            return res;
        }
        int m1 = 0, m2 = 0;
        for (int i = m; i >= 0; i --) {
            if (f(i)) {
                m1 = i;
                break;
            }
        }
        for (int i = m; ; i ++) {
            if (f(i)) {
                m2 = i;
                break;
            }
        }
        long s1 = 0, s2 = 0;
        for (int i = 0; i < n; i ++)
            s1 += Math.abs(a[i] - m1);
        for (int i = 0; i < n; i ++)
            s2 += Math.abs(a[i] - m2);
        return Math.min(s1, s2);
    }
    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int[][] ans = new int[n / 3][3];
        int top = 0;
        PriorityQueue<Integer> que = new PriorityQueue();
        for (int i = 0; i < n; i++) {
            while (!que.isEmpty() && que.peek() + k < nums[i]) {
                if (que.size() < 3) {
                    return new int[0][0];
                }
                // 每次把小的排出去，后续的都是更大的数，大的数字更容易满足条件
                ans[top++] = new int[]{que.poll(), que.poll(), que.poll()};
            }
            que.offer(nums[i]);
        }
        while (!que.isEmpty()) {
            // 每次把小的排出去，后续的都是更大的数，大的数字更容易满足条件
            ans[top++] = new int[]{que.poll(), que.poll(), que.poll()};
        }
        return ans;
    }
}
