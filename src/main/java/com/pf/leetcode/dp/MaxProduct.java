package com.pf.leetcode.dp;

/**
 * @author pf
 * @date 2020-05-18 13:55
 **/
public class MaxProduct {
    public static void main(String[] args) {
//        System.out.println(maxProduct(new int[]{-2, 0, -2, 1, 2,3, -2}));
//        System.out.println(maxProduct(new int[]{2, -2, 2, -2, 2, -3,2}));
        System.out.println(maxProduct(new int[]{2, -2, 2, -2, 2, -3,2}));
        System.out.println(maxProduct(new int[]{-2}));
        System.out.println(maxProduct(new int[]{2, -2}));

    }

//    public static int maxProduct(int[] nums) {
//        int len = nums.length;
//        if (len == 0){
//            return 0;
//        }
//        int max = nums[0];
//        int fuCount = 0;
//        int firstFuIndex = -1;
//        int endFuIndex = -1;
//        int[] mulArr = new int[len + 1];
//        mulArr[0] = 1;
//        for (int i = 1; i <= len; i++){
//            mulArr[i] = mulArr[i - 1] * nums[i - 1];
//            max = Math.max(max, mulArr[i]);
//            if (nums[i - 1] == 0){
//                int tempMax = cal(i - 1, mulArr, fuCount, firstFuIndex, endFuIndex);
//                tempMax = tempMax > 0 ? tempMax : 0;
//                max = Math.max(max, tempMax);
//                mulArr[i] = 1;
//                firstFuIndex = -1;
//                endFuIndex = -1;
//                fuCount = 0;
//            } else if (nums[i - 1] < 0){
//                fuCount++;
//                firstFuIndex = firstFuIndex == -1 ? i :firstFuIndex;
//                endFuIndex = i;
//            }
//        }
//        return max;
//    }
//
//    public static int cal(int end, int[] mulArr, int fuCount, int firstFuIndex, int endFuIndex){
//        if (end == 0){
//            return 1;
//        }
//        if ((fuCount & 1) == 0){
//            return mulArr[end];
//        } else {
//            int temp1 = mulArr[endFuIndex - 1];
//            int temp2 = mulArr[end] / mulArr[firstFuIndex];
//            return temp1 > temp2 ? temp1 : temp2;
//        }
//    }


    public static int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 0){
            return 0;
        }
        //整数 等于0的不能参与计算否则都为0
        //绝对值是越来越大的, 所以就是 fuCount 为负数的个数  fuCount % 2 == 0 就全部 *
        //fuCount % 2 == 1 就要比较是分为两段的负数count % 2 == 0的大小
        int[][] dp = new int[len + 1][2];
        int max = nums[0];
        for (int i = 1; i <= len; i++){
            int tempMax = nums[i - 1];
            //大于0的时候
            if (nums[i - 1] > 0){
                //如果前面存在 > 0 就 * 否则就是当前的
                dp[i][0] = dp[i - 1][0] == 0 ? nums[i - 1] : dp[i - 1][0] * nums[i - 1];
                //如果前面存在 < 0 就 * 否则就继续不存在
                dp[i][1] = dp[i - 1][1] == 0 ? 0 : dp[i - 1][1] * nums[i - 1];
            } else if (nums[i - 1] == 0){
                //全部重新开始计算都设为0
                dp[i][0] = 0;
                dp[i][1] = 0;
            } else {
                //前面 有 < 0 就 * （负负得正）  没有就给 0 表示这之后没有正数
                dp[i][0] = dp[i - 1][1] == 0 ? 0 : dp[i - 1][1] * nums[i - 1];
                //前面 没有 > 0 就 给当前的负数    有就 正数 * 当前的负数
                dp[i][1] = dp[i - 1][0] == 0 ? nums[i - 1] : dp[i - 1][0] * nums[i - 1];
            }
            System.out.println(nums[i - 1] + ":::" + dp[i][0] + ":::" + dp[i][1]);
            //当前不为0的情况下 如果正数为0 说明之前没有正数[-2] 或者 之前没有负数这次变成负数了（之前有max所以没关系）
            if (nums[i - 1] != 0){
                tempMax = dp[i][0] == 0 ? dp[i][1] : dp[i][0];
            }
            max = Math.max(max, tempMax);
        }
        return max;
    }

}
