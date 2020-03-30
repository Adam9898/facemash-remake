package com.adam.facemash.service;

import com.adam.facemash.domain.Person;
import com.adam.facemash.domain.User;
import com.adam.facemash.repository.PersonRepository;
import com.adam.facemash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class PersonService {

    UserRepository userRepository;
    PersonRepository personRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person[] generatePeople(String username) {
        ArrayList<Person> availablePeople = getAvailablePeople(username);
        return createVoteCandidates(availablePeople);
    };

    private ArrayList<Person> getAvailablePeople(String username) {
        User currentUser = userRepository.findByUsername(username);
        return new ArrayList<Person>(personRepository.getUserSpecificAvailablePeople(currentUser.getId()));
    }

    private Person[] createVoteCandidates(ArrayList<Person> people) {
        Person[] returnValue = new Person[2];
        int[] randomValues =  pickRandomValues(people.size());
        returnValue[0] = people.get(randomValues[0]);
        returnValue[1] = people.get(randomValues[1]);
        return returnValue;
    }

    public int[] pickRandomValues(int max) {
        int[] returnValue = new int[2];
        int min = 1;
        Random rand = new Random(max);
        returnValue[0] = rand.nextInt(((max - min) + 1) + min);
        returnValue[1] = rand.nextInt(((max - min) + 1) + min);
        while (returnValue[1] == returnValue[0])
            returnValue[1] =  rand.nextInt(((max - min) + 1) + min);
        return returnValue;
    }
}