package Interview;

import java.util.ArrayList;
import java.util.Arrays;

public class ByteDance {


//    算法题：15分钟
//  1、给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转，假设我们的环境只能存储得下 32 位的有符号整数，如果反转后整数溢出那么就返回 0，
//    示例1：
//    输入：123
//    输出：321
//    示例2：
//    输入：-123
//    输出：-321
//    示例3：
//    输入：210
//    输出：12
//
//
//    2、给定一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。 返回这三个数的和。 假定每组输入只存在恰好一个解。
//    示例 1：输入：nums = [-1,2,1,-4], target = 1输出：2解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
//    示例 2：输入：nums = [0,0,0], target = 1输出：0



    public static void main(String[] args) {
        int[] nums;
        int target = 1;
        nums = new int[]{0,0,0};
        System.out.println(closestSum(nums, target));
        nums = new int[]{-1,2,1,-4};
        System.out.println(closestSum(nums, target));
    }

    private static int closestSum(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int now = nums[i] + nums[left] + nums[right];
                sum = Math.abs(now - target) < Math.abs(sum - target) ? now : sum;
                if (now > target) {
                    right--;
                } else if (now == target) {
                    return target;
                } else  {
                    left++;
                }
            }
        }
        return sum;
    }

    private static int longestSubString(String s, int startL, int startR) {
        int ans = 0;
        while (startL >= 0 && startR <= s.length() - 1 && s.charAt(startL) == s.charAt(startR)) {
            ans = startR - startL + 1;
            startL--;
            startR++;
        }
        return ans;
    }
}
