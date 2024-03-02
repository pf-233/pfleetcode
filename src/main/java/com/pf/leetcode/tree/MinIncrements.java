package com.pf.leetcode.tree;

/**
 * author：panf
 * date：2/28/2024
 * Description:
 */
public class MinIncrements {

    public static void main(String[] args) {
        MinIncrements minIncrements = new MinIncrements();
        int n;
        int[] cost;
        n = 7;
        cost = new int[] {1,5,2,2,3,3,1};
        System.out.println(minIncrements.minIncrements(n, cost));
    }

    int ans;
    int[] cost;
    public int minIncrements(int n, int[] cost) {
        ans = 0;
        this.cost = cost;
        minIncrements(1);
        return ans;
    }

    public int minIncrements(int parent) {
        int left = parent * 2;
        int right = left + 1;
        if (right > cost.length) return 0;

        int leftPath = minIncrements(left);
        int rightPath = minIncrements(right);
        int max = Math.max(leftPath, rightPath);
        int min = Math.min(leftPath, rightPath);
        ans += max - min;
        return max += cost[parent - 1];
    }
}
