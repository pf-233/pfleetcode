package com.pf.leetcode.mid;

import java.util.Arrays;

/**
 * @author pf
 * @date 2020-04-29 13:11
 **/
public class FindInMountainArray {
    int[] mem;

    public static void main(String[] args) {
        FindInMountainArray ff = new FindInMountainArray();
        System.out.println(ff.findInMountainArray(3, ff.new MountainArray(new int[]{1,2,3,4,5,3,1})));
    }
    public int findInMountainArray(int target, MountainArray mountainArr) {
        mem = new int[mountainArr.length()];
        Arrays.fill(mem, -1);
        int indexRes = findRes(1, mountainArr.length() - 2, mountainArr);
        //小于最大值就肯定不存在
        if (mem[indexRes] <= target) {
            return mem[indexRes] == target ? indexRes : -1;
        }
        int left = midFindIn(target, 0, indexRes - 1, mountainArr, true);
        return left > -1 ? left : midFindIn(target, indexRes + 1, mountainArr.length() - 1, mountainArr, false);
    }

    /**
     * 查找反转点
     */
    int findRes(int left, int right, MountainArray mountainArr) {
        while (true) {
            int mid = (left + right) / 2;
            int midV = getMountain(mountainArr, mid);
            int valueBefore = getMountain(mountainArr, mid - 1);
            int valueAfter = getMountain(mountainArr, mid + 1);
            if (valueBefore < midV && valueAfter > midV) {
                left = mid + 1;
            } else if (valueBefore > midV && valueAfter < midV) {
                right = mid - 1;
            } else if (valueBefore < midV && valueAfter < midV) {
                return mid;
            }
        }
    }

    /**
     * 获取该位置的value
     */
    int getMountain(MountainArray mountainArr, int index) {
        if (mem[index] < 0) {
            mem[index] = mountainArr.get(index);
        }
        return mem[index];
    }

    int midFindIn(int target, int left, int right, MountainArray mountainArr, boolean isAdd) {
        int res = -1;
        while (left <= right) {
            if (left == right) {
                res = getMountain(mountainArr, left) == target ? left : -1;
                break;
            }
            int mid = (left + right) / 2;
            int value = getMountain(mountainArr, mid);
            if (target == value) {
                return mid;
            }
            //递增
            if (isAdd) {
                if (target > value) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                //递减
                if (target > value) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return res;
    }

    class MountainArray {
        int[] num;

        public MountainArray(int[] num) {
            this.num = num;
        }

        public int get(int index) {
            return num[index];
        }

        public int length() {
            return num.length;
        }
    }
}
