package com.pf.leetcode.easy;

import lombok.Data;


import java.util.*;

/**
 * 第一页
 *
 * @author pf
 * @date 2020-01-23 19:33
 **/
public class FirstPage {
    public static void main(String[] args) {
//        int[] aa = twoSum(new int[]{2, 7, 11, 15}, 9);
//        System.out.println(aa);
//        System.out.println(addTwoNumbers());

//        System.out.println(lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(lengthOfLongestSubstring("pwwkew"));
//        System.out.println(lengthOfLongestSubstring("bbbbb"));

//        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
//        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
//        System.out.println(findMedianSortedArrays(new int[]{3, 4}, new int[]{1, 2}));


//        System.out.println(longestPalindrome(""));

//        System.out.println(convert("A", 1));

//        System.out.println(reverse(-123));
//        System.out.println(reverse(1463847412));
//        System.out.println(2147483621 > Integer.MAX_VALUE);

//        System.out.println(myAtoi(""));

//        System.out.println(isPalindrome(121));

//        System.out.println(isMatch2("aa", "a*"));

//        System.out.println(intToRoman(9));

//        System.out.println(longestCommonPrefix("flower","flow","flight"));
//        System.out.println(longestCommonPrefix("dog","racecar","car"));

//        int[] nums = {-1, 0, 1, 2, -1, -4};
//        System.out.println(threeSum(nums));
//        int[] nums1 = {3, 0, -2, -1, 1, 2};
//        System.out.println(threeSum(nums1));
//        int[] nums2 = {6,-5,-6,-1,-2,8,-1,4,-10,-8,-10,-2,-4,-1,-8,-2,8,9,-5,-2,-8,-9,-3,-5};
//        System.out.println(threeSum(nums2));

//        int[] nums = {-1, 2, 1, -4};
//        System.out.println(threeSumClosest(nums, 1));

//        int[] nums = {1,0,-1,0,-2,2};
//        int[] nums = {-1,0,-5,-2,-2,-4,0,1,-2};
//        int target = -9;
//        System.out.println(fourSum(nums, target));
//        ListNode listNode1 = new ListNode(1);
//        ListNode listNode2 = new ListNode(2);
//        ListNode listNode3 = new ListNode(3);
//        ListNode listNode4 = new ListNode(4);
//        ListNode listNode5 = new ListNode(5);
//        listNode1.next=listNode2;
//        listNode2.next=listNode3;
//        listNode3.next=listNode4;
//        listNode4.next=listNode5;
//        removeNthFromEnd(listNode1, 2);

//        ListNode[] listNodes = new ListNode[3];
//        ListNode listNode11 = new ListNode(1);
//        ListNode listNode12 = new ListNode(4);
//        ListNode listNode13 = new ListNode(5);
//        listNode11.next=listNode12;
//        listNode12.next=listNode13;
//        ListNode listNode21 = new ListNode(1);
//        ListNode listNode22 = new ListNode(3);
//        ListNode listNode23 = new ListNode(4);
//        listNode21.next=listNode22;
//        listNode22.next=listNode23;
//        ListNode listNode31 = new ListNode(2);
//        ListNode listNode32 = new ListNode(6);
//        listNode31.next=listNode32;
//        listNodes[0]=listNode11;
//        listNodes[1]=listNode21;
//        listNodes[2]=listNode31;
//
//        ListNode[] listNodes4 = new ListNode[1];
//        ListNode listNode41 = null;
//        listNodes4[0]=listNode41;
//
//        ListNode[] listNodes2 = new ListNode[2];
//        ListNode listNode51 = new ListNode(1);
//        ListNode listNode52 = new ListNode(0);
//        listNodes2[0]=listNode51;
//        listNodes2[1]=listNode52;

//        int[] tempParam = new int[]{-6,-6,-4,2};
//        ListNode listNode1 = createParam(tempParam);
//        tempParam = new int[]{-10,-5,0,1,1,1,1,3,3};
//        ListNode listNode2 = createParam(tempParam);
//        tempParam = new int[]{-10,-10,-4,-3,0,1,1};
//        ListNode listNode3 = createParam(tempParam);
//        tempParam = new int[]{-10,-6,-6,-5,-4,-4,-3,3,4};
//        ListNode listNode4 = createParam(tempParam);
//        ListNode listNode5 = null;
//        tempParam = new int[]{-5,-5,-3,2,2};
//        ListNode listNode6 = createParam(tempParam);
//        tempParam = new int[]{0};
//        ListNode listNode7 = createParam(tempParam);
//        tempParam = new int[]{-10,-7,-6,-2,-1,1,1,2};
//        ListNode listNode8 = createParam(tempParam);
//
//        ListNode[] listNode = new ListNode[8];
//        listNode[0]=listNode1;
//        listNode[1]=listNode2;
//        listNode[2]=listNode3;
//        listNode[3]=listNode4;
//        listNode[4]=listNode5;
//        listNode[5]=listNode6;
//        listNode[6]=listNode7;
//        listNode[7]=listNode8;
//        mergeKLists(listNode);

//        int[] tempParam = new int[]{1,2,3,4};
//        ListNode listNode = createParam(tempParam);
//        swapPairs(listNode);
//        int[] tempParam = new int[]{1,2,3,4,5};
//        ListNode listNode = createParam(tempParam);
//        reverseKGroup(listNode ,2);

        divide(10,3);
    }

