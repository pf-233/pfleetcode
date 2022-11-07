package com.pf.leetcode.tu;

public class CheckWays {
    public static void main(String[] args) {
        int[][] pairs = new int[][] {
                {3,5},{4,5},{2,5},{1,5},{1,4},{2,4}
        };
        System.out.println(new CheckWays().checkWays(pairs));
    }

    public int checkWays(int[][] pairs) {
        int[][] arr = new int[505][505];
        int[] parents = new int[505];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        int max = 0;
        // 先处理数据有互为祖先关系的有1的边
        for (int i = 0; i < pairs.length; i++) {
            arr[pairs[i][1]][pairs[i][0]] = 1;
            arr[pairs[i][0]][pairs[i][1]] = 1;
            union(pairs[i][1], pairs[i][0], parents);
            max = Math.max(pairs[i][1], max);
            max = Math.max(pairs[i][0], max);
        }

        int root = -1;

        for (int i = 1; i <= max; i++) {
            if (parents[i] == i) {
                continue;
            }
            int tmp = find(i, parents);
            if (root < 0) {
                root = tmp;
            } else if (root != tmp) {
                return 0;
            }
        }


        int ways = 1;
        for (int i = 0; i < pairs.length; i++) {
            // first 是 second 的祖先则 first 下有全部的 second 的数据
            // second 是 first 的祖先则 second 下有全部的 first 的数据
            // 所以如果 first 和second 可以调换位置就数组全部一样， 不可以则必定有一个是另一个的祖先
            // 当两个都没有对方的全部数据时，说明不可能互为祖先直接返回0
            int first = pairs[i][0];
            int[] fi = arr[first];
            int second = pairs[i][1];
            int[] se = arr[second];

            int nowWays = -1;
            for (int j = 0; j < fi.length; j++) {
                // 如果是i,j数对本身直接跳过      如果j在两者的互为祖先中则无影响
                if (j == first || j == second || fi[j] == se[j]) {
                    continue;
                }
                // 假设first 是祖先,要求之前没有确定祖先或者也是假定first是祖先
                if (fi[j] == 1 && (nowWays == 1 || nowWays == -1)) {
                    nowWays = 1;
                } else if (se[j] == 1 && (nowWays == 2 || nowWays == -1)) {
                    //second 是祖先,要求之前没有确定祖先或者也是假定second是祖先
                    nowWays = 2;
                } else {
                    // 说明假设冲突
                    nowWays = 3;
                }
            }
            if (nowWays == 3) {
                return 0;
            } else if (nowWays == -1) {
                ways = 2;
            }
        }
        return ways;
    }

    int find(int x, int[] parents) {
        while (x != parents[x]) {
            x = parents[x];
        }
        return x;
    }

    void union(int x, int y, int[] parents) {
        int px = find(x, parents);
        int py = find(y, parents);
        parents[px] = py;
    }
}
