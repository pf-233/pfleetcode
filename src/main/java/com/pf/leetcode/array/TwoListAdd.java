package com.pf.leetcode.array;

import com.pf.leetcode.entity.ListNode;

import java.util.LinkedList;

/**
 * 两个链表相加
 *
 * @author pf
 * @date 2020-04-14 13:11
 **/
public class TwoListAdd {

    public static void main(String[] args) {
//        (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
        ListNode l1 = new ListNode(7);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);
        ListNode l4 = new ListNode(3);
        l1.next=l2;
        l2.next=l3;
        l3.next=l4;

        ListNode l21 = new ListNode(5);
        ListNode l22 = new ListNode(6);
        ListNode l23 = new ListNode(4);
        l21.next=l22;
        l22.next=l23;

        TwoListAdd twoListAdd = new TwoListAdd();
        ListNode temp = twoListAdd.addTwoNumbers(l1,l21);
        System.out.println(temp);

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        LinkedList<ListNode> list1 = new LinkedList();
        ListNode temp1=l1;
        while(temp1!=null){
            list1.addFirst(temp1);
            temp1=temp1.next;
        }

        LinkedList<ListNode> list2 = new LinkedList();
        ListNode temp2=l2;
        while(temp2!=null){
            list2.addFirst(temp2);
            temp2=temp2.next;
        }

        int carry = 0;
        ListNode zero = new ListNode(0);
        ListNode l3 = null;
        while(!list1.isEmpty() ||!list2.isEmpty() || carry > 0){
            temp1 = list1.isEmpty() ? zero : list1.pop();
            temp2 = list2.isEmpty() ? zero : list2.pop();
            int count = carry + temp1.val + temp2.val;

            ListNode temp3 = new ListNode(count%10);
            temp3.next=l3;
            l3=temp3;
            carry = count/10;
        }

//        ListNode res = reveres(l3);
        while(l3.val==0 && l3.next!=null){
            l3=l3.next;
        }
        return l3;
    }

    public ListNode reveres(ListNode node){
        ListNode cur = node;
        ListNode pre = null;
        ListNode next = null;
        while(cur != null){
            next = cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;
    }
}