    /**
     * 两数相加
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap(nums.length);
        for (int i = 0; i < nums.length; i++) {
            Integer temp = numMap.get(target - nums[i]);
            if (temp != null) {
                return new int[]{temp, i};
            }
            numMap.put(nums[i], i);
        }
        return null;
    }

    /**
     * @return com.pf.leetcode.easy.FirstPage.ListNode
     * @Description:两数之和
     * @创建人 pf
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode first = null;
        ListNode next = null;
        ListNode temp = null;
        ListNode temp1 = null;
        ListNode temp2 = null;
        ListNode zero = new ListNode(0);
        int sum = 0;
        boolean flag = l1 != null || l2 != null;
        temp1 = l1 == null ? zero : l1;
        temp2 = l2 == null ? zero : l2;
        while (flag) {
            sum += temp1.val + temp2.val;
            if (sum >= 10) {
                temp = new ListNode(sum % 10);
                sum = 1;
            } else {
                temp = new ListNode(sum);
                sum = 0;
            }
            if (first == null) {
                first = temp;
            } else {
                next.next = temp;
            }
            next = temp;
            flag = temp1.next != null || temp2.next != null || sum > 0;
            temp1 = temp1.next == null ? zero : temp1.next;
            temp2 = temp2.next == null ? zero : temp2.next;
        }
        return first;
    }

    @Data
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 最长无重复字符长度
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int max = 1;
        //记录temp数组的启始位置
        int now = 0;
        char[] chars = s.toCharArray();
        if (chars.length == 0) {
            return 0;
        }
        for (int i = 1; i < chars.length; i++) {
            int j = now;
            for (; j < i; j++) {
                if (chars[j] == chars[i]) {
                    break;
                }
            }
            if (j < i) {
                now = j + 1;
            } else {
                max = max > i - now + 1 ? max : i - now + 1;
            }
        }
        return max;
    }

    /**
     * 寻找两个有序数组的中位数
     *
     * @param lon
     * @param small
     * @return
     */
    public static double findMedianSortedArrays(int[] lon, int[] small) {
        //保证a比b长，减小比对的数组的长度
        if (small.length > lon.length) {
            int[] temp = lon;
            lon = small;
            small = temp;
        }

        int n = lon.length;
        int m = small.length;

        //m个数可以分为0，到m个数到一边
        int leftM = 0;
        int rightM = m;
        //第几个 总共的数目的中间的位置 3+5+1/2 = 4 2+2+1/2=2
        int mid = (n + m + 1) / 2;
        //第几个 中间的位置的个数位置
        int midM = m / 2;
        boolean isSingle = (((n + m) & 1) == 1 ? true : false);
        while (leftM <= rightM) {
            //当midM所在位置在mid的时候  num1数组有多少在前半部分
            int posN = mid - midM;
            // small[midM-1] small[midM]
            // lon[posN-1] lon[posN]
            //当small的最小中间位置大于lon数组的最大中间位置时就缩小small的位置  其他是防止越界的条件
            if (midM != 0 && posN != n && small[midM - 1] > lon[posN]) {
                //midM已经判断过了
                rightM = midM - 1;
            } else if (midM != m && posN != 0 && small[midM] < lon[posN - 1]) {
                // 当small的最大中间位置小于lon数组的最小中间位置时就扩大small的位置
                leftM = midM + 1;
            } else {
                //已经到边界了,就是small或者lon全在某一边
                int left = 0;
                int right = 0;
                if (midM == 0) {
                    //small全大的一边
                    left = lon[posN - 1];
                } else if (posN == 0) {
                    //small 全在小的一边
                    left = small[midM - 1];
                } else {
                    left = Math.max(small[midM - 1], lon[posN - 1]);
                }
                if (isSingle) {
                    return left;
                }

                if (midM == m) {
                    //small 全在小的一边
                    right = lon[posN];
                } else if (posN == n) {
                    //small 全在大的一边
                    right = small[midM];
                } else {
                    right = Math.min(lon[posN], small[midM]);
                }
                return (left + right) / 2.0;
            }
            midM = (rightM + leftM) / 2;
        }
        return 0;
    }


