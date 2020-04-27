package com.pf.leetcode.dp;

/**
 * 不同路径2
 *
 * @author pf
 * @date 2020-04-22 13:03
 **/
public class UniquePathsWithObstacles {

    public static void main(String[] args) {
//        int[][] grid = new int[][]{
//                {0,0,0},
//                {0,1,0},
//                {0,0,0},
//        };
//        System.out.println(uniquePathsWithObstacles(grid));

        int[][] grid = new int[][]{
                {1,2},
                {1,1},
        };
//        System.out.println(minPathSum(grid));
        System.out.println(numDecodings("12"));
        System.out.println(numDecodings("27"));
        System.out.println(numDecodings("101"));
        System.out.println(numDecodings("110"));
        System.out.println(numDecodings("130"));
        System.out.println(numDecodings("301"));

    }

    public static int numDecodings(String s) {
        char[] ch = s.toCharArray();
        int pre = 0;
        //把连续两个0，和30，40，。。。这些过滤掉直接返回0，还有一开始开头是0的也过滤掉
        int i = 0;
        for (; i < ch.length; i++){
            int now = ch[i] - '0';
            int temp = pre * 10 + now;
            if (temp == 0 || (now == 0 && pre > 2)){
                return 0;
            }
            pre = now;
        }

        String[] noZero = s.split("0");
        int count = 1;
        String temp = "";
        //只有一个
        if (noZero.length == 1){
            temp = noZero[0];
            if (s.charAt(s.length()-1) == '0'){
                temp = temp.substring(0, temp.length()-1);
            }
            return numDecodingsNoZero(temp);
        }
        for (i = 0; i < noZero.length - 1; i++){
            temp = noZero[i];
            temp = temp.substring(0, temp.length()-1);
            count*= numDecodingsNoZero(temp);
        }

        temp = noZero[noZero.length-1];
        if (s.charAt(s.length()-1) == '0'){
            temp = temp.substring(0, temp.length()-1);
        }
        count*= numDecodingsNoZero(temp);
        return count;
    }

    /**
     * 计算单独的
     * @param s
     * @return
     */
    public static int numDecodingsNoZero(String s){
        if (s.length() == 0){
            return 1;
        }
        char[] ch = s.toCharArray();
        int[] dp = new int[s.length() + 1];
        dp[0]=1;
        dp[1]=1;
        for (int i=2; i<dp.length; i++){
            //27,28,29    30以上都是只能单独当个数字
            if (ch[i-2] == '2' && ch[i-1] > '6' || ch[i-2] > '2'){
                dp[i] = dp[i-1];
            } else {
                //i-2存在才加
                dp[i] = dp[i-1] + dp[i-2];
            }
        }
        return dp[dp.length-1];
    }

    public static int minPathSum(int[][] grid) {
        int n = grid.length;
        if (n == 0){
            return 0;
        }
        int m = grid[0].length;
        for(int i = 1; i< n; i++){
            grid[0][i]+= grid[0][i-1];
        }
        for(int i = 1; i < n; i++){
            for(int j = 0; j< m; j++){
                grid[i][j]+= Math.min(j >= 1 ? grid[i][j-1] : Integer.MAX_VALUE, grid[i-1][j]);
            }
        }
        return grid[n-1][m-1];
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        if(n == 0){
            return 0;
        }
        int m = obstacleGrid[0].length;
        if(obstacleGrid[0][0] == 1 || obstacleGrid[n-1][m-1] == 1){
            return 0;
        }
        int[] dp = new int[m];
        for(int i = 0;i < m; i++){
            if(obstacleGrid[0][i] == 0){
                dp[i] = 1;
            } else {
                break;
            }
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(obstacleGrid[i][j] == 0){
                    dp[j] = dp[j] + dp[j-1];
                } else {
                    dp[j] = 0;
                }
            }
        }
        return dp[m-1];
    }
}
