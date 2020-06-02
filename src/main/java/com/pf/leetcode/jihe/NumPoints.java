package com.pf.leetcode.jihe;

/**
 * @author pf
 * @date 2020-05-18 18:55
 **/
public class NumPoints {

    public int numPoints(int[][] points, int r) {
        int len = points.length;
        int maxCount = 0;
        for(int i = 0; i < len; i++){
            for (int j = 0; j < len; j++){
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]){
                    continue;
                }
                if (!calTogetherCir(points[i], points[j], r)){
                    continue;
                }
                double midX = (points[i][0] + points[j][0]) * 0.5;
                double midY = (points[i][1] + points[j][1]) * 0.5;
                //长度的平方
                double midDis = (midX - points[i][0]) * (midX - points[i][0]) + (midY - points[i][1]) * (midY - points[i][1]);
                //高度的距离
                double high = Math.sqrt(r * r - midDis * midDis / 4);
                //(cx, cy)
                // (cx - midX) ^2 + (cy - midY) ^ 2 = high ^ 2
                // ji向量是(points[i][0] - points[j][0], points[i][1] - points[j][1])
                // ji向量和mc向量（圆心和中点相乘为0） ==> mc向量和(points[i][1] - points[j][1], points[j][0] - points[i][0]) 在同一个方向上
                //(points[i][1] - points[j][1]) * (points[i][0] - points[j][0]) + (points[j][0] - points[i][0]) * (points[i][1] - points[j][1])
                // (points[i][1] - points[j][1]) * (points[i][0] - points[j][0]) - (points[i][0] - points[j][0]) * (points[i][1] - points[j][1]) = 0
                //(cx - midx) / (points[i][1] - points[j][1]) = (cy - midy) / (points[j][0] - points[i][0])

                int dx = points[j][0] - points[i][0];
                int dy = points[i][1] - points[j][1];
                //(cx - midx) /dy = (cy - midy) / dx  ==== >  (cx - midx)  = (cy - midy) / dx * dy
                //(cx - midX) ^2 + (cy - midY) ^ 2 = high ^ 2 ====> ((cy - midy) / dx * dy) ^ 2 + (cy - midY) ^ 2 = high ^ 2
                //(cy - midy) = t ====> (t * dy / dx) ^ 2 + t ^ 2 = high ====> t ^ 2 = high ^ 2 * dx ^ 2 /(dx ^ 2 + dy ^ 2)
                //==>t = high  * dx / sqrt(dx ^ 2 + dy ^ 2);
                double cx = high * dx / Math.sqrt(dx * dx + dy * dy) + midX;
                double cy = high * dy / Math.sqrt(dx * dx + dy * dy) + midY;
                System.out.println(cx + "  "+ cy);
                int count = 0;
                for (int k = 0; k < len; k++){
                    if ((points[k][0] - cx) * (points[k][0] - cx) + (points[k][1] - cy) * (points[k][1] - cy) <= r * r){
                        count++;
                    }
                }
                maxCount = Math.max(maxCount, count);
            }
        }
        return  maxCount;
    }

    /**
     * 是否在同一个圆里
     */
    boolean calTogetherCir(int[] pointX, int[] pointY, int r){
        int dis = (pointX[0] - pointY[0]) * (pointX[0] - pointY[0]) + (pointX[1] - pointY[1]) * (pointX[1] - pointY[1]);
        return 4 * r * r >= dis;
    }
}
