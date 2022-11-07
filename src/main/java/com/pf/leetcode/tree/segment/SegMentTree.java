package com.pf.leetcode.tree.segment;

/**
 * @Description 线段树
 * @Author pf
 * @Date 2020/7/24 9:45
 */
public class SegMentTree {
    SegMengtNode[] nodes;
    int[] arr;
    /**
    * @Description 建树
     * 递归建树每个节点一次 O(n)
    * @Date 2020/7/24 10:37
    * @param [left, right, arr]
    *@return com.pf.leetcode.tree.segment.SegMentTree.SegMengtNode
    **/
    int build(int left, int right, int index) {
        if (index >= arr.length) {
            return 0;
        }
        if (left == right) {
            nodes[index] = new SegMengtNode(arr[left], left, right);
            return nodes[index].val;
        }
        int mid = left + (right - left) / 2;
        int leftVal = build(left , mid, index * 2);
        int rightVal = build(mid + 1 , right, index * 2 + 1);
        SegMengtNode now = new SegMengtNode(leftVal + rightVal, left, right);
        nodes[index] = now;
        return now.val;
    }

    /**
    * @Description 计算区间的懒加载后的和并标记位懒加载
     * 只是一次计算 O(1)
    * @Date 2020/7/24 10:56
    * @param [index, lazy]
    *@return void
    **/
    void calAndFlagLazy(int index, int lazy) {
        nodes[index].val += (nodes[index].right - nodes[index].left + 1) * lazy;
        nodes[index].lazy = lazy;
    }
    /**
    * @Description 下推懒加载到下一层区间
     * 把父节点的懒加载推到子节点O(1)
     * @Date 2020/7/24 10:58
    * @param [index]
    *@return void
    **/
    void updateLazyToChild(int index) {
        int lazy = nodes[index].lazy;
        calAndFlagLazy(index * 2, lazy);
        calAndFlagLazy(index * 2 + 1, lazy);
        nodes[index].lazy = 0;
        return;
    }

    /**
    * @Description 更新当前区间的值位子区间的值O(1)
     * 
    * @Date 2020/7/24 10:59
    * @param [index]
    *@return void
    **/
    void meger(int index) {
        nodes[index].val = nodes[index * 2].val + nodes[index * 2 + 1].val;
    }

    /**
    * @Description 递归更新区间 找到最后的两个区间每一个不超过树深 h = log n 最大是O(2 h) == O(2 log n)
     * 懒加载lazy 标记把要下推更新的操作摊还到正常要执行的更新和查询中了所以时间复杂度是O(1)
     * 1. 当前节点的区间不在要更新的区间里直接返回
     * 2. 当前区间全部在更新区间里就更新计算懒加载的
     * 3. 部分区间就下推该节点的layz，然后去更新左右节点，之后合并更新当前区间
    * @Date 2020/7/24 11:39
    * @param [left, right, index, val]
    *@return void
    **/
    void update(int left, int right, int index, int val) {
        //当前节点的区间不在要更新的区间里直接返回
        if(nodes[index].left > right || nodes[index].right < left) {
            return;
        }
        //当前区间全部在更新区间里就更新计算懒加载的 O(1)
        if(left <= nodes[index].right && nodes[index].right <= right) {
            calAndFlagLazy(index, val);
        } else {
            //当前节点部分区间在要更新的区间里面
            //下推懒加载到下一层区间，如果之前有就趁现在的机会推给下一层
            //O(1)
            updateLazyToChild(index);
            //尝试更新左区间O(log nows[index * 2].right - nows[index * 2].left   )
            update(left, right, index * 2, val);
            //尝试更新右区间O(log nows[i + 1].right - nows[index * 2 + 1].left   )
            update(left, right, index * 2 + 1, val);
            //两个子区间不重叠所以还是小于等于 log n
            //重新统计该节点的区间和O(1)
            meger(index);
        }
    }

    /**
    * @Description 递归查询区间的值，查询的过程中进行懒加载的向下更新和上级的合并
     * @Date 2020/7/24 11:45
    * @param [left, right, index]
    *@return int
    **/
    int query(int left, int right, int index) {
        //不在区间内返回0
        if(nodes[index].left > right || nodes[index].right < left) {
            return 0;
        }
        //完全在区间内返回全部
        if(left <= nodes[index].left && nodes[index].right <= right) {
            return nodes[index].val;
        }
        //更新懒加载到下一层
        updateLazyToChild(index);
        //递归查询左右区间
        int sl = query(left, right, index * 2);
        int sr = query(left, right, index * 2 + 1);
        //更新计算该节点
        meger(index);
        return sl + sr;
    }


    class SegMengtNode {
        //当前节点左区间边界
        int left;
        //当前节点右区间边界
        int right;

        //当前区间和的值
        int val;
        //懒加载
        int lazy;

        SegMengtNode(int val, int left, int right) {
            this.val = val;
            this.left = left;
            this.right = right;
            lazy = 0;
        }
    }
}
