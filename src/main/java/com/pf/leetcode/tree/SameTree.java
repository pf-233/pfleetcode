package com.pf.leetcode.tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 是否是同样的树
 *
 * @author pf
 * @date 2020-03-25 19:21
 **/
public class SameTree {

    public static void main(String[] args) {
//[1,2]
//[1,null,2]
//        List<List<Integer>> list = new LinkedList<>();
////        Collections.reverse(list)
////        TreeNode t = new TreeNode(1);
////        TreeNode t1 = new TreeNode(2);
////
////        TreeNode t2 = new TreeNode(1);
////        TreeNode t3 = new TreeNode(2);
////
////        t.left = t1;
////
////        t2.right = t3;
////        isSameTree(t, t2);

        String[]s = {"aaa", "bbbb"};
        s = Arrays.stream(s).sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        }).collect(Collectors.toList()).toArray(s);
        System.out.println(s);
        System.out.println(hasGroupsSizeX(new int[]{1,2,3,4,4,3,2,1}));
        System.out.println(hasGroupsSizeX(new int[]{1,1,1,2,2,2,3,3}));
        System.out.println(hasGroupsSizeX(new int[]{1}));
        System.out.println(hasGroupsSizeX(new int[]{1,1}));
        System.out.println(hasGroupsSizeX(new int[]{1,1,2,2,2,2}));
    }

    public static boolean hasGroupsSizeX(int[] deck) {
        if(deck.length <= 1){
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<deck.length;i++){
            Integer temp = map.get(deck[i]);
            temp = temp == null ? 1: ++temp;
            map.put(deck[i], temp);
        }

        Integer pre = map.get(deck[0]);
        Integer gcd = 10000;
        int now;
        for(Integer i: map.keySet()){
            now = map.get(i);
            gcd = Math.min(GCD(pre, now), gcd);
            pre = now;
        }
        if(gcd>=2 && gcd < 10000){
            return true;
        } else if(gcd == 10000){
            return pre >= 2;
        } else {
            return false;
        }

    }

    public static int GCD(int m, int n) {
        int result = 0;
        while (n != 0) {
            result = m % n;
            m = n;
            n = result;
        }
        return m;
    }

    public void order(List<List<Integer>> list, TreeNode[] nodes, int size, boolean isLeft) {
        List<Integer> childList = new LinkedList();
        TreeNode[] childNodes = new TreeNode[size * 2];
        int count = 0;
        if (isLeft) {
            for (int i = 0; i < size; i++) {
                childList.add(nodes[i].val);
                if (nodes[i].left != null) {
                    childNodes[count++] = nodes[i].left;
                }
                if (nodes[i].right != null) {
                    childNodes[count++] = nodes[i].right;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                childList.add(nodes[i].val);
                if (nodes[i].left != null) {
                    childNodes[count++] = nodes[i].left;
                }
                if (nodes[i].right != null) {
                    childNodes[count++] = nodes[i].right;
                }
            }
        }
        list.add(childList);
        order(list, childNodes, count, !isLeft);
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == null && q == null;
        }
        ArrayList<TreeNode> list = new ArrayList();
        ArrayList<TreeNode> list1 = new ArrayList();
        list.add(p);
        list1.add(q);
        int count = 0;
        int size = 1;
        while (count < size) {
            p = list.get(count);
            q = list1.get(count);
            if (p == null || q == null) {
                if (p == null && q == null) {
                    count++;
                    continue;
                } else {
                    return false;
                }
            }

            if (p.val == q.val) {
                list.add(p.left);
                list.add(p.right);

                list1.add(q.left);
                list1.add(q.right);
                size += 2;
            } else {
                return false;
            }
            count++;
        }
        return true;
    }
}
