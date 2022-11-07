package com.pf.leetcode;

import java.util.*;

public class testaaa {
    static Map<Integer, List<Integer>> map;
    static int[] res;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        map = new HashMap();

        for(int i = 0; i < m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            List<Integer> tempList = map.getOrDefault(b, new ArrayList());
            tempList.add(a);
            map.put(b, tempList);
        }

        res = new int[n];
        for(int i = n; i >= 1; i--){
            if(res[i - 1] != 0){
                continue;
            }
            dfs(i, i);
        }
        print(res);
    }

    static void dfs(int index, int max){
        if(res[index - 1] != 0){
            //之前已经计算过的
            return;
        }
        res[index - 1] = max;
        List<Integer> tempList = map.getOrDefault(index, new ArrayList());
        for(int i = 0; i <  tempList.size(); i++){
            dfs(tempList.get(i), max);
        }
        return;
    }

    static void print(int[] res){
        String str = "";
        if(res.length > 0){
            str += res[0];
        }
        for(int i = 1; i < res.length; i++){
            str += " " + res[i];
        }
        System.out.println(str);
    }
}