package com.pf.leetcode.array;

public class ReverseWords {
    public static void main(String[] args) {
        ReverseWords reverseWords = new ReverseWords();
        System.out.println(reverseWords.reverseWords("  hello world!  "));
        System.out.println(reverseWords.reverseWords2("the sky is blue"));

    }
    public String reverseWords2(String s) {
        char[] ch = s.toCharArray();
        int len = ch.length;
        int left = 0;
        int right = len - 1;
        StringBuffer sb = new StringBuffer();
        boolean first = true;
        while(right >= 0) {

            while(right >= 0 && ch[right] == ' ') {
                right--;
            }
            int end = right;
            while(right >= 0 && ch[right] != ' ') {
                right--;
            }
            int start = right + 1;
            if(first) {
                first = false;
            } else {
                sb.append(" ");
            }
            sb.append(new String(ch, start, end - start + 1));
        }
        return sb.toString();
    }
    public String reverseWords(String s) {
        char[] ch = s.toCharArray();
        int len = ch.length;
        int left = 0;
        int right = len - 1;
        reverse(ch, left, right);

        while(left < len && ch[left] == ' ') {
            left++;
        }
        while(right >= 0 && ch[right] == ' ') {
            right--;
        }

        int start = left;
        int end = right;
        System.out.println("start: " + start + "  end: " + end);
        for(int i = left; i <= right;) {
            while(i <= right && ch[i] != ' '){
                i++;
            }
            reverse(ch, left, i - 1);
            while(i <= right && ch[i] == ' ') {
                i++;
            }
            left = i;
        }
        return new String(ch, start, right - left + 1);
    }

    void reverse(char[] ch, int left, int right) {
        while(left < right) {
            char tmp = ch[left];
            ch[left] = ch[right];
            ch[right] = tmp;
            left++;
            right--;
        }
    }
}
