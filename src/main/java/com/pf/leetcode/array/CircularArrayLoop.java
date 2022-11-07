package com.pf.leetcode.array;

import sun.reflect.generics.tree.Tree;

import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

//https://leetcode-cn.com/problems/circular-array-loop/
public class CircularArrayLoop {
    public static void main(String[] args) {
        CircularArrayLoop circularArrayLoop = new CircularArrayLoop();
        int[] nums = new int[]{-2,1,-1,-2,-2};
//        System.out.println(circularArrayLoop.circularArrayLoop(nums));
//        nums = new int[]{-1,-2,-3,-4,-5};
        nums = new int[]{1,1,2};
//        System.out.println(circularArrayLoop.circularArrayLoop(nums));

//        System.out.println(circularArrayLoop.minSwaps("]]][[["));
        System.out.println(circularArrayLoop.longestObstacleCourseAtEachPosition(new int[]{5,1,5,5,1,3,4,5,1,4}));
        //[1,1,2,3,2,3,4,5,3,5]
    }

    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int len = obstacles.length;
        int[] ans = new int[len];
        ans[0] = 1;
        TreeSet<Integer> set = new TreeSet<Integer>((a, b) -> obstacles[a] == obstacles[b] ? a - b : obstacles[a] - obstacles[b]);
        set.add(0);
        for (int i = 1; i < len; i++) {
            Integer tmp = set.floor(i);
            if (tmp == null) {
                ans[i] = 1;
            } else {
                ans[i] = ans[tmp] + 1;
            }
            set.add(i);
        }
        return ans;
    }

    char le = '[';
    char ri = ']';

    public int minSwaps(String s) {
        if (s == null || s == "") {
            return 0;
        }

        Stack<Character> stack = new Stack();
        char[] chs = s.toCharArray();
        Character tmp = null;
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == le) {
                stack.push(le);
            } else {
                if (!stack.isEmpty()) {
                    tmp = stack.peek();
                    if (tmp == le) {
                        stack.pop();
                    } else {
                        stack.push(chs[i]);
                    }
                }
            }
        }

        int size = stack.size();
        return size % 4 == 0 ? size / 4 : size / 4 + 1;
    }
    // 数据定了的时候循环已经定了只有在一个循环里的才会走到一起
    public boolean circularArrayLoop(int[] nums) {
        int len = nums.length;
        int start = 0;
        int count = 0;
        int pre = start;
        boolean res = false;
        int cicleLen = 0;
        while (count < len) {
            int a = nums[pre];
            int index = (pre + a + len) % len;
            int b = nums[index];
            // 标记已经走过了
            nums[pre] = 0;
            count++;
            cicleLen++;
            pre = index;
            // 不是全正或负就不是，要重新设置起点
            // 0 是 =  不可能在这种情况里
            if (Math.abs(a + b) < Math.max(Math.abs(a), Math.abs(b))) {
                start = index;
                pre = start;
                cicleLen = 0;
                continue;
            }
            // 循环了
            if (nums[index] == 0) {
                if (cicleLen > 1) {
                    res = true;
                    break;
                }
                // 长度等于0的时候不算要继续,找到下一个start
                cicleLen = 0;
                while (count < len && nums[index] == 0) {
                    index = ++index % len;
                }
                start = index;
                pre = start;
            }
        }
        return res;
    }



}
