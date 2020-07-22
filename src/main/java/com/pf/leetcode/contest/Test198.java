package com.pf.leetcode.contest;

import java.util.*;

public class Test198 {
    public static void main(String[] args) {
        Test198 test198 = new Test198();
        int[][] edges = new int[][] {
                {0,2},
                {0,3},
                {1,2}
        };
//        System.out.println(test198.countSubTrees(4, edges, "aeed"));
        System.out.println(test198.maxNumOfSubstrings("adefaddaccc"));
        System.out.println(test198.maxNumOfSubstrings("abbaccd"));
    }



    public List<String> maxNumOfSubstrings(String s) {
        List<String> resList = new ArrayList();
        int[] first = new int[26];
        int[] end = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(end, -1);
        for(int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if(first[index] == -1) {
                first[index] = i;
                end[index] = i;
            } else {
                end[index] = i;
            }
        }
        for(int i = 0; i < 26; i++) {
            if (first[i] != -1) {
                for (int j = first[i]; j <= end[i]; j++) {
                    int tmp = s.charAt(j) - 'a';
                    if (first[tmp] > end[i] || end[tmp] < first[i]) {
                        continue;
                    }
                    end[i] = Math.max(end[tmp], end[i]);
                    if (first[tmp] < first[i]) {
                        first[i] = first[tmp];
                        j = first[tmp];
                    }
                }
            }
        }
        List<CharNode> charNodeEnd = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (first[i] > -1) {
                CharNode tmpNode = new CharNode(first[i], end[i], (char) ('a' + i));
                charNodeEnd.add(tmpNode);
            }
        }

        charNodeEnd.sort((a,b)->a.end - b.end);

        int nowInd = -1;
        for (CharNode charNode : charNodeEnd) {
            if (charNode.start > nowInd) {
                nowInd = charNode.end;
                resList.add(s.substring(charNode.start, charNode.end + 1));
            }
        }
        return resList;
//        int[] fo = new int[26];
//        int[] eo = new int[26];
//        int[] tmp = new int[26];
//        int[] tmpe = new int[26];
//        Set<Integer> set = new HashSet<>();
//        for(int i = 0; i < 26; i++){
//            tmp[i] = first[i];
//            tmpe[i] = end[i];
//        }
//        Arrays.sort(tmp);
//        Arrays.sort(tmpe);
//        for (int i = 0; i < 26; i++) {
//            for (int j = 0; j < 26 && first[j] > -1; j++) {
//                if (tmp[i] == first[j]) {
//                    fo[i] = j;
//                }
//                if (tmpe[i] == end[j]) {
//                    eo[i] = j;
//                }
//            }
//        }
//        int ftop = 0;
//        int etop = 0;
//
//        while (etop < 26) {
//            if (eo[etop] == -1) {
//                etop++;
//                continue;
//            }
//            int i = ftop;
//            for (; i < 26; i++) {
//                if (eo[etop] == fo[i]) {
//                    ftop = i + 1;
//                    etop++;
//                    break;
//                }
//            }
//            if (i == 26) {
//                etop++;
//            } else {
//                set.add(eo[etop - 1]);
//            }
//        }
//        for (Integer ind : set) {
//            resList.add(s.substring(first[ind], end[ind] + 1));
//        }
//        return resList;
    }

    class CharNode {
        int start;
        int end;
        char val;

        CharNode(int s, int e, char c) {
            start = s;
            end = e;
            val = c;
        }
    }
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        int[] res = new int[n];
        Node[] nodes = new Node[n];
        for(int i = 0; i < n; i++) {
            nodes[i] = new Node(labels.charAt(i));
        }
        Map<Integer, List<Integer>> edgeMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList();
        for(int i = 0; i < edges.length; i++) {
            int fa = edges[i][0];
            int son = edges[i][1];
            List list = edgeMap.getOrDefault(fa, new ArrayList<>());
            list.add(son);
            edgeMap.put(fa, list);
            list = edgeMap.getOrDefault(son, new ArrayList<>());
            list.add(fa);
            edgeMap.put(son, list);
        }
        queue.offer(0);
        int[] vis = new int[n];
        vis[0] = 1;
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            List<Integer> list = edgeMap.getOrDefault(tmp, new ArrayList<>());
            for (Integer in : list) {
                if (vis[in] == 0) {
                    nodes[tmp].sons.add(nodes[in]);
                    queue.offer(in);
                    vis[in] = 1;
                }
            }
        }
        houxu(nodes[0]);

        for (int i = 0; i < n; i++) {
            res[i] = nodes[i].nums[nodes[i].val - 'a'];
        }
        return res;
    }

    void  houxu(Node root) {
        for (int i = 0; i < root.sons.size(); i++) {
            Node tmp = root.sons.get(i);
            houxu(tmp);
            for (int j = 0; j < root.nums.length; j++) {
                root.nums[j] += tmp.nums[j];
            }
        }
    }
    class Node {
        char val;
        List<Node> sons;
        int[] nums;

        Node(char val) {
            this.val = val;
            sons = new ArrayList<>();
            nums = new int[26];
            nums[val - 'a']++;
        }
    }
}
