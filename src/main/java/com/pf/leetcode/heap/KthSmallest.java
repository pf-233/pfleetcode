package com.pf.leetcode.heap;

import java.util.Arrays;

/**
 * @author pf
 * @date 2020-05-14 13:26
 **/
public class KthSmallest {
    int top;
    int max = 10000;
    int[][] mat;

    public static void main(String[] args) {
        String[] dp = new String[5005];
        Arrays.fill(dp, "");
        System.out.println("ddd");
//        KthSmallest kthSmallest = new KthSmallest();
//        int[][] mat = new int[][]{
//                {1,3,11},
//                {2,4,6}
//        };
//        int k = 5;
//        System.out.println(kthSmallest.kthSmallest(mat, k));
    }
    /**
     * 构建一个堆，每次取出一个然后放入一个 后一个 - 前一个的diff 最小的
     */
    public int kthSmallest(int[][] mat, int k) {
        this.mat = mat;
        int row = mat.length;
        int col = mat[0].length;
        int sum = 0;

        for(int i = 0; i < row; i++){
            sum+= mat[i][0];
        }
        k--;
        if(k == 0){
            return sum;
        }
        Node[] heapArr = new Node[row];
        for(int i = 0; i < row; i++){
            heapArr[i] = new Node(mat[i][0], mat[i][1] - mat[i][0], i, 0);
        }
        heapify(heapArr);
        while(k-- > 0){
            sum+= popMinHeap(heapArr);
        }
        return sum;
    }

    private int popMinHeap(Node[] heapArr){
        int len = heapArr.length;
        Node min = heapArr[0];
        //没有下一个了
        if(min.col == len - 1){
            Node temp = heapArr[--top];
            heapifyInsert(heapArr, temp);
        } else {
            int nextDiff = min.col < len - 1 ? mat[min.row][min.col + 2] - mat[min.row][min.col + 1] : max;
            Node insert =  new Node(mat[min.row][min.col + 1], nextDiff, min.row, min.col + 1);
            heapifyInsert(heapArr, insert);
        }
        return min.nextDiff;
    }

    private void heapifyInsert(Node[] heapArr, Node insert){
        int count = 0;
        heapArr[count] = insert;
        while(count * 2 + 1 < top){
            int swapIndex = count;
            if(heapArr[swapIndex].nextDiff > heapArr[count * 2 + 1].nextDiff){
                swapIndex = count * 2 + 1;
            }
            if(count * 2 + 2 < top && heapArr[swapIndex].nextDiff > heapArr[count * 2 + 2].nextDiff){
                swapIndex = count * 2 + 2;
            }
            if(swapIndex == count){
                break;
            }
            swap(heapArr, count, swapIndex);
            count = swapIndex;
        }
    }

    private void heapify(Node[] heapArr){
        int len = heapArr.length;
        this.top = len;
        for(int i = len / 2; i >= 0; i--){
            int tempIndex = i;
            while(tempIndex * 2 + 1 < len){
                int swapIndex = tempIndex;
                if(heapArr[swapIndex].nextDiff > heapArr[tempIndex * 2 + 1].nextDiff){
                    swapIndex = tempIndex * 2 + 1;
                }
                if(tempIndex * 2 + 2 < len && heapArr[swapIndex].nextDiff > heapArr[tempIndex * 2 + 2].nextDiff){
                    swapIndex = tempIndex * 2 + 2;
                }
                if(swapIndex == tempIndex){
                    break;
                }
                swap(heapArr, tempIndex, swapIndex);
                tempIndex = swapIndex;
            }
        }
    }

    static void swap(Node[] heapArr, int a, int b){
        Node temp = heapArr[a];
        heapArr[a] = heapArr[b];
        heapArr[b] = temp;
    }

    static class Node{
        int val;
        /**
         * 下一个和当前差 多少
         *没有下一个就Integer.MAX_VALUE
         */
        private int nextDiff;
        /**
         *当前第几行
         */
        private int row;
        /**
         *当前第几列
         */
        private int col;

        Node(int val, int nextDiff, int row, int col){
            this.val = val;
            this.nextDiff = nextDiff;
            this.row = row;
            this.col = col;
        }
    }
}
