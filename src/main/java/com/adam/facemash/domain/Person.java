package com.adam.facemash.domain;


public class Person {
    private long id;
    private long imageId;
    private String nickname;
    private int voteCount;

    public long getId() {
        return id;
    }

    public long getImageId() {
        return imageId;
    }

    public String getNickname() {
        return nickname;
    }

    public int getVoteCount() {
        return voteCount;
    }
}
