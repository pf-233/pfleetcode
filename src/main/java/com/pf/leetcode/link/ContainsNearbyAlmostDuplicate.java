package com.pf.leetcode.link;

import java.util.Arrays;
import java.util.LinkedList;

public class ContainsNearbyAlmostDuplicate {
    public static void main(String[] args) {
        ContainsNearbyAlmostDuplicate containsNearbyAlmostDuplicate = new ContainsNearbyAlmostDuplicate();
        int[] nums = new int[] {1,2,1,1};
        System.out.println(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate(nums, 1, 0));
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        Node[] copy = new Node[len];
        for (int i = 0; i < len; i++) {
            copy[i] = new Node(nums[i], i);
        }
        Arrays.sort(copy, (a, b) -> a.val == b.val ? a.ind - b.ind :  (a.val - b.val > 0 ? 1 : -1));
        LinkedList<Node> list = new LinkedList();
        for(int i = 0; i < len; i++) {
            if (list.isEmpty()) {
                list.add(copy[i]);
            } else {
                Node pre = list.getFirst();
                while (pre != null && Math.abs(pre.val - copy[i].val) > t) {
                    list.removeFirst();
                    pre = list.peek();
                }
                for (Node tmp : list) {
                    if (Math.abs(tmp.ind - copy[i].ind) <= k) {
                        return true;
                    }
                }
                list.add(copy[i]);
            }
        }
        return false;
    }

    class Node {
        private long val;
        private int ind;
        Node(long v, int i) {
            val = v;
            ind = i;
        }
    }
}
