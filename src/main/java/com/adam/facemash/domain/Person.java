package com.adam.facemash.domain;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Person {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String nickname;

    @Column(nullable = false)
    private int voteCount;

    public long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public int getVoteCount() {
        return voteCount;
    }
}
