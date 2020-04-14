package com.pf.leetcode.other;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pf
 * @date 2020-04-06 12:19
 **/
public class LongestDiverseString {
    Map<String,Integer> map = new HashMap();
    Integer state =0;

    public static void main(String[] args) {
//        LongestDiverseString ld = new LongestDiverseString();
//        System.out.println(ld.longestDiverseString(1,1,7));

        LongestDiverseString request= new LongestDiverseString();
//        int[] start1 = new int[]{0,0};
//        int[] end1 = new int[]{0,1};
//        int[] start2 = new int[]{-1,0};
//        int[] end2 = new int[]{2,1};
//        int[] start1 = new int[]{0,3};
//        int[] end1 = new int[]{0,6};
//        int[] start2 = new int[]{0,1};
//        int[] end2 = new int[]{0,5};
        int[] start1 = new int[]{0,0};
        int[] end1 = new int[]{1,-1};
        int[] start2 = new int[]{0,0};
        int[] end2 = new int[]{-1,1};
        double[] doubles = request.intersection(start1, end1, start2, end2);

//        start2 = new int[]{2,0};
//        end2 = new int[]{2,1};
//
//        doubles = request.intersection(start1, end1, start2, end2);
        System.out.println(doubles[0]+","+doubles[1]);
    }

    public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {
        int x1=start1[0];
        int x2=end1[0];
        int x3=start2[0];
        int x4=end2[0];

        int y1=start1[1];
        int y2=end1[1];
        int y3=start2[1];
        int y4=end2[1];

        double[] res = new double[]{};
        double k1,c1,k2,c2;
        //x= ?
        if(x1==x2 && x3==x4){
            //平行
            if(x1 != x3){
                return res;
            }
            //不相交，
            if(!match(y1,y2,y3,y4) ){
                return res;
            }
            res = new double[2];
            res[0]=x1;
            res[1]=Math.max(Math.min(y1, y2), Math.min(y3, y4));
            return res;
        } else if(x1==x2){
            if(!match(x1,x2,x3,x4)){
                return res;
            }
            k2 = 1.0*(y4-y3)/(x4-x3);
            c2 = y3-k2*(x3);
            double y = k2*x1+c2;
            int min = Math.min(y3,y4);
            int max = Math.max(y3,y4);
            if(y>=min && y<=max){
                res=new double[]{x1, y};
            }
            return res;
        } else if(x3==x4){
            if(!match(x1,x2,x3,x4)){
                return res;
            }
            k1 = 1.0*(y2-y1)/(x2-x1);
            c1 = y2-k1*(x2);
            double y = k1*x3+c1;
            int min = Math.min(y1,y2);
            int max = Math.max(y1,y2);
            if(y>=min && y<=max){
                res=new double[]{x3, y};
            }
            return res;
        }


        //y=kx+c;
        k1 = 1.0*(y2-y1)/(x2-x1);
        c1 = y2-k1*(x2);

        k2 = 1.0*(y4-y3)/(x4-x3);
        c2 = y3-k2*(x3);

        //x=(c2-c1)/(k1-k2);
        if(k2==k1){
            //平行或重叠
            if(c1!=c2){
                return res;
            } else {
                if(match(x1,x2,x3,x4)){
                    int min = Math.max(Math.min(x1, x2), Math.min(x3, x4));
                    return new double[]{min, k1*min+c1};
                }
            }
        } else {
            double x=1.0*(c2-c1)/(k1-k2);
            if(match(x1, x2, x3,x4, x)){
                return new double[]{x, k1*x+c1};
            } else {
                return res;
            }
        }
        return res;
    }

    private static boolean match(int x1, int x2, int x3, int x4, double x){
        int min = Math.min(x1, x2);
        int max = Math.max(x1, x2);

        int min2 = Math.min(x3, x4);
        int max2 = Math.max(x3, x4);

        return x>=min && x<=max && x>=min2 && x<=max2;
    }

    private static boolean match(int y1, int y2, int y3, int y4){
        int min = Math.min(y1, y2);
        int max = Math.max(y1, y2);

        int min2 = Math.min(y3, y4);
        int max2 = Math.max(y3, y4);

        return Math.min(max, max2) >= Math.max(min, min2);
    }

    public String longestDiverseString(int a, int b, int c) {
        map.put("a", a);
        map.put("b", b);
        map.put("c", c);

        int count = a+b+c;
        StringBuffer sb=new StringBuffer(count);
        //贪心
        while(true){
            String next = getNext();
            if(map.get(next) <= 0){
                break;
            }
            Integer temp = map.get(next);
            map.put(next, --temp);
            if(state==0){
                state = next.charAt(0)-'a' + 1;
            } else {
                int tempChar = next.charAt(0)-'a' + 1;;
                state=state%10*10+tempChar;
            }
            sb.append(next);
        }
        return sb.toString();
    }

    String getNext(){
        String s = null;
        int count = 0;
        if(state == 11){
            s = map.get("b") >= map.get("c") ? "b" : "c";
        } else if(state == 22){
            s = map.get("a") >= map.get("c") ? "a" : "c";
        } else if(state == 33){
            s = map.get("a") >= map.get("b") ? "a" : "b";
        } else {
            s = map.get("a") >= map.get("b") ? "a" : "b";
            s = map.get("c") >= map.get(s) ? "c" : s;
        }
        return s;
    }
}
