package com.pf.leetcode.contest.codeforces.div2;

import java.util.*;

public class Round665 {

    public static void main(String[] args) {
        System.out.println(new Round665().judgePoint24(new int[]{4,1,8,7}));
//        Scanner scanner = new Scanner(System.in);
//        int num = scanner.nextInt();
//        while (num-- > 0) {
//            int n = scanner.nextInt();
//            int min = Integer.MAX_VALUE;
//            int[] ax = new int[n];
//            for (int i = 0; i < n; i++) {
//                ax[i] = scanner.nextInt();
//                min = Math.min(min, ax[i]);
//            }
//            int[] copy = Arrays.copyOf(ax, n);
//            Arrays.sort(copy);
//            boolean flag = true;
//            for (int i = 0; i < n; i++) {
//                if(ax[i] != copy[i] && (ax[i] % min != 0)) {
//                    flag = false;
//                    break;
//                }
//            }
//            System.out.println(flag ? "YES" : "NO");
//        }
    }

    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList();
        for(int i = 0; i < nums.length; i++) {
            list.add(nums[i] * 1.0);
        }

        return dg(list);
    }

    public boolean dg(List<Double> list) {
        boolean res = false;
        if(list.size() == 1) {
            return Math.abs(list.get(0) - 24) <= 1e-6;
        }
        List<Double> arr = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            arr.add(new Double(list.get(i)));
        }
        for(int i = 0; i < list.size(); i++) {
            double tmp = list.get(i);
            arr.remove(tmp);
            for(int j = 0; j < list.size(); j++) {
                if (i == j) {
                    continue;
                }
                double tmp2 = list.get(j);
                arr.remove(tmp2);
                arr.add(tmp * tmp2);
                res |= dg(arr);
                arr.remove(tmp * tmp2);

                arr.add(tmp + tmp2);
                res |= dg(arr);
                arr.remove(tmp + tmp2);
                if(tmp2 != 0) {
                    arr.add(tmp / tmp2);
                    res |= dg(arr);
                    arr.remove(tmp / tmp2);
                }

                arr.add(tmp - tmp2);
                res |= dg(arr);
                arr.remove(tmp - tmp2);

                arr.add(tmp2);
            }
            arr.add(tmp);
        }
        return res;
    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int num = scanner.nextInt();
//        while (num-- > 0) {
//            int[] arr1 = new int[3];
//            int[] arr2 = new int[3];
//            for (int i = 0; i < arr1.length; i++) {
//                arr1[i] = scanner.nextInt();
//            }
//            for (int i = 0; i < arr2.length; i++) {
//                arr2[i] = scanner.nextInt();
//            }
//            int sum = Math.min(arr1[2], arr2[1]);
//            arr1[2] -= sum;
//            arr2[1] -= sum;
//            sum *= 2;
//            int tmp = arr1[0] + arr1[2];
//            arr2[2] = arr2[2] >= tmp ? arr2[2] - tmp : 0;
//            sum -= 2 * (Math.min(arr2[2], arr1[1]));
//            System.out.println(sum);
//        }
//    }


}
