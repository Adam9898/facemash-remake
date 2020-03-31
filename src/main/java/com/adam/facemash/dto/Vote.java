package com.adam.facemash.dto;

import com.adam.facemash.validation.NotInAlreadyVotedDatabase;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Vote {


    @NotInAlreadyVotedDatabase
    @Positive
    @NotNull
    private Long chosenPersonId;

    @NotInAlreadyVotedDatabase
    @Positive
    @NotNull
    private Long leftOutPersonId;

    public Long getChosenPersonId() {
        return chosenPersonId;
    }

    public void setChosenPersonId(Long chosenPersonId) {
        this.chosenPersonId = chosenPersonId;
    }

    public Long getLeftOutPersonId() {
        return leftOutPersonId;
    }

    public void setLeftOutPersonId(Long leftOutPersonId) {
        this.leftOutPersonId = leftOutPersonId;
    }
}
