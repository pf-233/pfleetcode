package com.pf.leetcode.mid;

/**
 * author：11413
 * date：10/20/2023
 * Description:
 */
public class HIndex {

    public static void main(String[] args) {
        HIndex hIndex = new HIndex();
        int[] citations = null;
        citations = new int[]{0,1,3,5,6};
        System.out.println(hIndex.hIndex(citations));
    }
    public int hIndex(int[] citations) {
        int left = 0;
        int len = citations.length;
        int right = len;

        int ans = 0;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            int papers = len - mid;
            if (citations[mid] >= papers) {
                right = mid;
                // ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return right == len ? 0 : len - right;
    }
}
