package com.pf.leetcode.string;

/**
 * @author pf
 * @date 2020-03-31 19:40
 **/
public class Kmp {
    private int[] getNext(String s){
        int len = s.length();
        int[] next=new int[len];
        next[0]=-1;
        int k=-1;
        for(int i=1;i<len;i++){
            while(k!=-1 && s.charAt(k+1) != s.charAt(i)){
                k=next[k];
            }
            if (s.charAt(k+1) == s.charAt(i)){
                k++;
            }
            next[i]=k;
        }
        return next;
    }
}
