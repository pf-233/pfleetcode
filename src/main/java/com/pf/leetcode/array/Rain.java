package com.pf.leetcode.array;

/**
 * @author pf
 * @date 2020-04-04 10:43
 **/
public class Rain {
    public static void main(String[] args) {
        trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
//        left:2 + 1right:10
//        rigth:9+1left:3
//        left:4 + 1right:7
//        left:5 + 2right:7
//        left:6 + 1right:7
    }



    public static int trap(int[] height) {
        //评论的双指针解法
        int left = 0;
        int right = height.length - 1;
        if (right == -1) {
            return 0;
        }
        int leftMax = height[left];
        int rightMax = height[right];
        int count = 0;
        while (left < right) {
            //当左边的当前高度比右边最高低的时候  左边开始移动
//            if(height[left] < rightMax){
            if (height[left] < height[right]) {
                //当当前的左边最大值比现在大就把当前的高度差计入
                if (height[left] < leftMax) {
                    count += leftMax - height[left];
                    System.out.println("left:" + left + " + " + (leftMax - height[left]) + "right:" + right);
                } else {
                    //更新左边的最大值
                    leftMax = height[left];
                }
                left++;
            } else {
                //移动右边
                if (height[right] < rightMax) {
                    count += rightMax - height[right];
                    System.out.println("rigth:" + right + "+" + (rightMax - height[right]) + "left:" + left);
                } else {
                    rightMax = height[right];
                }
                right--;
            }
        }
        return count;
    }



}
