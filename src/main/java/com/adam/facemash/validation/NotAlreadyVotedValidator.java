package com.adam.facemash.validation;

import com.adam.facemash.dao.Person;
import com.adam.facemash.repository.PersonRepository;
import com.adam.facemash.service.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotAlreadyVotedValidator implements ConstraintValidator<NotInAlreadyVotedDatabase, Long> {

    private PersonRepository personRepository;
    private SessionCache sessionCache;


    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public void setSessionCache(SessionCache sessionCache) {
        this.sessionCache = sessionCache;
    }

    @Override
    public void initialize(NotInAlreadyVotedDatabase constraintAnnotation) { }

    @Override
    public boolean isValid(Long personId, ConstraintValidatorContext context) {
        System.out.println("Authentication: " + sessionCache.getCurrentlyLoggedInUser().getUsername());
        boolean returnValue = true;
        if (voteContextPersonExists(personId)) {
            returnValue = false;
        }
        return returnValue;
    }

    private boolean voteContextPersonExists(Long personId) {
        Person voteContextPerson = personRepository.findPersonBySpecificPersonAndUserID(personId,
                sessionCache.getCurrentlyLoggedInUser().getId());
        System.out.println(voteContextPerson);
        return voteContextPerson != null;
    }
}
