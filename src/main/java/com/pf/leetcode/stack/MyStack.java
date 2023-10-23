package com.pf.leetcode.stack;

import java.util.LinkedList;

/**
 * author：11413
 * date：10/11/2023
 * Description:
 */
public class MyStack {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top());
        System.out.println(myStack.pop());
    }
    LinkedList<Integer> q1;
    LinkedList<Integer> q2;
    public MyStack() {
        q1 = new LinkedList();
        q2 = new LinkedList();
    }

    public void push(int x) {
        while (!q1.isEmpty()) {
            q2.offer(q1.pop());
        }
        q1.offer(x);
        while (!q2.isEmpty()) {
            q1.offer(q2.pop());
        }
    }

    public int pop() {
        return q1.pop();
    }

    public int top() {
        return q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}
