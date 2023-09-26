package com.pf.leetcode.contest.leecode;

import java.util.Arrays;

public class ContestShuang112 {
    public static void main(String[] args) {
        ContestShuang112 contestShuang112 = new ContestShuang112();
        String s;
        int k;
        s = "kubawiihlmnrlpcydtsffwwfkyyanjyswxmxa";
        k = 32;
        // ans = 336
        System.out.println(contestShuang112.countKSubsequencesWithMaxBeauty(s, k));

    }
    int mode = (int)1e9 + 7;
    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        // 字符分数
        int[] scores = maxBeautifulScore(s);
        // 排序
        Arrays.sort(scores);
        int l = 0;
        int r = 25;
        while (l < r) {
            int temp = scores[l];
            scores[l++] = scores[r];
            scores[r--] = temp;
        }
        // 第k个的分数
        int same = scores[k - 1];
        int sameCnt = 0;
        int charCnt = 0;
        int lastInd = -1;
        // 和k个相同的数目  不同字符的个数  最后一个不和k相同的数字
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == same) sameCnt++;
            if (scores[i] != 0) charCnt++;
            if (sameCnt == 0) lastInd = i;
        }
        // 字符数字小于 k 返回0
        if (charCnt < k) {
            return 0;
        }
        // 前k个字符 出现次数相乘
        long ans = 1L;
        for (int i = 0; i < k; i++) {
            ans *= scores[i];
            ans %= mode;
        }
        // 与第k个不相同的 都是必选的，这些贡献大， 所以需要在与k 相同的里面选择 剩余的
        // 前k个里有lastInd + 1 个是不比k大的，所以这几个必选， 剩下的就是在sameCnt 中选择 need个的组合
        int need = k - lastInd - 1;
        ans *= Combination1(sameCnt, need);
        ans %= mode;

        return (int) ans;
    }


    private static int Combination1(int n, int k) {
        int a=1,b=1;
        if(k>n/2) {
            k=n-k;
        }
        for(int i=1;i<=k;i++) {
            a*=(n+1-i);
            b*=i;
        }
        return a/b;
    }

    public int[] maxBeautifulScore(String s) {
        int[] scores = new int[26];
        for (int i = 0; i < s.length(); i++) {
            scores[s.charAt(i) - 'a']++;
        }
        return scores;
    }
}
