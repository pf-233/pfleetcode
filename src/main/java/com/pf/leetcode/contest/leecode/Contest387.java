package com.pf.leetcode.contest.leecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * author：panf
 * date：2/25/2024
 * Description:
 */
public class Contest387 {
    public static void main(String[] args) {
        Contest387 contest = new Contest387();
        int[] nums;
//        nums = new int[] {5,14,3,1,2};
        nums = new int[] {2,1,3,3};
        System.out.println(contest.resultArray(nums));
//        int[][] grid = new int[][] {
//                {1,2,2},
//                {1,1,0},
//                {0,1,1}
//        };
//        System.out.println(contest.minimumOperationsToWriteY(grid));
    }


    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] order = Arrays.copyOf(nums, n);
        Arrays.sort(order);
        Map<Integer, Integer> map = new HashMap<>();
        int pre = -1;
        int indOrder = 1;
        for (int i = 0; i < order.length; i++) {
            if (order[i] == pre) continue;
            map.put(order[i], indOrder++);
            pre = order[i];
        }

        int[] arr = new int[n];
        int[] bit1 = new int[indOrder];
        int[] arr2 = new int[n];
        int[] bit2 = new int[indOrder];
        int ind = 0;
        int ind2 = 0;
        arr[ind] = nums[0];
        insertNum(bit1, nums[0], map);
        arr2[ind2] = nums[1];
        insertNum(bit2, nums[1], map);
        for (int i = 2; i < n; i++) {
            int cnt = ind - greaterCount(bit1, nums[i], map);
            int cnt2 = ind2 - greaterCount(bit2, nums[i], map);
            if (cnt > cnt2) {
                arr[++ind] = nums[i];
                insertNum(bit1, nums[i], map);
            } else if (cnt < cnt2) {
                arr2[++ind2] = nums[i];
                insertNum(bit2, nums[i], map);
            } else if (cnt == cnt2) {
                if (ind > ind2) {
                    arr2[++ind2] = nums[i];
                    insertNum(bit2, nums[i], map);
                } else {
                    arr[++ind] = nums[i];
                    insertNum(bit1, nums[i], map);
                }
            }
        }
        for (int i = 0; i <= ind2; i++) {
            arr[i + ind + 1] = arr2[i];
        }
        return arr;
    }

    private int greaterCount(int[] bit, int target, Map<Integer, Integer> map) {
        return query(bit, map.get(target));
    }

    private void insertNum(int[] bit, int target, Map<Integer, Integer> map) {
        update(bit, 1, map.get(target));
    }

    void update(int[] bit, int val, int index) {
        int len = bit.length;
        if(index == 0) {
            return;
        }
        while(index < len) {
            bit[index] += val;
            index += lowbit(index);
        }
    }

    int query(int[] bit, int index) {
        int sum = 0;
        while(index > 0) {
            sum += bit[index];
            index -= lowbit(index);
        }
        return sum;
    }

    int lowbit(int x) {
        return x & (-x);
    }






    public int minimumOperationsToWriteY(int[][] grid) {
        int[] yarr = new int[3];
        int[] allarr = new int[3];
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isY(i, j, grid)) {
                    yarr[grid[i][j]]++;
                } else {
                    allarr[grid[i][j]]++;
                }
            }
        }

        int min = n * n;
        int ysum = yarr[0] + yarr[1] + yarr[2];
        int allsum = min - ysum;
        // y 用哪个
        for (int i = 0; i < 3; i++) {
            int temp = ysum - yarr[i];
            // 其他改成什么
            for (int j = 0; j < 3; j++) {
                if (i == j) continue;
                min = Math.min(min, allsum - allarr[j] + temp);
            }
        }
        return min;
    }

    private boolean isY(int row, int col, int[][] grid) {
        int n = grid.length;
        int midpos = n / 2;
        if (row >= midpos) {
            return col == midpos;
        } else {
            return midpos - row == Math.abs(col - midpos);
        }
    }
}
