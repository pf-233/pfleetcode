package com.pf.leetcode.tu;

import java.util.*;

public class FindItinerary {

    public static void main(String[] args) {

        List<List<String>> tic = new ArrayList<>();
//        tic.add(Arrays.asList("MUC","LHR"));
//        tic.add(Arrays.asList("JFK","MUC"));
//        tic.add(Arrays.asList("SFO","SJC"));
//        tic.add(Arrays.asList("LHR", "SFO"));

//        [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[]]
        tic.add(Arrays.asList("EZE","ADL"));
        tic.add(Arrays.asList("EZE","ANU"));
        tic.add(Arrays.asList("EZE","HBA"));
        tic.add(Arrays.asList("EZE","TIA"));

        tic.add(Arrays.asList("ANU","AUA"));
        tic.add(Arrays.asList("ANU","EZE"));
        tic.add(Arrays.asList("ANU","JFK"));

        tic.add(Arrays.asList("ADL","ANU"));
        tic.add(Arrays.asList("ADL","EZE"));
        tic.add(Arrays.asList("ADL","EZE"));

        tic.add(Arrays.asList("AXA","AUA"));
        tic.add(Arrays.asList("AXA","EZE"));
        tic.add(Arrays.asList("AXA","TIA"));

        tic.add(Arrays.asList("TIA","ADL"));
        tic.add(Arrays.asList("TIA","AUA"));

        tic.add(Arrays.asList("AUA","ADL"));
        tic.add(Arrays.asList("AUA","ANU"));
        tic.add(Arrays.asList("AUA","AXA"));

        tic.add(Arrays.asList("JFK","AXA"));
        tic.add(Arrays.asList("JFK","AXA"));

        List<String> res = findItinerary(tic);

        System.out.println(res);
    }

    static Map<String, Node> nodeMap;
    static List<String> res;
    static int len;
    public static List<String> findItinerary(List<List<String>> tickets) {
        if(tickets.size() == 0){
            return new ArrayList();
        }
        nodeMap = new HashMap();
        res = new ArrayList(tickets.size() + 1);
        len = tickets.size() + 1;
        for(List<String> visList : tickets){
            Node temp = nodeMap.getOrDefault(visList.get(0), new Node(visList.get(0)));
            temp.toList.add(visList.get(1));
            nodeMap.put(visList.get(0), temp);
        }
        for (Node node : nodeMap.values()){
            Collections.sort(node.toList);
        }
        res.add("JFK");
        dfs(nodeMap.get("JFK"), 1);
        return res;
    }
//["JFK","MUC","LHR","SFO","SJC"]
//["JFK","AXA","AUA","ADL","ANU","AUA","ANU","EZE","ADL","EZE","ANU","JFK","AXA","EZE","TIA","AUA","AXA","TIA","ADL","EZE","HBA"]

    static boolean dfs(Node node, int deep){
        if(node.toList.size() == node.index){
            return deep == len;
        }
        boolean flag = false;
        for(int i = 0; i < node.toList.size(); i++){
            String str =  node.toList.get(i);
            if(str == null){
                continue;
            }
            node.index++;
            node.toList.set(i, null);
            res.add(str);
            flag = dfs(nodeMap.getOrDefault(str, new Node(str)), deep + 1);
            if(flag){
                return flag;
            }
            node.index--;
            node.toList.set(i, str);
            res.remove(deep);
        }
        return false;
    }

//    public static List<String> findItinerary(List<List<String>> tickets) {
//        if(tickets.size() == 0){
//            return new ArrayList();
//        }
//        Map<String, Node> nodeMap = new HashMap();
//        List<String> res = new ArrayList(tickets.size() + 1);
//        for(List<String> visList : tickets){
//            Node temp = nodeMap.getOrDefault(visList.get(0), new Node(visList.get(0)));
//            temp.toList.add(visList.get(1));
//            nodeMap.put(visList.get(0), temp);
//        }
//        for (Node node : nodeMap.values()){
//            Collections.sort(node.toList);
//        }
//        Queue<Node> que = new LinkedList();
//        que.offer(nodeMap.get("JFK"));
//        while(!que.isEmpty()){
//            Node temp = que.poll();
//            res.add(temp.val);
//            if(temp.index < temp.toList.size()){
//                String nextCity = temp.toList.get(temp.index++);
//                que.offer( nodeMap.getOrDefault(nextCity, new Node(nextCity)));
//            }
//        }
//        return res;
//    }
    static class Node {
        private String val;
        private int index;
        private List<String> toList;
        Node(String val){
            this.val = val;
            index = 0;
            toList = new ArrayList();
        }
    }
}
