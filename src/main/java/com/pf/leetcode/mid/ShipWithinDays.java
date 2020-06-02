package com.pf.leetcode.mid;

/**
 * @author pf
 * @date 2020-05-02 11:08
 **/
public class ShipWithinDays {
    public static void main(String[] args) {
        System.out.println(shipWithinDays(new int[]{3}, 3));
    }
    public static int shipWithinDays(int[] weights, int D) {
        int low = Integer.MIN_VALUE;
        int high = 0;
        for(int i = 0; i < weights.length; i++){
            high+= weights[i];
            low = Math.max(low, weights[i]);
        }

        while(low < high){
            int mid = (low + high) / 2;
            int count = 0;
            int index = 0;
            int daySum = 0;
            while(index < weights.length){
                if(daySum + weights[index] <= mid){
                    daySum+= weights[index];
                } else {
                    daySum = weights[index];
                    count++;
                    if (count == D){
                        break;
                    }
                }
                index++;
            }
            //没装完肯定不行要mid + 1
            if(index < weights.length){
                low = mid + 1;
            } else {
                //mid 就装完了所以 mid - 1可能装不完要保存mid
                high = mid;
            }
        }
        return high;
    }
}
