package com.pf.leetcode.dp;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author pf
 * @date 2020-05-16 23:22
 **/
public class LargestNumber {
    public static void main(String[] args) {
        int[][] points = new int[][]{
                {-3,0},
                {3,0},
                {2,6},
                {5,4},
                {0,9},
                {7,8}

        };
        System.out.println(numPoints(points, 5));
        System.out.println(arrangeWords("Keep calm and code on"));
        System.out.println(largestNumber(new int[]{4,3,2,5,6,7,2,5,5}, 9));
        System.out.println(largestNumber(new int[]{7,6,5,5,5,6,8,7,8}, 12));
        System.out.println(largestNumber(new int[]{2,4,6,2,4,6,4,4,4}, 5));
        System.out.println(largestNumber(new int[]{6,10,15,40,40,40,40,40,40}, 47));
    }

    public static int numPoints(int[][] points, int r) {
        Map<Integer, Set<Integer>> map = new HashMap();

        for(int i = 0; i < points.length; i++){
            for(int j = 0; j < points.length; j++){
                if(i == j){
                    continue;
                }
                if(calTogetherCir(points[i], points[j], r)){
                    //记录可以在同一个圆里
                    Set<Integer> tempSet = map.getOrDefault(i, new HashSet());
                    tempSet.add(j);
                    map.put(i, tempSet);
                }
            }
        }

        int max = 0;
        for(int i = 0; i < points.length; i++){
            Set<Integer> tempSeti = map.getOrDefault(i, new HashSet());
            for (Integer j : tempSeti){
                Set<Integer> tempSetj = map.getOrDefault(j, new HashSet());
                int count = 2;
                System.out.println("============");
                System.out.println(i + "  tempSeti = " + tempSeti.toString());
                System.out.println(j + "  tempSetj = " + tempSetj.toString());
                for(Integer index : tempSeti){
                    count+= tempSetj.contains(index) ? 1 : 0;
                }
                System.out.println(count);
                max = Math.max(max, count);
            }
        }

//        for(int i = 0; i < points.length; i++){
//            Set<Integer> tempSeti = map.getOrDefault(i, new HashSet());
//
//            for(int j = 0; j < points.length; j++){
//                if(i == j){
//                    continue;
//                }
//                Set<Integer> tempSetj = map.getOrDefault(j, new HashSet());
//                int count = 0;
//                for(Integer index : tempSeti){
//                    count+= tempSetj.contains(index) ? 1 : 0;
//                }
//                max = Math.max(max, count);
//            }
//        }
        return max;
    }

    /**
     * 是否在同一个圆里
     */
    static boolean calTogetherCir(int[] pointX, int[] pointY, int r){
        int dis = (pointX[0] - pointY[0]) * (pointX[0] - pointY[0]) + (pointX[1] - pointY[1]) * (pointX[1] - pointY[1]);
        return 4 * r * r >= dis;
    }

    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        List<Integer> res = new ArrayList(101);
        Map<Integer, Set<String>> map = new TreeMap();
        for(int i = 0; i < favoriteCompanies.size(); i++){
            Set<String> set = new HashSet();
            for(String s : favoriteCompanies.get(i)){
                set.add(s);
            }
            map.put(i, set);
        }

        for(int i = 0; i < favoriteCompanies.size(); i++){
            Set<String> seti = map.get(i);
            int count = 0;
            for(int j = 0; j < favoriteCompanies.size(); j++){
                if(i == j){
                    continue;
                }
                if(map.get(i).containsAll(seti)){
                    count++;
                    break;
                }
            }
            if (count == 0){
                res.add(i);
            }
        }
        return res;
    }

    public static String arrangeWords(String text) {
        char[] chs = text.toCharArray();
        Map<Integer, List<String>> map = new TreeMap();
        String[] sa = text.split(" ");
        for(int i = 0; i < sa.length; i++){
            int len = sa[i].length();
            List<String> tempList = map.getOrDefault(len, new ArrayList());
            tempList.add(sa[i]);
            map.put(len, tempList);
        }

        StringBuilder sb = new StringBuilder();
        for(Integer key : map.keySet()){
            for(String s : map.get(key)){
                sb.append(" " + s);
            }
        }
        char[] chars = sb.toString().substring(1).toCharArray();
        chars[0] = (char) (chars[0] - 32);
        return new String(chars);
    }

    public static String largestNumber(int[] cost, int target) {
        String[] dp = new String[5005];
        Arrays.fill(dp, "");
        for(int i = 1; i <= target; i++){
            for(int j = 0; j < 9; j++){
                if(i - cost[j] >= 0){
//                    System.out.println(dp[i] + "=====" +( i - cost[j]) + "====="+ dp[i - cost[j]]);
                    if (i == cost[j] || dp[i - cost[j]] != ""){
                        dp[i] = compare(dp[i], dp[i - cost[j]] + (j + 1));
                    }
//                    System.out.println(dp[i]);
                }
            }
        }
        return dp[target] == "" ? "0" : dp[target];
    }


    private static String compare(String s1, String s2){
        if(s1.length() != s2.length()){
            return s1.length() > s2.length() ? s1 : s2;
        }
        return s1.compareTo(s2) > 0 ? s1 : s2;
    }
}
