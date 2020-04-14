package com.pf.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 所有树
 *
 * @author pf
 * @date 2020-03-24 17:14
 **/
public class AllTree {

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(-3);
//        AllTree allTree = new AllTree();
//        allTree.maxPathSum(root);
//        allTree.list.pollLast()

//        System.out.println(checkOverlap( 1,  1, 1,  1, -3,  2,  -1));
//        System.out.println(numSteps("1101"));
//        String s = "ccbccaccb";
//        System.out.println(s.substring(0, s.length()));

        System.out.println(longestDiverseString(4,42,7));
    }

    public static String longestDiverseString(int a, int b, int c) {
        return huishu("",0,a,b,c);
    }

    static String huishu(String str, int state,int a, int b, int c){
        if(a<0||b<0||c<0){
            return str.substring(0, str.length()-1);
        }
        HashMap map = new HashMap();
        String temp1 = null;
        String temp2 = null;
        String temp3 = null;
        String res = null;
        if(str.length()>=2){
            if(state == 11){
                temp1 = huishu(str+"b", 12,a,b-1,c);
                temp2 = huishu(str+"c", 13,a,b,c-1);
                res = temp1.length() >= temp2.length() ? temp1 : temp2;
            } else if(state == 22){
                temp1 = huishu(str+"a", 21,a-1,b,c);
                temp2 = huishu(str+"c", 23,a,b,c-1);
                res = temp1.length() >= temp2.length() ? temp1 : temp2;
            } else if(state == 33){
                temp1 = huishu(str+"b", 32,a,b-1,c);
                temp2 = huishu(str+"a", 31,a-1,b,c);
                res = temp1.length() >= temp2.length() ? temp1 : temp2;
            } else {
                temp1 = huishu(str+"a", (state%10)*10+1,a-1,b,c);
                temp2 = huishu(str+"b", (state%10)*10+2,a,b-1,c);
                temp3 = huishu(str+"c", (state%10)*10+3,a,b,c-1);
                res = temp1.length() >= temp2.length() ? temp1 : temp2;
                res = res.length()>=temp3.length()?res:temp3;
            }
        } else {
            temp1 = huishu(str+"a", state*10+1,a-1,b,c);
            temp2 = huishu(str+"b", state*10+2,a,b-1,c);
            temp3 = huishu(str+"c", state*10+3,a,b,c-1);
            res = temp1.length() >= temp2.length() ? temp1 : temp2;
            res = res.length()>=temp3.length()?res:temp3;
        }
        return res;
    }

    public static int numSteps(String s) {
        int count = 0;
        char[] chs = new char[s.length() *2+1];
        for(int i=0;i<s.length();i++){
            chs[chs.length-1-i]=s.charAt(s.length()-1-i);
        }
        for(int i=chs.length-1;i>0 && chs[i] != 0;i--){
            if(chs[i-1] == 0){
                break;
            }
            if(chs[i]=='0'){
                count++;
            } else if(chs[i]=='1'){
                count+=2;
                int j = i-1;
                while(j>=0){
                    if(chs[j] == '0' || chs[j]==0){
                        chs[j]='1';
                        break;
                    } else if(chs[j]=='1'){
                        chs[j]='0';
                        j--;
                    }
                }
            }
        }
        return count;
    }

    public static boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        //中心相连距离 <= r+(x2-x1)/2 一定相连
        int[][] jx = {{x1, y1},{x2, y2},{x1, y2},{x2, y1}};

        //有一个角落在圆范围内
        for(int i=0;i<4;i++){
            boolean flaga =  (jx[i][0]-x_center) *(jx[i][0]-x_center)  + (jx[i][1]-y_center) *(jx[i][1]-y_center) <= radius * radius;
            if(flaga){
                return true;
            }
        }

        //在矩形内部
        if(x_center>=x1 && x_center <= x2 && y_center>=y1 && y_center<=y2){
            return true;
        }
        //边交叉
        int xd = Math.min(Math.abs(x_center-x1),Math.abs(x_center-x2));
        int yd = Math.min(Math.abs(y_center-y1),Math.abs(y_center-y2));
        //如果有交叉肯定有点在圆上，r的平方-线的距离平方对应的偏移量在线上就是交叉
        int xdd = radius*radius - xd*xd;
        if( xdd>=0 && Math.sqrt(xdd) + y_center<y2 && y_center -  Math.sqrt(xdd)  > y1 ){
            return true;
        }
        int ydd = radius*radius - xd*xd;
        if( ydd >=0 && Math.sqrt(ydd) + x_center < x2 && x_center - Math.sqrt(ydd)  < x2  ){
            return true;
        }

        return false;
    }
    private Integer sum = Integer.MIN_VALUE;
    LinkedList<TreeNode> list = new LinkedList();

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // midB(root);
        dg(root);
        return sum;
    }

    void midB(TreeNode node) {
        if (node == null) {
            return;
        }
        midB(node.left);
        list.add(node);
        midB(node.right);
    }

    public Integer dg(TreeNode node) {
        //0 左子树 1右子树  2 左+root 3 右+root 4 全部
        if (node == null) {
            return 0;
        }
        //把小于0的直接舍弃
        Integer leftSum = Math.max(dg(node.left), 0);
        Integer rightSum = Math.max(dg(node.right), 0);
        Integer maxSum = leftSum + rightSum + node.val;

        sum = Math.max(maxSum, sum);
        return maxSum;
    }


    public int numTrees(int n) {
        if (n == 0) {
            return 1;
        }
        return countTrees(1, n);
    }

    public static int countTrees(int start, int end) {
        if (start > end) {
            return 0;
        } else if (start == end) {
            return 1;
        }
        int sum = 0;
        for (int i = start; i <= end; i++) {
            //当i为这个子树当根节点时  左子树的个数和右子树的个数
            int countLeft = countTrees(start, i - 1);
            int countRight = countTrees(i + 1, end);
            //当一个为空子树的时候种类为另一个子树的数量
            if (countLeft == 0 || countRight == 0) {
                sum += countLeft + countRight;
            } else {
                //都不为0时就相乘
                sum += countLeft * countRight;
            }
        }
        return sum;
    }

    public static List<TreeNode> generateTrees(int n) {
        return createTree(1, n);
    }

    public static LinkedList<TreeNode> createTree(int start, int end) {
        LinkedList<TreeNode> all_trees = new LinkedList<TreeNode>();
        if (start > end) {
            all_trees.add(null);
            return all_trees;
        }
        //创建左子树
        for (int i = start; i <= end; i++) {
            //左边的全部
            LinkedList<TreeNode> leftList = createTree(start, i - 1);
            //右边的全部
            LinkedList<TreeNode> rightList = createTree(i + 1, end);
            for (TreeNode l : leftList) {
                for (TreeNode r : rightList) {
                    TreeNode node = new TreeNode(i);
                    node.left = l;
                    node.right = r;
                    all_trees.add(node);
                }
            }
        }
        return all_trees;
    }
}
