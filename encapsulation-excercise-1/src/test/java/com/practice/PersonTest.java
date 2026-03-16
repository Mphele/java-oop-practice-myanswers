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
    void nameCanBeChanged() {

        Person person = new Person();

        person.setName("Alice");
        person.setName("Bob");

        assertEquals("Bob", person.getName());
    }

    @Test
    void nameShouldNotBeNullAfterSetting() {

        Person person = new Person();

        person.setName("Charlie");

        assertNotNull(person.getName());
    }

}