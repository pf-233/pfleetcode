package com.pf.leetcode.contest.codeforces.div2;

import java.util.*;

public class Round668 {

    public static void main(String[] args) {
//        new Round668().permutationForgery();
        int[] res = new Round668().topKFrequent(new int[]{1,1,1,2,2,3}, 2);
       res = new Round668().topKFrequent(new int[]{-1,-1}, 1);
        System.out.println(res);
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int tmp = map.getOrDefault(nums[i], 0);
            map.put(nums[i], ++tmp);
        }
        int[] res = new int[k];
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (a,b) -> (map.get(a) - map.get(b)));
        for (Integer key : map.keySet()) {
            if (queue.size() < k) {
                queue.offer(key);
            } else if (map.get(queue.peek()) < map.get(key)){
                queue.poll();
                queue.offer(key);
            }
        }
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        return res;
    }

    private void  heapify(int[] res, int nowCount, int count, int val) {
        if (nowCount < res.length) {

        }
    }

    private void heap() {

    }
    public void permutationForgery() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        while (num-- > 0) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            System.out.print(arr[n - 1]);
            for (int i = n - 2; i >= 0; i--) {
                System.out.println(" " + arr[i]);
            }
        }
    }

    public void balancedBitstring() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        while (num-- > 0) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            String sb = scanner.next();
            int count1 = 0;
            int count0 = 0;
            Stack<Integer> stack = new Stack<>();
            for(int i = 0; i < k; i++) {
                switch (sb.charAt(i)) {
                    case '1' : count1++; break;
                    case '0' : count0++; break;
                    case '?' : stack.push(i); break;
                }
            }
            if (Math.abs(count0 - count1) > stack.size()) {
                System.out.println("NO");
                continue;
            } else {
                char[] chs = sb.toCharArray();
                if (stack.size() == 1) {
                    chs[stack.pop()] = count0 > count1 ? '1' : '0';
                }
                int i = k;
                for (; i < n; i++) {
                    char pre = chs[i - k];
                    char now = chs[i];
                    if (pre != '?' && now != '?') {
                        if (pre != now) {
                            System.out.println("NO");
                            break;
                        }
                    } else if (pre == '?' && now == '?'){
                        
                    } else {
                        char tmp = pre == '?' ? now : pre;
                        chs[i - k] = tmp;
                        chs[i] = tmp;
                    }
                }
                if (i < n) {
                    continue;
                }
            }
            System.out.println("YES");
        }
    }

    public void cancellation() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        while (num-- > 0) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            long max = arr[n - 1];
            long sum = 0;
            for (int i = n - 1; i >= 0; i--) {
                sum += arr[i];
                max = Math.max(sum, max);
            }
            System.out.println(max);
        }
    }

}
