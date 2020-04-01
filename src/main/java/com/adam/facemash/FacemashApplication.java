package com.adam.facemash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Facemash software implements a web application that
 * that lets users compare 2 randomly selected person by
 * their looks, and it keeps track of the votes, which later
 * can be viewed on a top 10 list.
 *
 * @author  Adam Reichardt
 * @version 0.40
 */
@SpringBootApplication
public class FacemashApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacemashApplication.class, args);
    }

}
