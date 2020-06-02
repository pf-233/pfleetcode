package com.pf.leetcode.stack;

import java.util.Stack;

/**
 * @author pf
 * @date 2020-05-28 13:00
 **/
public class DecodeString {

    public static void main(String[] args) {
        System.out.println(decodeString("3[a]2[bc]"));
    }
    public static String decodeString(String s) {
        StringBuffer sb = new StringBuffer();
        Stack<String> stack = new Stack();
        int pre = 0;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            while(i < s.length() && ch >= '0' && ch <= '9'){
                pre = pre * 10 + (ch - '0');
                i++;
                ch = s.charAt(i);
            }
            //数字入栈
            if(pre > 0){
                stack.push(pre+"");
                stack.push("[");
                pre = 0;
                continue;
            }
            //非空就要么入栈要么出栈
            if(!stack.empty()){
                if(ch != ']'){
                    stack.push(ch + "");
                } else {
                    popSubString(stack, sb);
                }
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    static void popSubString(Stack<String> stack, StringBuffer sb){
        if(stack.empty()){
            return;
        }
        String str = new String();
        while(!stack.peek().equals("[")){
            System.out.println(stack.peek());
            str = stack.pop() + str;
        }
        //排除[
        stack.pop();
        System.out.println(str);
        int count = new Integer(stack.pop());
        System.out.println(count);
        String res = "";
        for(int i = 0; i < count; i++){
            res += str;
        }
        //空了就直接入sb
        if(stack.empty()){
            sb.append(res);
        } else {
            //非空是子串继续放进去
            stack.push(res);
        }
    }
}
