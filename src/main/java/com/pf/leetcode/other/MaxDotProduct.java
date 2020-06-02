package com.pf.leetcode.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pf
 * @date 2020-05-24 11:46
 **/
public class MaxDotProduct {
    public static void main(String[] args) {
        MaxDotProduct maxDotProduct = new MaxDotProduct();
        List list = maxDotProduct.largeGroupPositions("abbxxxxzzy");
        System.out.println(list);
    }

    public List<List<Integer>> largeGroupPositions(String S) {
        char[] chs = S.toCharArray();
        if(chs.length == 0){
            return new ArrayList();
        }
        int count = 1;
        char pre = chs[0];
        Map<Character, List<List<Integer>>> map = new HashMap();
        List<List<Integer>> tempList = null;
        for(int i = 1; i < chs.length; i++){
            if(pre == chs[i]){
                count++;
            } else {
                if(count >= 3){
                    tempList = map.getOrDefault(pre, new ArrayList());
                    List<Integer> indexList = new ArrayList();
                    indexList.add(i - count);
                    indexList.add(i);
                    tempList.add(indexList);
                    map.put(pre, tempList);
                }
                pre = chs[i];
                count = 0;
            }
        }

        List<List<Integer>> resList = new ArrayList();
        for(int i = 0; i < 26; i++){
            resList.addAll(map.getOrDefault((char)(i + 'a'), new ArrayList()));
        }
        return resList;
    }


    //先全部拿正数然后
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int ln = nums1.length;
        int ln2 = nums2.length;

        int[][] dp = new int[ln + 1][ln2 + 1];
        int res = (int) -1e9;
        for (int i = 1; i < ln; i++) {
            for (int j = 1; j < ln2; j++) {
                dp[i][j] = Math.max(Math.max(Math.max(dp[i - 1][j - 1], dp[i - 1][j - 1] + nums1[i - 1] * nums2[j - 1]), dp[i - 1][j]), dp[i][j - 1]);
            }
        }

        return res;
    }
}
