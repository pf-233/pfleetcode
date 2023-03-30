package com.pf.leetcode.design;

import java.util.LinkedList;
import java.util.ListIterator;

class StreamChecker {
    Node root;
    LinkedList<Integer> list;
    static final int limit = 204;

    public static void main(String[] args) {
        String[] words = new String[]{"ab","ba","aaab","abab","baa"};
        StreamChecker streamChecker = new StreamChecker(words);
        System.out.println(streamChecker.query('a'));;
    }
    public StreamChecker(String[] words) {
        root = new Node(false);
        list = new LinkedList();
        for (int i = 0; i < words.length; i++) {
            Node tmp = root;
            for (int j = words[i].length() - 1; j >= 0; j--) {
                char ch = words[i].charAt(j);
                Node next = tmp.next[ch - 'a'];
                if (next == null) {
                    next = new Node(j == 0);
                    tmp.next[ch - 'a'] = next;
                }
                // 如果当前是0就说明可以是结束节点
                next.isEnd |= (j == 0);
                tmp = next;
            }
        }
    }

    public boolean query(char letter) {
        list.add(letter - 'a');
        if (list.size() > limit) {
            list.removeFirst();
        }

        ListIterator<Integer> iterator = list.listIterator(list.size());
        Node tmp = root;
        boolean flag = true;
        while (iterator.hasPrevious()) {
            int now = iterator.previous();
            System.out.print(now);
            if (tmp.next[now] == null || tmp.next[now].isEnd) {
                flag = tmp.next[now] != null;
                break;
            }
            tmp = tmp.next[now];
        }
        System.out.println();
        return flag;
    }

    class Node {
        private boolean isEnd;
        private Node[] next;

        Node(boolean isEnd) {
            this.isEnd = isEnd;
            next = new Node[26];
        }
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
