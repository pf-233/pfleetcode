package com.pf.leetcode.other;

public class NumFriendRequests {
    public static void main(String[] args) {
        NumFriendRequests numFriendRequests = new NumFriendRequests();
        System.out.println(numFriendRequests.numFriendRequests(new int[]{16,16}));
    }
    public int numFriendRequests(int[] ages) {
        int[] ageCount = new int[125];
        for (int i = 0; i < ages.length; i++) {
            ageCount[ages[i]]++;
        }
        int res = 0;
        for (int i = 1; i < ageCount.length; i++) {
            for (int j = 1; j < ageCount.length; j++) {
                if (sendFriend(i, j)) {
                    if (i != j) {
                        res += ageCount[i] * ageCount[j];
                    } else if (ageCount[i] != 0){
                        res += (ageCount[i] - 1) * ageCount[i];
                    }
                }
            }
        }
        return res;
    }

    private boolean sendFriend(int x, int y) {
        if (y <= 0.5 * x + 7 || y > x || (y > 100 && x < 100)) {
            return false;
        }
        return true;
    }
}
