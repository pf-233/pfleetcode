package com.pf.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

public class PathInZigZagTree {

    public static void main(String[] args) {
        PathInZigZagTree path = new PathInZigZagTree();
        List<Integer> res = path.pathInZigZagTree(14);
    }
    public List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> res = new LinkedList();
        dg(label, res, true, 0, 0);
        return res;
    }

    public int dg(int label, LinkedList<Integer> res, boolean isLeftToRight, int sum, int deep) {
        int tmp = 1 << deep;
        if (label <= sum + tmp) {
            res.addFirst(label);
            return isLeftToRight ? label - sum : tmp - (label - sum) + 1;
        }
        int next = dg(label, res, !isLeftToRight, sum + tmp, deep + 1);
        int now = ((next + 1) / 2);
        if (isLeftToRight) {
            res.addFirst(sum + now);
        } else {
            res.addFirst(sum + tmp - now + 1);
        }
        return now;
    }
}
