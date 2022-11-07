package com.pf.leetcode.other;

import java.util.Arrays;

public class PlatesBetweenCandles {
    public static void main(String[] args) {
        PlatesBetweenCandles platesBetweenCandles = new PlatesBetweenCandles();
        System.out.println(platesBetweenCandles.platesBetweenCandles("***|**|*****|**||**|*", new int[][] {{1,17},{4,5}}));

    }
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int len = s.length();
        int[] prech = new int[len];
        int[] afterch = new int[len];
        int[] countch = new int[len];
        Arrays.fill(prech, -1);
        Arrays.fill(afterch, -1);
        prech[0] = s.charAt(0) == '|' ? 0 : -1;
        countch[0] = s.charAt(0) == '|' ? 1 : 0;
        for (int i = 1; i < len; i++) {
            countch[i] = countch[i - 1];
            if (s.charAt(i) == '|') {
                prech[i] =  i;
                countch[i]++;
            } else if (s.charAt(i - 1) == '|') {
                prech[i] =  i - 1;
            } else {
                prech[i] = prech[i - 1];
            }
        }

        afterch[len - 1] = s.charAt(len - 1) == '|' ? len - 1 : -1;
        for (int i = len - 2; i >= 0; i--) {
            if (s.charAt(i) == '|') {
                afterch[i] =  i;
            } else if (s.charAt(i + 1) == '|') {
                afterch[i] =  i + 1;
            } else {
                afterch[i] = afterch[i + 1];
            }
        }

        int n = queries.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            ans[i] = getans(queries[i], prech, afterch, countch);
        }
        return ans;
    }

    private int getans(int[] queries, int[] prech, int[] afterch, int[] countch) {
        int preInd0 = prech[queries[0]];
        int afterInd0 = afterch[queries[0]];
        int start = preInd0 == queries[0] ? preInd0 : afterInd0;
        int preInd1 = prech[queries[1]];
        int afterInd1 = afterch[queries[1]];
        int end = afterInd1 == queries[1] ? afterInd1 : preInd1;
        if (start < 0 || end < 0) {
            return 0;
        }
        return end - start - (countch[end] - countch[start]);
    }
}
