package com.pf.leetcode.contest.leecode;

/**
 * author：panf
 * date：2/25/2024
 * Description:
 */
public class Contest386 {
    public static void main(String[] args) {
        Contest386 contest = new Contest386();
        int[] nums;
        int[] changeIndices;
//        nums = new int[] {2,2,0};
//        changeIndices = new int[] {2,2,2,2,3,2,2,1};
//        nums = new int[] {0,2,3,0};
//        changeIndices = new int[] {2,4,1,3,3,3,3,3,3,2,1};
//        System.out.println(contest.earliestSecondToMarkIndices(nums, changeIndices));

//        int[][] bottomLeft;
//        int[][] topRight;
//        bottomLeft = new int[][] {
//                {3,1},
//                {5,7},
//                {1,5}
//        };
//        topRight = new int[][] {
//                {9,6},
//                {6,9},
//                {8,7}
//        };
//        System.out.println(contest.largestSquareArea(bottomLeft, topRight));
    }

    int[] nums;
    int[] changeIndices;
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        this.nums = nums;
        this.changeIndices = changeIndices;
        long sum = 0;
        int n = nums.length;
        int m = changeIndices.length;
        for (int i = 0; i < n; i++) sum += nums[i];
        if (sum + n > m) return -1;

        int min = (int)sum + n;
        for (int i = min - 1; i < m; i++) {
            if (can(i)) return i + 1;
        }
        return -1;
    }

    private boolean can(int ind) {
        int needCount = 0;
        int markCount = 0;
        boolean[] markArr = new boolean[nums.length];
        for (int i = ind; i >= 0; i--) {
            int index = changeIndices[i] - 1;
            if (!markArr[index]) {
                markArr[index] = true;
                needCount = Math.max(needCount, 0) + nums[index];
                markCount++;
            } else {
                needCount--;
            }
        }
        return needCount <= 0 && markCount == nums.length;
    }

    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        long max = 0;
        int len = bottomLeft.length;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {

                long distancex = getArea(bottomLeft[i][0], topRight[i][0], bottomLeft[j][0], topRight[j][0]);
                long distancey = getArea(bottomLeft[i][1], topRight[i][1], bottomLeft[j][1], topRight[j][1]);
                long min = Math.min(distancex, distancey);
                max = Math.max(min * min, max);
            }
        }
        return max;
    }

    private long getArea(int a, int b, int c, int d) {
        if (a >= d || b <= c) return 0L;
        long ans = 0L;
        if (a <= c) {
            ans += Math.min(b, d) - c;
        } else {
            ans += Math.min(b, d) - a;
        }
        return ans;
    }
}
