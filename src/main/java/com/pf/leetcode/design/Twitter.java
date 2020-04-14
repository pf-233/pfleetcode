package com.pf.leetcode.design;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author pf
 * @date 2020-04-13 12:59
 **/
class Twitter {
    public static void main(String[] args) {
//        ["Twitter","postTweet","getNewsFeed","follow","postTweet","getNewsFeed","unfollow","getNewsFeed"]
//[[],[1,5],[1],[1,2],[2,6],[1],[1,2],[1]]
        Twitter twitter = new Twitter();
//        twitter.postTweet(1,5);
//        twitter.getNewsFeed(1);
//        twitter.follow(1,2);
//        twitter.postTweet(2,6);
//        twitter.getNewsFeed(1);
//        twitter.unfollow(1,2);
//        twitter.getNewsFeed(1);

//        ["Twitter","postTweet","getNewsFeed","follow","getNewsFeed","unfollow","getNewsFeed"]
//[[],[1,1],[1],[2,1],[2],[2,1],[2]]
        List<Integer> list = null;
//        twitter.postTweet(1,1);
//        list = twitter.getNewsFeed(1);
//        twitter.follow(2,1);
//        list = twitter.getNewsFeed(2);
//        twitter.unfollow(2,1);
//        list = twitter.getNewsFeed(2);

//        ["Twitter","postTweet","postTweet","unfollow","follow","getNewsFeed"]
//[[],[1,4],[2,5],[1,2],[1,2],[1]]
        System.out.println(list);
        twitter.postTweet(1,4);
        twitter.postTweet(2,5);
        twitter.unfollow(1,2);
        twitter.follow(1,2);
        list = twitter.getNewsFeed(1);
        System.out.println("aa");
    }
    Map<Integer, LinkedList<TwitterData>> userTweetMap;
    /**
     * userId, 后面是被关注的人的列表
     * 1 ->  2,3
     * 1关注了2和3
     */
    Map<Integer, HashSet<Integer>> userFollowedMap;
    private int count = 0;
    /** Initialize your data structure here. */
    public Twitter() {
        userTweetMap=new HashMap();
        userFollowedMap=new HashMap();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        TwitterData td = new TwitterData(userId, tweetId, count++);
        LinkedList<TwitterData> list = userTweetMap.getOrDefault(userId, new LinkedList());
        list.addFirst(td);
        userTweetMap.put(userId, list);

//        HashSet<Integer> followSet = userFollowedMap.getOrDefault(userId, new HashSet());
//        Iterator<Integer> it = followSet.iterator();
//        while(it.hasNext()){
//            Integer tempUserId = it.next();
//            LinkedList<TwitterData> tempList = userTweetMap.getOrDefault(tempUserId, new LinkedList());
//            tempList.addFirst(td);
//            userTweetMap.put(tempUserId, tempList);
//        }
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        LinkedList<TwitterData> list = userTweetMap.getOrDefault(userId, new LinkedList());
        HashSet<TwitterData> set = new HashSet<TwitterData>();
        for (TwitterData td : list){
            set.add(td);
        }

        HashSet<Integer> followSet = userFollowedMap.getOrDefault(userId, new HashSet<>());
        for (Integer userID : followSet){
            list = userTweetMap.getOrDefault(userID, new LinkedList());
            for (TwitterData td : list){
                set.add(td);
            }
        }

        List<TwitterData> dataList = new ArrayList<>();
        dataList.addAll(set);
        dataList = dataList.stream().sorted((a1,a2)->a2.count-a1.count).collect(Collectors.toList());
        List<Integer> res = new ArrayList<>(10);
        for (int i=0;i<10 && i<dataList.size();i++){
            res.add(dataList.get(i).tweetId);
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followeeId==followerId){
            return;
        }
        HashSet<Integer> followSet = userFollowedMap.getOrDefault(followerId, new HashSet());
        followSet.add(followeeId);
        userFollowedMap.put(followerId, followSet);

//        LinkedList<TwitterData> listFoller = userTweetMap.getOrDefault(followerId, new LinkedList());
//        LinkedList<TwitterData> listFolled = userTweetMap.getOrDefault(followeeId, new LinkedList());
//
//        List<TwitterData> list = listFolled.stream().filter(e->e.getUserId()==followeeId).collect(Collectors.toList());
//        listFoller.addAll(list);
//        listFoller.sort((a1,a2)->a2.tweetId-a1.tweetId);
//        userTweetMap.put(followerId, listFoller);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followeeId==followerId){
            return;
        }
        HashSet<Integer> followSet = userFollowedMap.getOrDefault(followerId, new HashSet());
        followSet.remove(followeeId);

//        LinkedList<TwitterData> list = userTweetMap.getOrDefault(followerId, new LinkedList());
//        Iterator<TwitterData> it = list.iterator();
//        while(it.hasNext()){
//            TwitterData td = it.next();
//            if(td.userId==followeeId){
//                it.remove();
//            }
//        }
    }

    class TwitterData{
        public Integer userId;
        public Integer tweetId;
        private Integer count;

        TwitterData(Integer userId, Integer tweetId, Integer count){
            this.userId=userId;
            this.tweetId=tweetId;
            this.count = count;
        }

        public Integer getTweetId(){
            return this.tweetId;
        }

        public Integer getUserId(){
            return this.userId;
        }
    }
}