    public static String longestPalindrome(String s) {
        if (s == null || s == "") {
            return "";
        }
        char[] strs = s.toCharArray();
        int maxLenth = 0;
        int maxi = 0;
        int maxj = 0;
        for (int i = 0; i < strs.length; i++) {
            for (int j = strs.length - 1; j >= i; j--) {
                if (ishui(i, j, strs)) {
                    int tempMax = j - i;
                    if (tempMax > maxLenth) {
                        maxLenth = tempMax;
                        maxi = i;
                        maxj = j;
                    }
                }
            }
        }
        return s.substring(maxi, maxj) + s.charAt(maxj);
    }

    public static boolean ishui(int i, int j, char[] strs) {
        //就一个直接返回是
        if (i == j) {
            return true;
        }
        for (; i <= j; i++, j--) {
            if (strs[i] != strs[j]) {
                break;
            }
        }
        //跳出的就不是了
        return i > j ? true : false;
    }

    public static String convert(String s, int numRows) {
        StringBuffer sb = new StringBuffer();
        if (numRows == 1) {
            return s;
        }
        int mod = 2 * (numRows - 1);
        for (int i = 0; i < s.length(); ) {
            sb.append(s.charAt(i));
            i += mod;
        }
        for (int j = 1; j < numRows - 1; j++) {
            int mod1 = 2 * (numRows - 1 - j);
            for (int i = j; i < s.length(); i += mod) {
                sb.append(s.charAt(i));
                if (i + mod1 < s.length()) {
                    sb.append(s.charAt(i + mod1));
                }
            }
        }
        for (int i = numRows - 1; i < s.length(); ) {
            sb.append(s.charAt(i));
            i += mod;
        }
        return sb.toString();
    }

    public static int reverse(int x) {
        int max = (1 << 31) - 1;
        int min = -1 << 31;
        int temp = 0;
        //是否是负数
        boolean negative = x < 0 ? true : false;
        while (x != 0) {
            //正数的时候
            int bj = negative ? min : max;
            int tempbj = (bj - x % 10) / 10;
            if (Math.abs(tempbj) >= Math.abs(temp)) {
                temp = temp * 10 + x % 10;
            } else {
                return 0;
            }
            x /= 10;
        }
        return temp;
    }

    public static int myAtoi(String str) {
        str = str.trim();
        if (str.equals("")) {
            return 0;
        }
        char[] cs = str.toCharArray();
        int fuhao = 1;
        int temp = 0;
        if (cs[0] == '-' || cs[0] == '+') {
            fuhao = cs[0] == '-' ? -1 : 1;
        } else if (cs[0] < '0' || cs[0] > '9') {
            return 0;
        } else {
            temp = cs[0] - '0';
        }

        int max = (1 << 31) - 1;
        int min = -1 << 31;
        for (int i = 1; i < cs.length; i++) {
            //非数字就结束
            if (cs[i] < '0' || cs[i] > '9') {
                break;
            } else {
                int mod = (cs[i] - '0');
                if (fuhao == 1 && (max - mod) / 10 >= temp) {
                    temp = temp * 10 + mod;
                } else if (fuhao == 1 && (max - mod) / 10 < temp) {
                    return max;
                } else if (fuhao == -1 && (min + mod) / 10 <= temp) {
                    temp = temp * 10 - mod;
                } else if (fuhao == -1 && (min + mod) / 10 > temp) {
                    return min;
                }
            }
        }
        return temp;
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int temp = 0;
        while (x != 0) {
            temp = temp * 10 + x % 10;
            x /= 10;
        }
        return temp == x;
    }


