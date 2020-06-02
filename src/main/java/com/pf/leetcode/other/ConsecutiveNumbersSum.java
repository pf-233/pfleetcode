package com.pf.leetcode.other;

/**
 * @author pf
 * @date 2020-05-29 11:02
 **/
public class ConsecutiveNumbersSum {
    public static void main(String[] args) {
        int[] arr = new int[85];
        arr[0] = 1;
        for (int i = 1; i < 85; i++){
            arr[i] = arr[i - 1] + i + 1;
        }
        for (int i = 1; i < 85; i++){
            for (int j = 0; j < i; j++){
                if (arr[i] - arr[j] == 85){
                    System.out.println("========:"+i +"===="+ j);
                }
            }
        }

        System.out.println(consecutiveNumbersSum(85));
        System.out.println(consecutiveNumbersSum(9));
        System.out.println(consecutiveNumbersSum(7));
        System.out.println(consecutiveNumbersSum(4));
    }
    public static int consecutiveNumbersSum(int N) {
        // <= 2的就只能 N本身  > 2 的就可以找到两个被二除后的数
        int ans = N > 2 && (N & 1) == 1? 2 : 1;
        for (int i = 3; i < N; i++){
            int temp = N / i;
            if (temp + i / 2 <= N && temp - i / 2 > 0){
                if(N % i == 0){
                    ans++;
                }
            } else {
                //有一端超出的时候就不行了
                break;
            }
        }
        return ans;
    }
}
