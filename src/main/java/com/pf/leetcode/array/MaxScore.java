package com.pf.leetcode.array;

/**
 * author：panf
 * date：2023/12/3
 * Description:
 */
public class MaxScore {

    public static void main(String[] args) {
        MaxScore maxScore = new MaxScore();
        int[] cardPoints;
        int k;
        cardPoints = new int[]{2,2,2};
        k = 2;
        System.out.println(maxScore.maxScore(cardPoints, k));
    }
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        int[] rsum = new int[len];
        rsum[len - 1] = cardPoints[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rsum[i] = rsum[i + 1] + cardPoints[i];
        }
        for (int i = 1; i < len; i++) {
            cardPoints[i] += cardPoints[i - 1];
        }

        int max = Math.max(rsum[len - k], cardPoints[k - 1]);
        for (int i = 0; i < len && k - i >= 0; i++) {
            max = Math.max(max, cardPoints[i] + rsum[k - i]);
        }
        return max;
    }
}
