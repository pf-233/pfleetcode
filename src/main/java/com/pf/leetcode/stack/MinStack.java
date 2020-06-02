package com.pf.leetcode.stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author pf
 * @date 2020-05-12 13:15
 **/
class MinStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
//        ["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
//        System.out.println(minStack.getMin());
//        minStack.pop();
//        minStack.top();
//        System.out.println(minStack.getMin());

//        ["MinStack","push","push","push","top","pop","getMin","pop","getMin","pop","push","top","getMin","push","top","getMin","pop","getMin"]
//[[],[2147483646],[2147483646],[2147483647],[],[],[],[],[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]
        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.push(2147483647);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.push(-2147483648);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
    }

    List<Integer> arr;
    LinkedList<Integer> sort;
    int top;
    /** initialize your data structure here. */
    public MinStack() {
        arr = new ArrayList();
        sort = new LinkedList();
        top = 0;
    }

    public void push(int x) {
        int index = midFind(x);
        sort.add(index + 1, x);
        arr.add(top, x);
        System.out.println("push ==========" + x);
        System.out.println(sort.toString());
        System.out.println(arr.toString());
        top++;
    }

    public void pop() {
        int x = arr.get(top - 1);
        sort.remove(midFind(x));
        System.out.println("pop ==========");
        System.out.println(sort.toString());
        System.out.println(arr.toString());
        top--;
    }

    public int top() {
        return arr.get(top - 1);
    }

    public int getMin() {
        return sort.getLast();
    }

    private int midFind(int x){
        if(top == 0){
            return -1;
        }
        int left = 0;
        int right = top - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(sort.get(mid) < x){
                right = mid;
            } else if(sort.get(mid) == x){
                return mid;
            } else {
                left = mid + 1;
            }
        }
        return sort.get(left) >= x ? left : left - 1;
    }
}