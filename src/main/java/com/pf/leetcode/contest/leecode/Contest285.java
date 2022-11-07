package com.pf.leetcode.contest.leecode;

public class Contest285 {
    public static void main(String[] args) {
        Contest285 contest285 = new Contest285();
        int numArrows = 3;
        int[] aliceArrows = new int[]{0,0,1,0,0,0,0,0,0,0,0,2};
        contest285.maximumBobPoints(numArrows, aliceArrows  );
        System.out.println("aa");
    }
    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        int state = 1 << 12;
        int[] resArr = null;
        int max = 0;
        for (int i = 0; i < state; i++) {
            int[] tmp = getBob(aliceArrows, i);
            int sum = sum(tmp);
            if (sum > numArrows) {
                continue;
            }
            int score = score(tmp);
            if (max < score) {
                max = score;
                resArr = tmp;
                if (sum < numArrows) {
                    resArr[0] += numArrows - sum;
                }
            }

        }
        return resArr;
    }

    private int score(int[] bobArrows) {
        int ans = 0;
        for (int i = 0; i < bobArrows.length; i++) {
            ans += i;
        }
        return ans;
    }

    private int sum(int[] bobArrows) {
        int ans = 0;
        for (int i = 0; i < bobArrows.length; i++) {
            ans += bobArrows[i];
        }
        return ans;
    }

    private int[] getBob(int[] aliceArrows, int state) {
        int[] res = new int[aliceArrows.length];
        int ind = aliceArrows.length - 1;
        while (state > 0) {
            if ((state & 1) == 1) {
                res[ind] = aliceArrows[ind] + 1;
            }
            ind--;
            state >>>= 1;
        }
        return res;
    }
}
