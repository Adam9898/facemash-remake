package com.adam.facemash.service;

import static org.junit.jupiter.api.Assertions.*;

import com.adam.facemash.dao.Person;
import com.adam.facemash.dao.User;
import com.adam.facemash.repository.PersonRepository;
import com.adam.facemash.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;



@SpringBootTest(classes = {PersonService.class})
public class PersonServiceTests {


    @Autowired
    private PersonService personService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PersonRepository personRepository;

    Person person;

    @BeforeEach
    public void init() {
        person = new Person();
        person.setId(1);
        person.setNickname("testperson");
    }

    @Test
    @DisplayName("Test that after evaluating a Person array the method will return false")
    public void noMorePersonLeftReturnsFalseTest() {
        Person[] people = new Person[2];
        people[0] = person;
        people[1] = new Person();
        people[1].setId(2);
        assertFalse(personService.noMorePersonLeft(people), "Should be false based on the Person array input");
    }

    @Test
    @DisplayName("Test that after evaluating a Person array the method will return true")
    public void noMorePersonLeftReturnsTrueTest() {
        Person[] people = new Person[2];
        people[0] = person;
        assertTrue(personService.noMorePersonLeft(people), "Should return true based on the Person array input");
    }

    @Test
    @DisplayName("Test that people generator returns a Person array with the proper length")
    public void generatePeopleLengthTest() {
        User user = new User();
        ArrayList<Person> daoResult = new ArrayList<Person>();
        daoResult.add(person);
        daoResult.add(new Person());
        user.setId(1L);
        when(userRepository.findByUsername(anyString())).thenReturn(new User());
        when(personRepository.getUserSpecificAvailablePeople(user.getId())).thenReturn(daoResult);
        Person[] expectedValue = personService.generatePeople(person.getNickname());
        assertEquals(2, expectedValue.length, "Should have the proper length");
    }

    @Test
    @DisplayName("Test that people generator returns a Person array containing null objects")
    public void generatePeopleNullTest() {
        User user = new User();
        ArrayList<Person> daoResult = new ArrayList<Person>();
        daoResult.add(person);
        when(userRepository.findByUsername(anyString())).thenReturn(new User());
        when(personRepository.getUserSpecificAvailablePeople(user.getId())).thenReturn(daoResult);
        Person[] expectedValue = personService.generatePeople(person.getNickname());
        assertNull(expectedValue[0], "Should be a Person object that equals null");
    }

    @RepeatedTest(100)
    @DisplayName("Test that random number generator return unique values")
    public void randomValuePickerUniqueValueTesting() {
        int max = 30;
        int[] generatedRandomNumbers = personService.pickRandomValues(max);
        assertNotEquals(generatedRandomNumbers[0], generatedRandomNumbers[1]);
    }

    @RepeatedTest(100)
    @DisplayName("Test that the method which is responsible for generating random values functions is a proper way")
    public void randomValuePicker() {
        int max = 30;
        int min = 1;
        int[] generatedRandomNumbers = personService.pickRandomValues(max);


        assertEquals(2, generatedRandomNumbers.length, "Test that the number array is of proper size");

        for (int i = 0; i < generatedRandomNumbers.length; i++) {
            assertThat("Test that random number is between min and max value",
                    generatedRandomNumbers[0], allOf(lessThan(max + 1), greaterThan(min - 1)));
        }
    }

    @Test
    @DisplayName("Test the method responsible for getting back the top 10 person with the most voted")
    public void getTop10Test() {
        when(personRepository.findTop10()).thenReturn(new ArrayList<Person>());
        assertTrue(personService.getTop10() instanceof List, "Should return an instance of a List");
    }

}
