package com.pf.leetcode.contest.leecode;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Contest347 {


    public static void main(String[] args) throws IOException {
        Contest347 contest347 = new Contest347();
        String path = "src/main/java/com/pf/leetcode/contest/leecode/asset/aa.txt";
        File file = new File(path);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(isr);


        String s = br.readLine();
        long time = System.currentTimeMillis();
        System.out.println("minCount:" + contest347.minimumCost(s));
        System.out.println("time:" + (System.currentTimeMillis() - time));
        System.out.println("beforeCnt:"+contest347.count);
        System.out.println("len:" + s.length());
    }

//    minCount:50000
//    time:81
//    beforeCnt:599991
//    len:100000

    int count = 0;
    int[] arr;
    long[][][] memoBefore;
    long[][][] memoAfter;

    public long minimumCost(String s) {
        int len = s.length();
        arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = s.charAt(i) - '0';
        }
        long min = Long.MAX_VALUE;
        memoBefore = new long[len][2][2];
        memoAfter = new long[len][2][2];
        for (int i = 0; i < len; i++) {
            Arrays.fill(memoBefore[i][0], -1);
            Arrays.fill(memoBefore[i][1], -1);
            Arrays.fill(memoAfter[i][0], -1);
            Arrays.fill(memoAfter[i][1], -1);
        }
        for (int i = 0; i < len; i++) {
            int changeNum = (arr[i] + 1) % 2;
            if (changeNum == -1) {
                System.out.println("aa");
            }
            // [0, i-1] 改成(arr[i] + 1) % 2,此时前面变化次数1次       [i+1, len-1] 变化为0次
            long change = i + 1 + before(i - 1, true, changeNum) + after(i + 1, false, changeNum);
            // 改后面的
            change = Math.min(change, len - i + before(i - 1, false, changeNum) + after(i + 1, true, changeNum));
            long noChange = before(i - 1, false, arr[i]) + after(i + 1, false, arr[i]);
            min = Math.min(min, noChange);
            min = Math.min(min, change);
        }
        return min;
    }

    private long before(int end, boolean isChanged, int need) {
        if (end < 0) {
            return 0;
        }
        count++;
        if (memoBefore[end][isChanged ? 1 : 0][need] != -1) {
            return memoBefore[end][isChanged ? 1 : 0][need];
        }

        int now = isChanged ? (arr[end] + 1) % 2 : arr[end];
        long step = 0L;
        if (now == need) {
            step = before(end - 1, isChanged, need);
        } else {
            step = before(end - 1, !isChanged, need) + end + 1;
        }
        memoBefore[end][isChanged ? 1 : 0][need] = step;
        return step;
    }

    private long after(int start, boolean isChanged, int need) {
        if (start == arr.length) {
            return 0;
        }
        if (start == -1 || need == -1) {
            System.out.println("aaa");
        }
        if (memoAfter[start][isChanged ? 1 : 0][need] != -1) {
            return memoAfter[start][isChanged ? 1 : 0][need];
        }

        int now = isChanged ? (arr[start] + 1) % 2 : arr[start];
        long step = 0L;
        if (now == need) {
            step = after(start + 1, isChanged, need);
        } else {
            step = after(start + 1, !isChanged, need) + arr.length - start;
        }
        memoAfter[start][isChanged ? 1 : 0][need] = step;
        return step;
    }
/*
    minCount:50000
    time:567
    beforeCnt:599991
    len:100000

    int[] arr;
    Map<String, Long> memoBefore;
    Map<String, Long> memoAfter;
    public long minimumCost(String s) {
        int len = s.length();
        arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = s.charAt(i) - '0';
        }
        long min = Long.MAX_VALUE;
        memoBefore = new HashMap();
        memoAfter = new HashMap();

        for (int i = 0; i < len; i++) {
            int changeNum =  (arr[i] + 1) % 2;
            // [0, i-1] 改成(arr[i] + 1) % 2,此时前面变化次数1次       [i+1, len-1] 变化为0次
            long change = i + 1 + before(i - 1, true, changeNum) + after(i+1, false, changeNum);
            // 改后面的
            change = Math.min(change, len - i + before(i - 1, false, changeNum) + after(i+1, true, changeNum));
            long noChange = before(i - 1, false, arr[i]) + after(i+1, false, arr[i]);
            min = Math.min(min, noChange);
            min = Math.min(min, change);
        }
        return min;
    }

    private long before(int end, boolean isChanged, int need) {
        if (end < 0) {
            return 0;
        }
        count++;
        String key = end+","+isChanged+","+need;
        if (memoBefore.get(key) != null) {
            return memoBefore.get(key);
        }

        int now = isChanged ? (arr[end] + 1) % 2 : arr[end];
        long step = 0L;
        if (now == need) {
            step = before(end - 1, isChanged, need);
        } else {
            step = before(end - 1, !isChanged, need) + end + 1;
        }
        memoBefore.put(key, step);
        return step;
    }

    private long after(int start, boolean isChanged, int need) {
        if (start == arr.length) {
            return 0;
        }
        String key = start+","+isChanged+","+need;
        if (memoAfter.get(key) != null) {
            return memoAfter.get(key);
        }

        int now = isChanged ? (arr[start] + 1) % 2 : arr[start];
        long step = 0L;
        if (now == need) {
            step = after(start + 1, isChanged, need);
        } else {
            step = after(start + 1, !isChanged, need) + arr.length - start;
        }
        memoAfter.put(key, step);
        return step;
    }*/
}