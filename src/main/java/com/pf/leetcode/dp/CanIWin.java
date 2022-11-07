package com.pf.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class CanIWin {

    public static void main(String[] args) {
        CanIWin canIWin = new CanIWin();
        System.out.println(canIWin.canIWin(2,3));
    }
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }
        Map<Integer, Boolean> map = new HashMap();
        return dg(0,0,maxChoosableInteger, desiredTotal, map);
    }

    //path 是当前路径, nowVal 是当前的值, target 是目标值
    private boolean dg(int path, int nowVal, int len,int target, Map<Integer, Boolean> map) {
        if(map.get(path) != null) {
            return map.get(path);
        }
        if (nowVal >= target) {
            return true;
        }
        int tmp = path;
        int tmpNow = 1;
        boolean flag = false;
        for(int i = 1; i <= len; i++) {
            if((tmp & 1) == 0) {
                flag = flag || dg(path + tmpNow, nowVal + i, len, target, map);
            }
            tmp >>= 1;
            tmpNow <<= 1;
        }
        map.put(path, !flag);
        return !flag;
    }
}
