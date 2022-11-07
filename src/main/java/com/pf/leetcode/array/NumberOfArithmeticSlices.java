package com.pf.leetcode.array;

public class NumberOfArithmeticSlices {
    public static void main(String[] args) {
        NumberOfArithmeticSlices numberOfArithmeticSlices = new NumberOfArithmeticSlices();
        numberOfArithmeticSlices.numberOfArithmeticSlices(new int[]{1,2,3,4});
    }
    public int numberOfArithmeticSlices(int[] nums) {
        int res = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            int diff = nums[i] - nums[i - 1];
            if (diff == nums[i + 1] - nums[i]) {
                int start = i - 1;
                int end = i + 1;
                while (end + 1 < nums.length && nums[end + 1] - nums[end] == diff) {
                    end++;
                }
                res += getCount(start, end == nums.length ? end - 1 : end);
            }
        }
        return res;
    }

    private int getCount(int start, int end) {
        //     int count = 0;
        //     for (int i = start; i <= end - 2; i++) {
        //         for(int j = end; j >= i + 2; j--) {
        //             count++;
        //         }
        //end - start - 2  >= 0 |  end - 1 - start - 2 >= 0 | end - (start + 1) - 2 >= 0
        //当end 时 start=i可以有 end - i >= 2 ===> i <= end - 2        因为包括0 所以个数+1
        //     }
        //     return count;
        // }
        int count = 0;
        for (int i = end - start; i >= 2; i--) {
            count += i - 1;
        }
        return count;
    }
}
