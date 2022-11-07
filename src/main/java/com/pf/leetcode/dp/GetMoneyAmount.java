package com.pf.leetcode.dp;

public class GetMoneyAmount {
    public static void main(String[] args) {
        GetMoneyAmount getMoneyAmount = new GetMoneyAmount();
        System.out.println(getMoneyAmount.getMoneyAmount(10));
    }
    public int getMoneyAmount(int n) {
        int max = 0;
        for(int i = 1; i <= n; i++) {
            max = Math.max(max, midFind(i, n));
        }
        return max;
    }

//    int midFind(int i, int n) {
//        int left = 1;
//        int right = n;
//        int count = 0;
//        while(left <= right) {
//            int mid = (right - left) / 2 + left;
//            if(mid == i) {
//                System.out.println("i = " + i +" n = " + n + " count = " + count);
//                return count;
//            } else if (mid > i) {
//                count += mid;
//                right = mid - 1;
//            } else {
//                count += mid;
//                left = mid + 1;
//            }
//        }
//        return count;
//    }
    int midFind(int i, int n) {
        int left = 1;
        int right = n;
        int count = 0;
        int mid = 0;
        while(left <= right) {
            if(right - left == 1) {
                mid = left;
            } else {
                mid = (right - left) / 2 + left;
                mid += ((right - left + 1) & 1) == 0 ? 1 : 0;
            }

            if(mid == i) {
                System.out.println("i = " + i +" n = " + n + " count = " + count);
                return count;
            } else if (mid > i) {
                count += mid;
                right = mid - 1;
            } else {
                count += mid;
                left = mid + 1;
            }
        }
        return count;
    }
}