    public boolean isMatch(String s, String p) {
        //p为0的时候进来如果s为0就匹配完了ok
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        //两边的第一个字符串是否匹配
        boolean first_match = (!s.isEmpty() &&
                (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

        //如果匹配串的长度大于2且后一个是*
        if (p.length() >= 2 && p.charAt(1) == '*') {
            //看看s这个不认的情况就不需要匹配(*匹配0个)      s这个字符认的情况下要匹配(s截取)
            return (isMatch(s, p.substring(2)) || (isMatch(s.substring(1), p) && first_match));
        } else {
            //不是*就匹配下一个
            return first_match && isMatch(s.substring(1), p.substring(1));
        }

    }

    public static boolean isMatch2(String s, String p) {
        //p为0的时候进来如果s为0就匹配完了ok
        boolean[][] me = new boolean[s.length() + 1][p.length() + 1];
        return dpMatch(0, 0, s, p, me);
    }

    private static boolean dpMatch(int textNum, int patternNum, String text, String pattern, boolean[][] me) {
        //剪枝
        if (me[textNum][patternNum]) {
            return me[textNum][patternNum];
        }
        //长度为0就结束
        if (patternNum == pattern.length()) {
            return textNum == text.length();
        }
        //判断 patternNum 和textNum是否相等
        char pc = pattern.charAt(patternNum);
        boolean match = textNum < text.length() && (pc == '.' || pc == text.charAt(textNum));
        if (patternNum + 1 < pattern.length() && pattern.charAt(patternNum + 1) == '*') {
            me[textNum][patternNum] = dpMatch(textNum, patternNum + 2, text, pattern, me) || (match && dpMatch(textNum + 1, patternNum, text, pattern, me));
            return me[textNum][patternNum];
        } else {
            me[textNum][patternNum] = match && dpMatch(textNum + 1, patternNum + 1, text, pattern, me);
            return me[textNum][patternNum];
        }
    }

    enum Result {
        TRUE, FALSE
    }

    class Solution {
        Result[][] memo;


        public boolean dp(int i, int j, String text, String pattern) {
            if (memo[i][j] != null) {
                return memo[i][j] == Result.TRUE;
            }
            boolean ans;
            if (j == pattern.length()) {
                ans = i == text.length();
            } else {
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));

                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    ans = (dp(i, j + 2, text, pattern) ||
                            first_match && dp(i + 1, j, text, pattern));
                } else {
                    ans = first_match && dp(i + 1, j + 1, text, pattern);
                }
            }
            memo[i][j] = ans ? Result.TRUE : Result.FALSE;
            return ans;
        }
    }

    public static String intToRoman(int num) {
        char[] base = new char[]{'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        String[] sp = new String[]{"VI", "XI", "LX", "CX", "DC", "MC"};

        StringBuffer sb = new StringBuffer();
        int temp = 0;
        for (int i = 0; num != 0; i++) {
            temp = num % 10;
            num /= 10;
            if (temp == 4) {
                sb.append(sp[i * 2]);
                continue;
            } else if (temp == 9) {
                sb.append(sp[i * 2 + 1]);
                continue;
            }
            for (int j = 0; j < temp % 5; j++) {
                sb.append(base[2 * i]);
            }
            if (temp / 5 > 0) {
                sb.append(base[2 * i + 1]);
            }
        }
        return sb.reverse().toString();
    }

    public int romanToInt(String s) {
        char[] base = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        int[] baseNum = {1000, 500, 100, 50, 10, 5, 1};
        int[] res = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < base.length; j++) {
                if (s.charAt(i) == base[j]) {
                    res[i] = baseNum[j];
                    break;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            //当i不为最后一个时   当前当i比下一个小就是特殊当
            if (i < s.length() - 1 && res[i] < res[i + 1]) {
                sum += res[i + 1] - res[i];
                i++;
            } else {
                sum += res[i];
            }
        }
        return sum;
    }

    public static String longestCommonPrefix(String... strs) {
        int strNum = strs.length;
        if (strNum == 0) {
            return "";
        }
        int count = 0;
        int limit = Integer.MAX_VALUE;
        for (int i = 0; i < strNum; i++) {
            limit = Math.min(limit, strs[i].length());
        }
        for (int i = 0; i < limit; i++) {
            char tempChar = strs[0].charAt(i);
            int j = 1;
            for (; j < strNum; j++) {
                if (tempChar != strs[j].charAt(i)) {
                    break;
                }
            }
            if (j < strNum) {
                break;
            }
            count++;
        }
        return count == 0 ? "" : strs[0].substring(0, count);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        //分出正负并排序
        List<Integer> fu = new ArrayList();
        List<Integer> zheng = new ArrayList();
        int countZero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                fu.add(nums[i]);
            } else {
                if (nums[i] == 0) {
                    countZero++;
                }
                zheng.add(nums[i]);
            }
        }
        fu.sort((a, b) -> a - b);
        zheng.sort((a, b) -> a - b);
        System.out.println("========================");
        System.out.println(fu);
        System.out.println(zheng);


        List<List<Integer>> resList = new ArrayList();
        List<Integer> tempList = null;
        //3个0的情况
        if (countZero >= 3) {
            tempList = Arrays.asList(0, 0, 0);
            resList.add(tempList);
        }
        int min = -1 << 31;
        int max = (1 << 31) - 1;
        //两个负数相加 一个正数
        for (int i = 0; i < fu.size() - 1; i++) {
            int temp1 = fu.get(i);
            int limit = min - temp1;
            for (int j = i + 1; j < fu.size(); j++) {
                int temp2 = fu.get(j);
                //防溢出
                if (limit < fu.get(j)) {
                    int res = midFind(zheng, temp1 + fu.get(j));
                    if (res >= 0) {
                        tempList = new ArrayList();
                        tempList.add(temp1);
                        tempList.add(temp2);
                        tempList.add(zheng.get(res));
                        resList.add(tempList);
                    }
                }
                //如果下一个和这个一样大就防止重复跳过
                while (j < fu.size() - 1 && fu.get(j + 1) == temp2) {
                    j++;
                }
            }
            //如果下一个和这个一样大就防止重复跳过
            while (i < fu.size() - 1 && fu.get(i + 1) == temp1) {
                i++;
            }
        }

        //两个非负数相加 一个负数
        for (int i = 0; i < zheng.size() - 1; i++) {
            int temp1 = zheng.get(i);
            int limit = max - temp1;
            for (int j = i + 1; j < zheng.size(); j++) {
                int temp2 = zheng.get(j);
                //防溢出
                if (limit > zheng.get(j)) {
                    int res = midFind(fu, temp1 + zheng.get(j));
                    if (res >= 0) {
                        tempList = new ArrayList();
                        tempList.add(fu.get(res));
                        tempList.add(temp1);
                        tempList.add(temp2);
                        resList.add(tempList);
                    }
                }
                //如果下一个和这个一样大就防止重复跳过
                while (j < zheng.size() - 1 && zheng.get(j + 1) == temp2) {
                    j++;
                }
            }
            //如果下一个和这个一样大就防止重复跳过
            while (i < zheng.size() - 1 && zheng.get(i + 1) == temp1) {
                i++;
            }
        }
        resList.sort((a, b) -> a.get(0) - b.get(0));
        return resList;
    }

    public static int midFind(List<Integer> a, int find) {
        int left = 0;
        int right = a.size() - 1;
        int mid = (left + right) / 2;
        find = 0 - find;
        boolean flag = find >= 0;
        while (left <= right) {
            if (find == a.get(mid)) {
                return mid;
            } else if (find > a.get(mid)) {
                left = mid + 1;
            } else if (find < a.get(mid)) {
                right = mid - 1;
            }
            mid = (right + left) / 2;
        }
        return -1;
    }

    public static int threeSumClosest(int[] nums, int target) {
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int temp = nums[i] + nums[j] - target;
                for (int k = j + 1; k < nums.length; k++) {
                    int temp2 = temp + nums[k];
                    if (temp2 == 0) {
                        return target;
                    }
                    distance = Math.min(temp2, distance);
                }
            }
        }
        return distance;
    }

    public List<String> letterCombinations(String digits) {
        char[] zm = {'c', 'f', 'i', 'l', 'o', 's', 'v', 'z'};
        char[] base = new char[26];
        for (int i = 0; i < 26; i++) {
            base[i] = (char) (i + 'a');
        }
        List<String> strList = new ArrayList<>();
        if (digits == "") {
            return strList;
        }
        dg(0, digits, zm, "", strList);
        return strList;
    }

    public static void dg(int len, String digits, char[] zm, String pre, List<String> strList) {
        if (len == digits.length()) {
            strList.add(pre);
            return;
        }
        int index = digits.charAt(len) - '2';
        char start = index == 0 ? 'a' : (char) (zm[index - 1] + 1);
        char end = zm[index];
        int limit = end - start;
        for (int i = 0; i <= limit; i++) {
            dg(len + 1, digits, zm, pre + ((char) (start + i)), strList);
        }
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        kuaipai(nums, 0, nums.length-1);
        List<List<Integer>> resultList = new ArrayList<>();
        List<Integer> tempList = null;
        int tempSum = 0;
        for (int i=0;i<=nums.length-4;i++){
            //防重复
            if (i>0 && nums[i]==nums[i-1]){
                continue;
            }
            for (int j = i+1;j<=nums.length-3;j++){
                //防重复
                if (j > i +1 && nums[j]==nums[j-1]){
                    continue;
                }
                tempSum = target - nums[i] - nums[j];
                int start=j+1;
                int end = nums.length-1;
                while (start < end){
                    if (start > j+1 && nums[start] == nums[start-1]){
                        start++;
                        continue;
                    }
                    int tempSum1 = nums[start] + nums[end];
                    if (tempSum1 == tempSum){
                        tempList = Arrays.asList(nums[i], nums[j], nums[start], nums[end]);
                        resultList.add(tempList);
                        start++;
                        end--;
                    } else if(tempSum1 > tempSum){
                        end--;
                    } else {
                        start++;
                    }
                }
            }

        }
        return resultList;
    }

    private static void kuaipai(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int pos = getPosion(nums, start, end);
        kuaipai(nums, start, pos-1);
        kuaipai(nums, pos + 1, end);
    }

    private static int getPosion(int[] nums, int start, int end) {
        int temp = nums[end];
        int count = end-1;
        int tempNum = 0;
        //把比temp大的放到count位置
        for (int i = start; i <= count; ) {
            if (nums[i]> temp){
                tempNum = nums[count];
                nums[count] = nums[i];
                nums[i] = tempNum;
                count--;
            } else {
                i++;
            }
        }
        nums[end] = nums[count+1];
        nums[count+1]=temp;
        return count+1;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = null;
        int len = 1;
        temp = head;
        while(temp.next != null){
            temp = temp.next;
            len++;
        }
        len = len-n;
        temp = head;
        //删除的是头节点
        if(len==0){
            return head.next;
        }
        //过到len过
        for(int i =0;i<len;i++){
            temp=temp.next;
        }
        if(temp.next != null){
            temp.next = temp.next.next;
        }
        return head;
    }


    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0){
            return null;
        }
        ListNode[] heapList = new ListNode[lists.length];
        //初始化建立小顶堆
        int count = 0;
        for(int i=0;i<lists.length;i++){
            count = insertHeap(heapList, lists[i], lists.length, count);
        }
        ListNode head=heapList[0];
        ListNode temp = head;
        count = pop(heapList, count);
        while(count > 0){
            temp.next = heapList[0];
            count = pop(heapList, count);
            temp = temp.next;
        }
        return head;
    }

    public static int insertHeap(ListNode[] heapList, ListNode ln, int len,int count){
        //堆里的长度和len比满了  或者ln==空
        if(count >= len || ln==null){
            return count;
        }
        //放入堆里
        int pos = count;
        heapList[count++] = ln;
        ListNode temp = null;
        while((pos-1)/2>= 0 &&heapList[(pos-1)/2].val>heapList[pos].val){
            temp = heapList[(pos-1)/2];
            heapList[(pos-1)/2] = heapList[pos];
            heapList[pos]=temp;
            pos= (pos-1)/2;
        }
        return count;
    }

    public static int pop(ListNode[] heapList, int count){
        if(count == 0){
            return -1;
        }
        ListNode res = heapList[0];
        ListNode next = res.next;
        ListNode temp = null;
        //没有了就count-- ,然后把最后一个拿到上面进行堆化;
        int pos = 0;
        int minPos = pos;
        if(next == null){
            heapList[0]=heapList[--count];
        } else {
            //还有就next放进去堆化
            heapList[0]=next;
        }
        //从上向下堆化
        while(pos*2+1<count){
            if(heapList[pos].val > heapList[pos*2+1].val){
                minPos = pos*2+1;
            }
            if(pos*2+2<count && heapList[minPos].val > heapList[pos*2+2].val)  {
                minPos = pos*2+2;
            }
            //没有变化就结束
            if (pos == minPos){
                break;
            }
            temp=heapList[pos];
            heapList[pos]=heapList[minPos];
            heapList[minPos]=temp;
            pos = minPos;
        }
        return count;
    }

    private static ListNode createParam(int[] param){
        ListNode temp = null;
        ListNode head = null;
        for(int i =0; i< param.length;i++){
            if (head==null){
                head=new ListNode(param[i]);
                temp= head;
            } else {
                temp.next=new ListNode(param[i]);
                temp=temp.next;
            }
        }
        return head;
    }

    public static ListNode swapPairs(ListNode head) {
        if(head==null){
            return head;
        }
        int count = 1;
        ListNode temp1 = head;
        ListNode temp2 = head.next;
        ListNode front = null;
        ListNode after = null;
        while(temp1!=null && temp2!= null){
            after = temp2.next;
            if(front==null){
                head=temp2;
                front = temp1;
            } else {
                front.next=temp2;
                front=temp1;
            }
            temp1.next=null;
            temp2.next=temp1;

            temp1=after;
            temp2= after == null ? null:after.next;
        }
        return head;
    }


    public static ListNode reverseKGroup(ListNode head, int k) {
        if(k==1){
            return head;
        }
        ListNode[] tempArray = new ListNode[k];
        ListNode temp = head;
        ListNode front = null;
        int count =0;
        while(temp!=null){
            if(count<k){
                tempArray[count++]=temp;
                temp=temp.next;
            } else if(count==k){
                //反转
                reverseK(tempArray, count);
                if(front == null){
                    head = tempArray[count-1];
                    front = tempArray[0];
                } else {
                    front.next=tempArray[count-1];
                }
                count=0;
                tempArray[count++]=temp;
                temp=temp.next;
            }
        }
        String a="";
        if (count == k){
            reverseK(tempArray, count);
            if(front == null){
                head = tempArray[count-1];
            } else {
                front.next=tempArray[count-1];
            }
        }
        return head;
    }

    public static void reverseK(ListNode[] tempArray, int count){
        tempArray[0].next=tempArray[count-1].next;
        for(int i =1 ;i<=count-1;i++){
            tempArray[i].next=tempArray[i-1];
        }
    }

    public static int divide(int dividend, int divisor) {
        int max = (1<<31)-1;
        int min = -1<<31;
        if(dividend==min && divisor==-1){
            return max;
        }
        //都为相同的时候为false
        boolean flag = (dividend>0) ^ (divisor >0);
        //if((dividend>0&&divisor<0 )||(dividend<0&&divisor>0 )){
        //  flag=true;
        //}

        //转成负数不会溢出
        dividend = dividend < 0? dividend : -dividend;
        divisor = divisor < 0? divisor : -divisor;
        int sum=divisor;
        int count = 0;
        while(dividend < sum){
            count++;
            if(min - sum > divisor){
                break;
            }
            sum+=divisor;
        }
        return flag? count:-count;
    }
}
