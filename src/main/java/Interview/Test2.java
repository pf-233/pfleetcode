package Interview;

import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) {
        int[] nums = new int[]{0};
        System.out.println(getMaxDiff(nums));
        nums = null;
        System.out.println(getMaxDiff(nums));
        nums = new int[]{5, 6, -4, 1, 6};
        System.out.println(getMaxDiff(nums));
        nums = new int[]{5, 7, 4, 6, 5};
        System.out.println(getMaxDiff(nums));
        nums = new int[]{5, 4, 3, 2, 1};
        System.out.println(getMaxDiff(nums));
    }

    public static int getMaxDiff(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int minInd = 0;
        int min = nums[0];
        int[] ans = new int[]{0, -1};
        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - min;
            if (diff > max) {
                ans[0] = minInd;
                ans[1] = i;
                max = nums[i] - min;
            }
            if (nums[i] < min) {
                min = nums[i];
                minInd = i;
            }
        }
        System.out.println(ans[0] + " : " + ans[1]);
        return max;
    }
}
