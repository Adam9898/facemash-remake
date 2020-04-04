package com.adam.facemash.dao;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "people")
public class Person {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String nickname;

    @Column(nullable = false)
    private int voteCount;

    @ManyToMany(mappedBy = "people")
    private Set<User> users = new HashSet<>();

    public long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
