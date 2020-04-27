package com.pf.leetcode.array;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

/**
 * @author pf
 * @date 2020-04-18 15:17
 **/
public class Test {
    public static void main(String[] args) {
//        5
//                [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]]
//        3

//        []
//[]
        int[][] increase= new int[][]{
                {2,8,4},
                {2,5,0},
                {10,9,8}
        };
        int[][] requirements=new int[][]{
                {2,11,3},
                {15,10,7},
                {9,17,12},
                {8,1,14}
        };
//        System.out.println(getTriggerTime(increase, requirements));
//        System.out.println(minJump(new int[]{2, 5, 1, 1, 1, 1}));
//        minNumberOfFrogs("croakcroak");
        char[][] grid=new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'},

        };
//        System.out.println(numIslands(grid));
//        System.out.println(longestPalindrome("babad"));
        int[] nums = new int[]{1,1,2,1,1};
        System.out.println(numberOfSubarrays(nums, 3));

    }

    public static int numberOfSubarrays(int[] nums, int k) {
        int len = nums.length;
        if(len==0){
            return 0;
        }

        int count=0;
        int[] arr = new int[len+2];
        int top=0;
        arr[top++]=-1;
        int i=0;
        //先找全部奇数数组
        for(;i<len;i++){
            if(nums[i]%2==1){
                arr[top++]=i;
            }
        }
        arr[top++]=len;
        //奇数长度小于等于k+1直接为0  一开始的-1和最后的len
        if(top <= k+1){
            return count;
        }

        for(i = k;i < top-1; i++){
            //包含start-end 的数组个数   1，3
            count+= (arr[i-(k-1)] - arr[i-k]) * (arr[i+1] - arr[i]);
        }
        return count;
    }

    public  static String longestPalindrome(String s) {
        if(s.length()==0){
            return "";
        }
        int[] dp = new int[s.length()+1];
        dp[1]=1;
        int max = 0;
        int index = 0;
        for(int i=2;i<dp.length;i++){
            if(s.charAt(i-1 - dp[i-1]) == s.charAt(i-1)){
                dp[i]=dp[i-1]+2;
            } else {
                dp[i]=1;
            }
            if(max<dp[i]){
                max=dp[i];
                index=i;
            }
        }
        return s.substring(index-1- dp[index],index-1);
    }

    public static int numIslands(char[][] grid) {
        if(grid.length==0 || grid[0].length==0){
            return 0;
        }
        //方向数组
        int[] row = new int[]{-1,1,0,0};
        int[] col = new int[]{0,0,-1,1};
        LinkedList<Integer> list = new LinkedList();
        int n=grid.length;
        int m=grid[0].length;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]=='1'){
                    list.addLast(i*m+j);
                }
            }
        }
        //全部都为1是块是1 全为0是没有
        if(list.size()==n*m){
            return 1;
        } else if(list.size()==0){
            return 0;
        }

        int count=0;
        while(list.size()>0){
            int now = list.pop();
            int nowRow = now/m;
            int nowCol = now%m;

            if(grid[nowRow][nowCol]!='1'){
                continue;
            }
            grid[nowRow][nowCol]='*';
            LinkedList<Integer> tempList = new LinkedList();
            tempList.add(now);
            while(tempList.size()>0){
                int tempNow = tempList.pop();
                int tempRow = tempNow/m;
                int tempCol = tempNow%m;
                for(int i=0;i<4;i++){
                    if(tempRow>=0 && tempRow<n && tempCol>=0 && tempCol<m && grid[tempRow][tempCol]=='1'){
                        tempList.add(tempRow*m+tempCol);
                        grid[tempRow][tempCol]= '*';
                    }
                }
            }
            count++;
        }
        return  count;
    }

    public static int minNumberOfFrogs(String croakOfFrogs) {
        int[] count=new int[4];
        int min = 0;
        for(int i=0;i<croakOfFrogs.length();i++){
            if(croakOfFrogs.charAt(i)=='c'){
                count[0]++;
            } else if(croakOfFrogs.charAt(i)=='r'){
                count[1]++;
            }else if(croakOfFrogs.charAt(i)=='o'){
                count[2]++;
            }else if(croakOfFrogs.charAt(i)=='a'){
                count[3]++;
            }else if(croakOfFrogs.charAt(i)=='k'){
                for(int j=0;j<4;j++){
                    count[j]--;
                }
            }
            int res = check(count);
            if(res == -1){
                return -1;
            } else{
                min = Math.max(min, res);
            }
        }
        return min;
    }

    static int check(int[] count){
        for(int i=1;i<count.length;i++){
            if(count[i]>count[i-1]){
                return -1;
            }
        }
        return count[0];
    }

    public List<List<String>> displayTable(List<List<String>> orders) {
        List<List<String>> res = new ArrayList<>();
        List<String> tableName = new ArrayList();
        Set<String> menuSet = new TreeSet();
        //桌号          菜单，数量
        Map<Integer, Map<String, Integer>> tableMenuMap = new HashMap<>();
        for(int i=0;i<orders.size();i++){
            List<String> order = orders.get(i);
            String table = order.get(1);
            Integer tableNum = new Integer(table);
            String menu = order.get(2);
            menuSet.add(menu);

            Map<String, Integer> tempMap = tableMenuMap.getOrDefault(tableNum,  new HashMap<>());
            Integer num = tempMap.getOrDefault(menu, 0);
            tempMap.put(menu, ++num);
            tableMenuMap.put(tableNum, tempMap);
        }

        List<String> list = menuSet.stream().collect(Collectors.toList());
        list.sort((a1,a2)->a1.compareTo(a2));
        tableName.add("Table");
        for (int i=0;i<list.size();i++){
            tableName.add(list.get(i));
        }
        res.add(tableName);

        List<Integer> table = tableMenuMap.keySet().stream().collect(Collectors.toList());
        list.sort((a1,a2)->a1.compareTo(a2));

        List<String> tempList = null;
        for(int i=0;i<table.size();i++){
            tempList=new ArrayList<>();
            tempList.add(table.get(i)+"");
            Map<String, Integer> tempMap = tableMenuMap.getOrDefault(table.get(i),  new HashMap<>());
            for(int j=0;j<list.size();j++){
                int tempCount = tempMap.getOrDefault(list.get(j), 0);
                tempList.add(""+tempCount);
            }
            res.add(tempList);
        }
        return  res;
    }

    public static int minJump(int[] jump) {
        LinkedList<Integer> list = new LinkedList();
        list.addLast(jump[0]);
        list.addLast(-1);

        Set<Integer> set = new HashSet();
        set.add(jump[0]);
        int count = 1;
        while(list.size()>0){
            int index = list.pollFirst();
            if(index>jump.length-1){
                return count;
            } else if(index==-1){
                count++;
                list.addLast(-1);
            } else {
                int temp = index+jump[index];
                if(!set.contains(temp)){
                    set.add(temp);
                    list.addLast(temp);
                }
                for(int i=index-1;i>=0;i--){
                    if(!set.contains(i)){
                        set.add(i);
                        list.addLast(i);
                    }
                }
            }
        }
        return count;
    }

    public static int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int[] res = new int[requirements.length];
        int[][] preIncrease=new int[increase.length+1][3];
        preIncrease[0][0]=0;
        preIncrease[0][1]=0;
        preIncrease[0][2]=0;
        for(int i=1;i<=increase.length;i++){
            preIncrease[i][0]=preIncrease[i-1][0]+increase[i-1][0];
            preIncrease[i][1]=preIncrease[i-1][1]+increase[i-1][1];
            preIncrease[i][2]=preIncrease[i-1][2]+increase[i-1][2];
        }

        for(int i=0;i<requirements.length;i++){
            int left=0;
            int right= preIncrease.length-1;
            while(left < right){
                int mid = (left+right)/2;
                if(preIncrease[mid][0] >=requirements[i][0] && preIncrease[mid][1] >=requirements[i][1] && preIncrease[mid][2] >=requirements[i][2]){
                    right = mid-1;
                } else {
                    left=mid+1;
                }
            }
            res[i]=right;
        }
        return res;
    }
}
