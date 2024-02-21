package com.pf.leetcode.mid;

/**
 * author：panf
 * date：2023/12/24
 * Description:
 */
public class MinimumPerimeter {

    public static void main(String[] args) {
        MinimumPerimeter minimumPerimeter = new MinimumPerimeter();
        System.out.println(minimumPerimeter.minimumPerimeter(13));
    }
    public long minimumPerimeter(long neededApples) {
        //边长是偶数
        //枚举长度
        long left = 1;
        long right = 100005;
        long ans = 0;
        while (left < right) {
            long mid = left + right >> 1;
            // long temp = calculate(mid);
            if (calculate(mid) >= neededApples) {
                right = mid;
                ans = mid * 8;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private long calculate(long len) {
        //分成4部分第一象限 第一行是pre 后续每一行都在前面的基础上上升一行，有len个所以原先
        //pre, pre+len, pre+len+len
        long pre = (1 + len) * len / 2;
        // 0的x,y轴上没有计算要加上
        long zeroy = pre * 4;
        // pre+len +.... pre+len*len
        long ans = (pre+len + pre+len*len) * len / 2;
        ans *= 4;
        ans += zeroy;
        // ans = (2pre + len*(len+1))*len / 2 * 4 + pre*4 ====>(4pre) * len * 2 + pre*4 ===>4pre * (2len+1)==>2*(len+1)*len*2(len+1);
        return ans;
    }
}
