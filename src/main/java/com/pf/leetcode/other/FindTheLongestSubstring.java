package com.pf.leetcode.other;

import java.util.HashSet;
import java.util.Set;

/**
 * @author pf
 * @date 2020-05-20 10:49
 **/
public class FindTheLongestSubstring {
    public static void main(String[] args) {
        System.out.println(findTheLongestSubstring("eleetminicoworoep"));
        System.out.println(findTheLongestSubstring("leetcodeisgreat"));
        System.out.println(findTheLongestSubstring("bcbcbc"));
    }

    //除了元音其他都为0
    static  int[] charValue = new int[26];
    static {
        //1   10  100  1000 10000
        charValue['a'-'a'] = 1;
        charValue['e'-'a'] = 2;
        charValue['i'-'a'] = 4;
        charValue['o'-'a'] = 8;
        charValue['u'-'a'] = 16;
    }
    public static int findTheLongestSubstring(String s) {
        System.out.println("========");
        char[] chs = s.toCharArray();
        int len = chs.length;
        //vals[0] = 0 其实可以匹配 最后一个0
        int[] vals = new int[len + 1];
        //存放所有可能的结果
        Set<Integer> integers = new HashSet<>();
        int max = 0;
        for(int i = 1; i <= len; i++){
            //1   10  100  1000 10000   异或就不会互相影响
            vals[i] = charValue[chs[i - 1] - 'a'] ^ vals[i - 1];
            System.out.println(vals[i]);
            integers.add(vals[i]);
            //获取前缀字串为0的
            if (vals[i] == 0){
                max = i;
            }
        }
        //最后为0就全部都是偶数可以直接返回  优化可以不要
        if (max == len){
            return  len;
        }
        //这边就获取两个相同数量的开始和结束位置  两者中间的就是元音为偶数的
        for (Integer val : integers){
            if (val == 0){
                continue;
            }
            int start = 1;
            int end = len;
            while (vals[start] != val){
                start++;
            }

            while (vals[end] != val){
                end--;
            }
            max = Math.max(max, end - start);
        }
        return max;
    }
}
