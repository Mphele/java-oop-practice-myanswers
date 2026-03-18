package com.practice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void defaultConstructorShouldSetDefaultValues() {

        Person person = new Person();

        assertEquals("Unknown", person.getName());
        assertEquals(0, person.getAge());
    }

    @Test
    void parameterizedConstructorShouldSetValues() {

        Person person = new Person("Alice", 25);

        assertEquals("Alice", person.getName());
        assertEquals(25, person.getAge());
    }

    @Test
    void constructorShouldRejectInvalidAge() {

        Person person = new Person("Bob", -5);

        assertEquals(0, person.getAge());
    }

    @Test
    void setterShouldUpdateAge() {

        Person person = new Person();

        person.setAge(30);

        assertEquals(30, person.getAge());
    }

    @Test
    void isAdultShouldWorkCorrectly() {

        Person person = new Person("Charlie", 20);

        assertTrue(person.isAdult());
    }

}