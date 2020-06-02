package com.pf.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pf
 * @date 2020-05-02 22:34
 **/
public class Test {

    public static void main(String[] args) {
//        List<Boolean> resList = kidsWithCandies(new int[]{12,1,12}, 10);
//        List<Boolean> resList = kidsWithCandies(new int[]{}, 10);
//        System.out.println(resList);

//        System.out.println(checkIfCanBreak("abc", "xya"));
//        System.out.println(checkIfCanBreak("abe", "acd"));
//        System.out.println(checkIfCanBreak("leetcodee", "interview"));
//        System.out.println(checkIfCanBreak("szy", "cid"));
//        System.out.println(maxDiff(123456));
//        System.out.println(maxDiff(10000));
        System.out.println(longestSubarray(new int[]{8,2,4,7}, 4));
        System.out.println(longestSubarray(new int[]{10,1,2,4,7,2}, 5));
        System.out.println(longestSubarray(new int[]{4,2,2,2,4,4,2,2}, 0));
        System.out.println(longestSubarray(new int[]{2,5,2}, 9));
    }

    public  static int longestSubarray(int[] nums, int limit) {
        int maxCount = 1;

        int max = nums[0];
        int min = nums[0];
        int maxIndex = 0;
        int minIndex = 0;
        int temp = 0;
        int i = 1;
        int count = 1;
        for(; i < nums.length; i++){
            if(max < nums[i]){
                max = nums[i];
                maxIndex = i;
                if(max - min > limit){
                    temp = i;
                    int tempMinIndex = i;
                    min = max;
                    while(temp > minIndex && max - nums[temp] <= limit){
                        if (min > nums[temp]){
                            min = nums[temp];
                            tempMinIndex = temp;
                        }
                        temp--;
                    }
                    count = i - temp - 1;
                    minIndex = tempMinIndex;
                }
            } else if(min > nums[i]){
                min = nums[i];
                minIndex = i;
                if(max - min > limit){
                    temp = i;
                    int tempMaxIndex = i;
                    max = min;
                    while(temp > maxIndex && nums[temp] - min <= limit){
                        if (max < nums[temp]){
                            max = nums[temp];
                            tempMaxIndex = temp;
                        }
                        temp--;
                    }
                    count = i - temp - 1;
                    maxIndex = tempMaxIndex;
                }
            }
            count++;
            maxCount = maxCount >  count ? maxCount :  count;
        }
        return maxCount > count ? maxCount :  count;
    }

    public static int maxDiff(int num) {
        if(num < 10){
            return 8;
        }
        int[] arr = new int[8];
        int[] arrRes = new int[8];
        int top = 0;
        while(num > 0){
            arr[top++] = num % 10;
            num/= 10;
        }

        int len = top;
        for(int i = 0; i < top; i++){
            arrRes[i] = arr[top - 1 - i];
        }

        int a = 0;
        int b = 0;
        int[] tempMax = getMax(arrRes);
        int[] tempMin = getMin(arrRes);
        for(int i = 0; i < len; i++){
            a = a * 10 + tempMax[i];
        }
        for(int i = 0; i < len; i++){
            b = b * 10 + tempMin[i];
        }
        return a - b;
//        int temp1 = a - b;

//        tempMin = getMin(arrRes);
//        tempMax = getMax(tempMin);
//        a = 0;
//        b = 0;
//        for(int i = 0; i < len; i++){
//            a = a * 10 + tempMax[i];
//        }
//        for(int i = 0; i < len; i++){
//            b = b * 10 + tempMin[i];
//        }
//        return Math.max(temp1, a - b);
    }

    static int[] getMax(int[] arrRes){
        int temp = 0;
        int index = 0;
        int a = 0;
        int[] res = new int[arrRes.length];
        for(int i = 0; i < arrRes.length; i++){
            res[i] = arrRes[i];
        }
        if(res[0] == 9){
            while(index < arrRes.length && res[index] == 9){
                a = a * 10 + res[index];
                index++;
            }
        }
        temp = index < arrRes.length ? res[index] : 9;
        for(int i = index; i < arrRes.length; i++){
            if(res[i] == temp){
                res[i] = 9;
            }
        }
        return res;
    }

    static int[] getMin(int[] arrRes){
        int temp = 0;
        int index = 0;
        int[] res = new int[arrRes.length];
        for(int i = 0; i < arrRes.length; i++){
            res[i] = arrRes[i];
        }
        if(res[0] == 1){
            index = 1;
            while(index < arrRes.length && res[index] == 0){
                index++;
            }
            temp = index < arrRes.length ? res[index] : 0;
            for(int i = index; i < arrRes.length; i++){
                if(res[i] == temp){
                    res[i] = 0;
                }
            }
        } else {
            temp = res[0];
            for(int i = 0; i < arrRes.length; i++){
                if(res[i] == temp){
                    res[i] = 1;
                }
            }
        }
        return res;
    }

    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        List<Boolean> resList = new ArrayList();
        for(int i = 0; i < candies.length; i++){
            max = Math.max(max, candies[i]);
        }

        for(int i = 0; i < candies.length; i++){
            resList.add(candies[i] + extraCandies >= max);
        }
        return resList;
    }

    public static  boolean checkIfCanBreak(String s1, String s2) {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();

        int[] count1 = new int[26];
        int[] count2 = new int[26];
        int i = 0;
        for(; i < ch1.length; i++){
            count1[ch1[i] - 'a']++;
        }
        for(i = 0; i < ch2.length; i++){
            count2[ch2[i] - 'a']++;
        }

        i = 0;
        int sum1 = 0;
        int sum2 = 0;
        while(sum1 >= sum2 && i < 26){
            sum1+= count1[i];
            sum2+= count2[i];
            i++;
        }
        if(i < 26){
            sum1 = 0;
            sum2 = 0;
            i = 0;
            while(sum1 <= sum2 && i < 26){
                sum1+= count1[i];
                sum2+= count2[i];
                i++;
            }
            return i == 26;
        }
        return true;
    }
}
