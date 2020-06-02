package com.pf.leetcode.mid;

/**
 * @author pf
 * @date 2020-05-24 14:03
 **/
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{2}, new int[]{1,3}));
        System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
        System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2,4}));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        if (((l1 + l2) & 1) == 1){
            return findKSortedArrays(nums1, nums2, (l1 + l2) / 2 + 1);
        } else {
            return (findKSortedArrays(nums1, nums2, (l1 + l2) / 2) + findKSortedArrays(nums1, nums2, (l1 + l2) / 2 + 1)) * 0.5;
        }
    }

    /**
    * @Description: 数组和k是合并后的第几个数  是长度
    * @创建人  pf
    * @return double
    */
    private static double findKSortedArrays(int[] nums1, int[] nums2, int k) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        //先前的数组长度是0 （抛弃的数组长度）
        int preLen1 = 0;
        int preLen2 = 0;
        while (true){
            //当nums1的全部抛弃那么就是 preLen2 (已经不符合要求的) + k (还需要几个数) - 1 （前面的是长度转化成索引）
            if (preLen1 == l1){
                return 1.0 * nums2[preLen2 + k - 1];
            } else if (preLen2 == l2){
                return 1.0 * nums1[preLen1 + k - 1];
            } else  if (k == 1){
                //当还需要一个数的时候 就去看  preLen1 和 preLen2 中小的那个 之前抛弃的长度是preLen1（索引是preLen1 - 1） 所以下一个索引就是 preLen1
                return 1.0 * Math.min(nums1[preLen1], nums2[preLen2]);
            }

            //步进为 k / 2;
            int step = k / 2;
            //已经抛弃的 + 可能抛弃的长度   防止越界
            int nowLen1 = Math.min(preLen1 + step, l1);
            int nowLen2 = Math.min(preLen2 + step, l2);
            //实际的索引值要 - 1
            int point1 = nums1[nowLen1 - 1];
            int point2 = nums2[nowLen2 - 1];

            if (point1 <= point2){
                //k 继续抛弃 nowLen1 - preLen1 个数字（这些肯定不是）
                k -= (nowLen1 - preLen1);
                preLen1 = nowLen1;
            } else {
                k -= (nowLen2 - preLen2);
                preLen2 = nowLen2;
            }
        }
    }
}
