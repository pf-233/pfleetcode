package com.pf.leetcode.stack;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DinnerPlates {

    public static void main(String[] args) {
        DinnerPlates dinnerPlates = new DinnerPlates(2);
        dinnerPlates.push(1);
        dinnerPlates.push(2);
        dinnerPlates.push(3);
        dinnerPlates.push(4);
        dinnerPlates.push(7);
        dinnerPlates.popAtStack(8);
        dinnerPlates.push(20);
        dinnerPlates.push(21);
        dinnerPlates.popAtStack(0);
        dinnerPlates.popAtStack(2);
    }


    int capacity;
    Map<Integer, LinkedList<Integer>> indexMap;
    TreeSet<Integer> set;
    int top;
    public DinnerPlates(int capacity) {
        this.capacity = capacity;
        indexMap = new HashMap();
        new ConcurrentHashMap<>();
        set = new TreeSet();
        top = 0;
    }

    public void push(int val) {
        int index = set.size() == 0 ? top : set.first();
        LinkedList<Integer> tempStack = indexMap.getOrDefault(index, new LinkedList());
        // 如果满了不可能在set里所以一定是top
        if (tempStack.size() == capacity) {
            tempStack = new LinkedList();
            index = ++top;
        }
        tempStack.addLast(val);
        indexMap.put(index, tempStack);
        if (index != top && tempStack.size() == capacity) {
            set.remove(index);
        }
    }

    public int pop() {
        LinkedList<Integer> tempStack = null;
        while (top >= 0) {
            tempStack = indexMap.getOrDefault(top, new LinkedList());
            if (tempStack.size() == 0) {
                indexMap.remove(top);
                set.remove(top);
                top--;
            } else {
                break;
            }
        }
        int ans = -1;
        if (top >= 0) {
            ans = tempStack.removeLast();
            if (tempStack.size() > 0)
                set.add(top);
            else
                set.remove(top);
        }
        return ans;
    }

    public int popAtStack(int index) {
        LinkedList<Integer> tempStack = indexMap.getOrDefault(index, new LinkedList());
        set.add(index);
        return tempStack.size() == 0 ? -1 : tempStack.removeLast();
    }
}
