package com.adam.facemash.service;

import static org.junit.jupiter.api.Assertions.*;

import com.adam.facemash.dao.User;
import com.adam.facemash.repository.PersonRepository;
import com.adam.facemash.repository.UserRepository;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;



@SpringBootTest(classes = PersonService.class)
public class PersonServiceTests {

    @Autowired
    private PersonService personService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PersonRepository personRepository;

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

}
