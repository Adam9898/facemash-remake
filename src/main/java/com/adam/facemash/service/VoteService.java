package com.adam.facemash.service;

import com.adam.facemash.dto.Vote;
import com.adam.facemash.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VoteService {

    private SessionCache sessionCache;
    private PersonRepository personRepository;

    @Autowired
    public void setSessionCache(SessionCache sessionCache) {
        this.sessionCache = sessionCache;
    }

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void vote(Vote voteObj) {
        Long currentUserId = sessionCache.getCurrentlyLoggedInUser().getId();
        personRepository.saveVoteContext(currentUserId, voteObj.getChosenPersonId());
        personRepository.saveVoteContext(currentUserId, voteObj.getLeftOutPersonId());
    }
}
