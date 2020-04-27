package com.pf.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * 最大整除子集
 *
 * @author pf
 * @date 2020-04-22 10:35
 **/
public class LargestDivisibleSubset {
    public static void main(String[] args) {
//        List<Integer> list = largestDivisibleSubset(new int[]{1,2,3});
//        System.out.println(list);

        System.out.println(waysToChange(5));
        System.out.println(waysToChange(7));
        System.out.println(waysToChange(10));
        System.out.println(waysToChange(14));
        System.out.println(waysToChange(15));
        System.out.println(waysToChange(61));
    }

    public static int waysToChange(int n) {
        int mode = 1000000007;
        if(n < 5){
            return 1;
        }
        LinkedList linkedList = new LinkedList();
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        //这里是组合5+5 -> 1*5+5 和 5+ 1*5 这种只算一种
        for(int i=2; i<=n;i++){
            // 不能整除就是只能用1  可以整除就是可以用11111   和 5
            dp[i] = i % 5 > 0 ? dp[i-1] : (dp[i-1] * 2) % mode;
            //dp[i-10] 的时候的情况 多一种可能i-10 之前的都可能 然后后面的是一个10
            dp[i]+= i % 10 > 0 ? 0 : (dp[i-10]) % mode;
            //dp[i-25] 的时候的情况 多一种可能 i-25 之前的都可能 然后后面的是一个25
            dp[i]+= i % 25 > 0 ? 0 : (dp[i-25]) % mode;
        }
        return dp[n];
    }
    /**
     * 给出一个由无重复的正整数组成的集合，找出其中最大的整除子集，子集中任意一对 (Si，Sj) 都要满足：Si % Sj = 0 或 Sj % Si = 0。
     *
     * 如果有多个目标子集，返回其中任何一个均可。
     *
     * 输入: [1,2,3]
     * 输出: [1,2] (当然, [1,3] 也正确)
     * @param nums
     * @return
     */
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        LinkedList<Integer> list = new LinkedList<>();
        if (nums.length == 0){
            return list;
        }
        Arrays.sort(nums);
        int[] parent = new int[nums.length];
        int[] count = new int[nums.length];
        parent[0] = 0;
        count[0] = 1;
        int max = 1;
        int index = 0;
        for (int i = 1; i < parent.length; i++){
            parent[i] = i;
            count[i] = 1;
            int tempMax = 0;
            int tempIndex = i;
            for (int j = i-1; j >= 0; j--){
                if (nums[i] % nums[j] == 0){
                    if (tempMax < count[j]){
                        tempIndex = j;
                        tempMax = count[j];
                    }
                }
            }
            if (tempIndex != i){
                parent[i] = tempIndex;
                count[i]+= count[tempIndex];
            }
            if(max < count[i]){
                max = count[i];
                index = i;
            }
        }
        while (parent[index] != index){
            list.addFirst(nums[index]);
            index = parent[index];
        }
        list.addFirst(nums[index]);
        return list;
    }
}
