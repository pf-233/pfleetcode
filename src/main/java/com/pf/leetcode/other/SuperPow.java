package com.pf.leetcode.other;

import com.pf.leetcode.tree.TreeNode;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class SuperPow {

    Character left = 'L';
    Character right = 'R';
    Character parent = 'U';

    public static void main(String[] args) {
        SuperPow superPow = new SuperPow();
//        TreeNode node = new TreeNode(5);
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(3);
//        TreeNode node4 = new TreeNode(6);
//        TreeNode node5 = new TreeNode(4);
//        node.left=node1;
//        node.right=node2;
//        node1.left=node3;
//        node2.left =node4;
//        node2.right=node5;
        System.out.println(superPow.truncateSentence("Hello how are you Contestant", 4));
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        node1.left = node2;
        System.out.println(superPow.getDirections(node1, 2, 1));;
    }

    public String truncateSentence(String s, int k) {
        int endIndex = 0;
        int now = 0;
        while (endIndex < s.length() && k > 0) {
            now = 0;
            while (endIndex < s.length() && s.charAt(endIndex) == ' ') {
                now++;
                endIndex++;
            }
            if (now > 0) {
                k -= 1;
            } else {
                endIndex++;
            }
        }
        return s.substring(0, endIndex - now);
    }

    public String getDirections(TreeNode root, int startValue, int destValue) {
        LinkedList<Character> listStart = new LinkedList();
        LinkedList<Character> listDest = new LinkedList();
        findValue(root, startValue, listStart);
        findValue(root, destValue, listDest);
        return merge(listStart, listDest);
    }

    String merge(LinkedList<Character> listStart,  LinkedList<Character> listDest) {
        StringBuilder sb = new StringBuilder();
        while (listDest.size() > 0 && listStart.size() > 0 && listStart.getFirst().equals(listDest.getFirst())) {
            listStart.removeFirst();
            listDest.removeFirst();
        }
        for (int i = 0; i < listStart.size(); i++) {
            sb.append(parent);
        }
        return sb.toString() + getString(listDest);
    }

    String getString(LinkedList<Character> list) {
        StringBuilder sb = new StringBuilder();
        for (Character character : list) {
            sb.append(character);
        }
        return sb.toString();
    }

    boolean findValue(TreeNode node, int value, LinkedList<Character> list) {
        if (node == null) {
            return false;
        }
        if (node.val == value) {
            return true;
        }
        list.add(left);
        if (findValue(node.left, value, list)) {
            return true;
        }
        list.removeLast();

        list.add(right);
        if (findValue(node.right, value, list)) {
            return true;
        }
        list.removeLast();
        return false;
    }

    public int[] findEvenNumbers(int[] digits) {
        int[] count = new int[10];
        for (int i = 0; i < digits.length; i++) {
            count[digits[i]]++;
        }

        Set<Integer> set = new HashSet();
        for (int i = 1; i < count.length; i++) {
            count[i]--;
            for (int j = 0; j < count.length; j++) {
                count[j]--;
                for (int k = 0; k < count.length; k += 2) {
                    if (count[i] >= 0 && count[j] >= 0 && count[k] > 0) {
                        set.add(i * 100 + j * 10 + k);
                    }
                }
                count[j]++;
            }
            count[i]++;
        }
        int[] res = new int[set.size()];
        int top = 0;
        for (Integer ind : set) {
            res[top++] = ind;
        }

        Arrays.sort(res);
        return res;
    }
}
