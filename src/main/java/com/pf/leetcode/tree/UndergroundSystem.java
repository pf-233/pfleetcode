package com.pf.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pf
 * @date 2020-03-29 11:07
 **/
class UndergroundSystem {

    public static void main(String[] args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);
        undergroundSystem.checkOut(27, "Waterloo", 20);
        undergroundSystem.checkOut(32, "Cambridge", 22);
        System.out.println(undergroundSystem.getAverageTime("Paradise", "Cambridge"));;       // 返回 14.0。从 "Paradise"（时刻 8）到 "Cambridge"(时刻 22)的行程只有一趟
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));;          // 返回 11.0。总共有 2 躺从 "Leyton" 到 "Waterloo" 的行程，编号为 id=45 的乘客出发于 time=3 到达于 time=15，编号为 id=27 的乘客于 time=10 出发于 time=20 到达。所以平均时间为 ( (15-3) + (20-10) ) / 2 = 11.0
        undergroundSystem.checkIn(10, "Leyton", 24);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));;          // 返回 11.0
        undergroundSystem.checkOut(10, "Waterloo", 38);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));;          // 返回 12.0
    }
    //站到站的次数
    private Map<String, Integer> stationToStationCountMap;
    //站到站的总时间
    private Map<String, Integer> stationToStationTimeCountMap;
    //顾客进入地铁记录
    private Map<Integer, String> idStationMap;
    //顾客进入地铁时间记录
    private Map<Integer, Integer> idTimeMap;
    public UndergroundSystem() {
        stationToStationCountMap = new HashMap();
        stationToStationTimeCountMap = new HashMap();
        idStationMap = new HashMap();
        idTimeMap = new HashMap();
    }

    public void checkIn(int id, String stationName, int t) {
        idStationMap.put(id, stationName);
        idTimeMap.put(id, t);
    }

    public void checkOut(int id, String stationName, int t) {

        String startStation = idStationMap.get(id);
        String stationKey = startStation+"-"+stationName;

        Integer time = idTimeMap.get(id);
        t = t-time;

        Integer sCount = stationToStationCountMap.get(stationKey);
        sCount = sCount == null ? 1 : ++sCount;
        stationToStationCountMap.put(stationKey, sCount);

        Integer sTime = stationToStationTimeCountMap.get(stationKey);
        sTime = sTime == null ? t : sTime + t;
        stationToStationTimeCountMap.put(stationKey, sTime);
    }

    public double getAverageTime(String startStation, String endStation) {
        String stationKey = startStation+"-"+endStation;
        Integer count = stationToStationCountMap.get(stationKey);
        Integer time = stationToStationTimeCountMap.get(stationKey);
        return time/count;
    }
}