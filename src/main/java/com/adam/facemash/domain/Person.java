package com.adam.facemash.domain;

import javax.persistence.*;

@Entity
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
