package com.pf.leetcode.contest.niuke;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Contest8 {

    public static void main(String[] args) {
        Contest8 contest8 = new Contest8();
//        System.out.println(contest8.solve(3,7,new int[]{9,4,9}));
//        System.out.println(contest8.solve(3,5,new int[]{5,4,3}));
        System.out.println(contest8.Encode("nowcoder", "rfrvhbiim"));
    }
    public String Encode (String key, String str) {
        // write code here
        char[][] grid = new char[5][5];
        int tmp = 0;
        int[] arr = new int[26];
        Map<Character, Integer> map = new HashMap();
        for(int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if(ch == 'j') {
                ch = 'i';
            }
            if (arr[ch - 'a'] == 1) {
                continue;
            }
            arr[ch - 'a'] = 1;
            grid[tmp / 5][tmp % 5] = ch;
            map.put(ch, tmp);
            tmp++;
        }
        for(int i = 0; i < 26; i++) {
            if(i == ('j' - 'a')) {
                continue;
            }
            if(arr[i] == 0) {
                char ch = (char)(i + 'a');
                grid[tmp / 5][tmp % 5] = ch;
                map.put(ch, tmp);
                tmp++;
            }
        }
        return encode(str, map, grid);
    }

    private String encode(String str, Map<Character, Integer> map, char[][] grid) {
        StringBuffer sb = new StringBuffer();

        str = str.replaceAll("j", "i");
        for(int i = 0; i < str.length(); i += 2) {
            if (i + 1 == str.length()) {
                sb.append(str.charAt(i));
                break;
            } else if (str.charAt(i) == str.charAt(i + 1)) {
                sb.append(str.charAt(i));
                sb.append(str.charAt(i + 1));
                continue;
            }
            char c1 = str.charAt(i);
            char c2 = str.charAt(i + 1);

            int tmp1 = map.get(c1);
            int tmp2 = map.get(c2);

            int row1 = tmp1 / 5;
            int col1 = tmp1 % 5;
            int row2 = tmp2 / 5;
            int col2 = tmp2 % 5;

            if(row1 == row2) {
                sb.append(grid[row1][(col1 + 1) % 5]);
                sb.append(grid[row1][(col2 + 1) % 5]);
            } else if (col1 == col2) {
                sb.append(grid[(row1 + 1) % 5][col1]);
                sb.append(grid[(row2 + 1) % 5][col2]);
            } else {
                sb.append(grid[row1][col2]);
                sb.append(grid[row2][col1]);
            }

        }
        return sb.toString();
    }

    public int solve (int n, int x, int[] a) {
        // write code here
        Arrays.sort(a);
        int sum = 0;
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum += a[i];
            System.out.println("i:" + i + "a[]:" + a[i] + "sum:" + sum);
            count++;
            if (sum / count < x) {
                count--;
                break;
            }
        }
        return count > 0 ? count : 0;
    }
}
