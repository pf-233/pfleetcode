package com.pf.leetcode.tu.template;

import java.util.ArrayList;
import java.util.List;

/**
 * 最短路径算法
 *
 * Bellman-ford
 * 不可以求解存在负权值回路的图
 */
public class Bellman {
    //最大值
    int INF = 100000;
    int MAX = 8;
    //顶点个数
    int n;
    //边的矩阵
    int[][] edges;
    //最短的路径
    int[] dis;
    //路径上的上一个节点
    int[] path;
    void Bellman(int start){

        edges = new int[MAX][MAX];
        dis = new int[n];
        path = new int[n];
        int i,j,k,u;
        for (i = 0; i < n; i++){
            dis[i] = edges[start][i];
            path[i] = i != start && dis[i] < INF ? start : -1;
        }
        //从dis[1][u] 推到 dis[n - 1][u]
        for (k = 2; k < n; k++){
            //修改每个顶点的dis 和path
            //本来不用两个数组是会造成dis[k - 1][U] 已经计算过是从 dis[k-1][j] 过来的那么当计算其他的点的时候的u已经是dis[k][u]了
            //但是实际上的算法的本质是每次对边进行枚举如果加入这条边可以让从源点到达u点的距离变小就加入，当不存在负权值回路的时候肯定是就n - 1 条边才是最小的
            //所以这里计算即使使用dis[k][u] 也没有关系，只要能让路径变小就可以继续下去
            //重点是判断不存在负权值回路。 当推到 n - 1的时候就肯定已经加入了n - 1条边，这个时候如果再对每条边枚举加入看看是否会减小当前的dis
            //如果减小说明存在负权值的回路，之前的可能就一直在回路里
            //如果不减少说明是好的--------> 其实在n - 1个循环里当有一次dis不变的时候就已经好了
            for ( u = 0; u < n; u++){
                if (u != start){
                    for (j = 0; j < n; j++){
                        if (edges[j][u] < INF && dis[j] + edges[j][u] < dis[u]){
                            dis[u]  = dis[j] + edges[j][u];
                            path[u] = j;
                        }
                    }
                }
            }
        }
    }
}
