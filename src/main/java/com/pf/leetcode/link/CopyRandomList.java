//package com.pf.leetcode.link;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class CopyRandomList {
//
//    Map<Node, Node> nodesMap = new HashMap();
//    //方法一：递归回溯 O(n) + O(n)
//    public Node copyRandomList1(Node head) {
//        if (head == null) {
//            return null;
//        }
//        if(nodesMap.containsKey(head)) {
//            return nodesMap.get(head);
//        }
//
//        Node newNode = new Node(head.val);
//        nodesMap.put(head, newNode);
//
//        newNode.random = copyRandomList1(head.random);
//        newNode.next = copyRandomList1(head.next);
//        return newNode;
//    }
//
//    //时间O(n)  空间O (1)
//
//    /**
//     * 把节点放到每个原先节点的后面这种设计代替了一个map
//     * @param head
//     * @return
//     */
//    public Node copyRandomList2(Node head) {
//        if (head == null) {
//            return null;
//        }
//        Node tmp_old = head;
//        Node tmp_new = null;
//        //复制每一个节点，并把当前新节点放在老节点的后面
//        while(tmp_old != null) {
//            tmp_new = new Node(tmp_old.val);
//            tmp_new.next = tmp_old.next;
//            tmp_old.next = tmp_new;
//            tmp_old = tmp_old.next.next;
//        }
//
//        tmp_old = head;
//        Node newHead = head.next;
//        //同步随机节点
//        while(tmp_old != null) {
//            if ( tmp_old.random != null) {
//                tmp_old.next.random = tmp_old.random.next;
//            }
//            tmp_old = tmp_old.next.next;
//        }
//
//        tmp_old = head;
//        tmp_new = newHead;
//        while(tmp_old != null) {
//            tmp_old.next = tmp_old.next.next;
//            tmp_new.next = tmp_new.next == null ? null : tmp_new.next.next;
//            tmp_old = tmp_old.next;
//            tmp_new = tmp_new.next;
//        }
//        return newHead;
//    }
//
//}
//
//class Node {
//    int val;
//    Node next;
//    Node random;
//
//    public Node(int val) {
//        this.val = val;
//        this.next = null;
//        this.random = null;
//    }
//}