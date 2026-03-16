package com.practice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void ageShouldBeStoredCorrectly() {

        Person person = new Person();

        person.setAge(25);

        assertEquals(25, person.getAge());
    }

    @Test
    void ageShouldNotAllowNegativeNumbers() {

        Person person = new Person();

        person.setAge(25);
        person.setAge(-5);

        assertEquals(25, person.getAge());
    }

    @Test
    void ageShouldNotAllowUnrealisticValues() {

        Person person = new Person();

        person.setAge(30);
        person.setAge(200);

        assertEquals(30, person.getAge());
    }

}