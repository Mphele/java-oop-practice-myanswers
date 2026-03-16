package com.practice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void nameShouldBeStoredCorrectly() {

        Person person = new Person();

        person.setName("Alice");

        assertEquals("Alice", person.getName());
    }

    @Test
    void ageShouldBeStoredCorrectly() {

        Person person = new Person();

        person.setAge(30);

        assertEquals(30, person.getAge());
    }

    @Test
    void ageShouldRejectInvalidValues() {

        Person person = new Person();

        person.setAge(40);
        person.setAge(-10);

        assertEquals(40, person.getAge());
    }

    @Test
    void heightShouldBeStoredCorrectly() {

        Person person = new Person();

        person.setHeight(175.5);

        assertEquals(175.5, person.getHeight());
    }

    @Test
    void heightShouldRejectInvalidValues() {

        Person person = new Person();

        person.setHeight(180);
        person.setHeight(-5);

        assertEquals(180, person.getHeight());
    }

}