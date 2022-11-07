//package com.pf.leetcode.tree;
//
//import java.util.*;
//
//public class PalindromePairs {
//
//    public static void main(String[] args) {
//        PalindromePairs palindromePairs = new PalindromePairs();
//        String[] words = new String[] {"abcd","dcba","lls","s","sssll"};
//        System.out.println(palindromePairs.palindromePairs(words));
//    }
//
//
//    public List<List<Integer>> palindromePairs(String[] words) {
//        List<List<Integer>> list = new ArrayList();
//        if (words == null) {
//            return list;
//        }
//        int len = words.length;
//        if (len < 2) {
//            return list;
//        }
//        Node root = geneateTree(words);
//        for (int i = 0; i < len; i++) {
//            find(new StringBuffer(words[i]).reverse().toString(), root, list, i);
//        }
//        return list;
//    }
//
//    void find(String str, Node root, List<List<Integer>> list, int index) {
//        Node pre = root;
//        Node tmp = null;
//        for (int i = 0; i < str.length(); i++) {
//            char tmpch = str.charAt(i);
//            tmp = pre.next.get(tmpch);
//            if (tmp != null) {
//                pre = tmp;
//            } else {
//                //当str比当前长的时候
//                String many = new StringBuffer(str.substring(i, str.length())).reverse().toString();
//                if(pre.list.size() > 0 && isHui(many)) {
//                    for(Integer tmpIndex : pre.list) {
//                        if(index == tmpIndex) {
//                            continue;
//                        }
//                        list.add(Arrays.asList(tmpIndex, index));
//                    }
//                }
//                return;
//            }
//        }
//
//        addRes(list, pre.list, index);
//        for (Node tmpNode : pre.next.values()) {
//            findNodeHui(tmpNode, list, index, "");
//        }
//    }
//
//    Set<Integer> findNodeHui(Node node, List<List<Integer>> list, int index, String now) {
//        if (node.nextHuiSet.size() > 0) {
//            addRes(list, node.nextHuiSet, index);
//            return node.nextHuiSet;
//        }
//        Set<Integer> nowSet = new HashSet();
//        String tmp = now + node.ch;
//        if (node.list.size() > 0 && isHui(tmp)) {
//            nowSet.addAll(node.list);
//            addRes(list, nowSet, index);
//        }
//        for (Node tmpNode : node.next.values()) {
//            nowSet.addAll(findNodeHui(tmpNode, list, index, tmp));
//        }
//        node.nextHuiSet = nowSet;
//        return nowSet;
//    }
//
//    boolean isHui(String str) {
////        if(str.equals("")){
////            return false;
////        }
//        return str.equals(new StringBuffer(str).reverse().toString());
//    }
//
//
//    Node geneateTree(String[] words) {
//        int len = words.length;
//        Node root = new Node('*');
//        Node tmp = null;
//        Node pre = null;
//        int index = 0;
//        for (String tmpStr : words) {
//            pre = root;
//            if (tmpStr.length() == 0) {
//                continue;
//            }
//            for (int i = 0; i < tmpStr.length(); i++) {
//                char tmpch = tmpStr.charAt(i);
//                if (pre.next.get(tmpch) != null) {
//                    pre = pre.next.get(tmpch);
//                } else {
//                    tmp = new Node(tmpch);
//                    pre.next.put(tmpch, tmp);
//                    pre = tmp;
//                }
//            }
//            pre.list.add(index++);
//        }
//        return root;
//    }
//
//    static class Node {
//        private Map<Character, Node> next;
//        private char ch;
//        private Set<Integer> list;
//        private Set<Integer> nextHuiSet;
//
//        Node(char ch) {
//            nextHuiSet = new HashSet();
//            next = new HashMap();
//            this.ch = ch;
//            list = new HashSet();
//        }
//    }
//
////    public List<List<Integer>> palindromePairs(String[] words) {
////        List<List<Integer>> list = new ArrayList();
////        if (words == null) {
////            return list;
////        }
////        int len = words.length;
////        if (len < 2) {
////            return list;
////        }
////
////        Node root = geneateTree(words);
////        for (int i = 0; i < len; i++) {
////            find(new StringBuffer(words[i]).reverse().toString(), root, list, i);
////        }
////        return list;
////    }
////
////    void find(String str, Node root, List<List<Integer>> list, int index) {
////        Node pre = root;
////        Node tmp = null;
////        for (int i = 0; i < str.length(); i++) {
////            char tmpch = str.charAt(i);
////            tmp = pre.next.get(tmpch);
////            if (tmp != null) {
////                pre = tmp;
////            } else {
////                String many = new StringBuffer(str.substring(i, str.length())).reverse().toString();
////                if(pre.list.size() > 0 && isHui(many)) {
////                   addRes(list, pre.list, index);
////                }
////                return;
////            }
////        }
////
////        addRes(list, pre.list, index);
////
////        for (Node tmpNode : pre.next.values()) {
////            findNodeHui(tmpNode, list, index, "");
////        }
////    }
////
////    Set<Integer> findNodeHui(Node node, List<List<Integer>> list, int index, String now) {
////        if (node.nextHuiSet.size() > 0) {
////            addRes(list, node.nextHuiSet, index);
////            return node.nextHuiSet;
////        }
////        Set<Integer> nowSet = new HashSet();
////        String tmp = now + node.ch;
////        if (node.list.size() > 0 && isHui(tmp)) {
////            nowSet.addAll(node.list);
////            addRes(list, nowSet, index);
////        }
////        for (Node tmpNode : node.next.values()) {
////            nowSet.addAll(findNodeHui(tmpNode, list, index, tmp));
////        }
////        node.nextHuiSet = nowSet;
////        return nowSet;
////    }
////
////    boolean isHui(String str) {
////        return str.equals(new StringBuffer(str).reverse().toString());
////    }
////
////    void addRes(List<List<Integer>> list, Collection<Integer> tmpList, int index) {
////        for (Integer tmpindex : tmpList) {
////            if (tmpindex == index) {
////                continue;
////            } else {
////                list.add(Arrays.asList(index, tmpindex));
////            }
////        }
////    }
////
////
////    Node geneateTree(String[] words) {
////        int len = words.length;
////        Node root = new Node('*');
////        Node tmp = null;
////        Node pre = null;
////        int index = 0;
////        for (String tmpStr : words) {
////            pre = root;
////            if (tmpStr.length() == 0) {
////                continue;
////            }
////            for (int i = 0; i < tmpStr.length(); i++) {
////                char tmpch = tmpStr.charAt(i);
////                if (pre.next.get(tmpch) != null) {
////                    pre = pre.next.get(tmpch);
////                } else {
////                    tmp = new Node(tmpch);
////                    pre.next.put(tmpch, tmp);
////                    pre = tmp;
////                }
////            }
////            pre.list.add(index++);
////        }
////        return root;
////    }
////
////    static class Node {
////        private Map<Character, Node> next;
////        private char ch;
////        private Set<Integer> list;
////        private Set<Integer> nextHuiSet;
////
////        Node(char ch) {
////            nextHuiSet = new HashSet();
////            next = new HashMap();
////            this.ch = ch;
////            list = new HashSet();
////        }
////    }
//}
