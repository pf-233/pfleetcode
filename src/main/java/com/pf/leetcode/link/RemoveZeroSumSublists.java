package com.pf.leetcode.link;

import com.pf.leetcode.entity.ListNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RemoveZeroSumSublists {
    public static void main(String[] args) {
        ListNode head = ListNode.create(new int[]{1,2,3,-3,-2});
        ListNode res = new RemoveZeroSumSublists().removeZeroSumSublists(head);
        System.out.println(res);
    }
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode sentinel = new ListNode();
        sentinel.next = head;
        Map<Integer, LinkedList<ListNode>> sumMap = new HashMap();
        LinkedList<ListNode> temp = null;
        int sum = 0;
        ListNode pre = sentinel;
        temp = new LinkedList();
        temp.add(pre);
        sumMap.put(sum, temp);
        LinkedList emptyList = new LinkedList();
        while (head != null) {
            sum += head.val;
            temp = sumMap.getOrDefault(sum, emptyList);
            if (temp.size() == 0) {
                pre.next = head;
                pre = head;
                temp = new LinkedList();
                temp.add(head);
                sumMap.put(sum, temp);
            } else {
                removeSum(temp.peek(), sum - head.val, sumMap);
                pre = temp.peek();
                pre.next = null;
            }
            head = head.next;
        }
        return sentinel.next;
    }

    private void removeSum(ListNode finalNode, int preSum, Map<Integer, LinkedList<ListNode>> sumMap) {
        ListNode now = null;
        while ((now = sumMap.get(preSum).peek()) != finalNode) {
            sumMap.get(preSum).removeLast();
            preSum -= now.val;
        }
    }
}
