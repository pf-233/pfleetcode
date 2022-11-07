package com.pf.leetcode.dp;

public class RemoveBoxes {

    public static void main(String[] args) {
        RemoveBoxes removeBoxes = new RemoveBoxes();
        System.out.println(removeBoxes.removeBoxes(new int[] {1,3,2,2,2,3,4,3,1}));
    }
    int[][][] dp;
    int[] boxes;
    public int removeBoxes(int[] boxes) {
        dp = new int[100][100][100];
        this.boxes = boxes;
        return calScoreArea(0, boxes.length - 1, 0);
    }

    private int calScoreArea(int left, int right, int endLength) {
        if(left > right) {
            return endLength * endLength;
        }
        if(dp[left][right][endLength] != 0) {
            return dp[left][right][endLength];
        }


        int tmp = boxes[right];
        if(endLength > 0) {
            tmp = boxes[right + 1];
        }

        int mergeNum = 0;
        while(right - mergeNum >= left && boxes[right - mergeNum] == tmp) {
            mergeNum++;
        }
        int nowLen = endLength + mergeNum;
        int max = calScoreArea(left, right - mergeNum, 0) + nowLen * nowLen;
        for(int i = left; i <= right - mergeNum; i++) {
            if(boxes[i] == tmp) {
                max = Math.max(max, calScoreArea(left, i - 1, nowLen + 1) + calScoreArea(i + 1, right - mergeNum, 0));
            }
        }
        dp[left][right][endLength] = max;
        return max;
    }
}
