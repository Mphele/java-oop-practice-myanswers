package com.practice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void shouldTrackNumberOfPeopleCreated() {

        int before = Person.getTotalPeople();

        new Person();
        new Person();
        new Person();

        int after = Person.getTotalPeople();

        assertEquals(before + 3, after);
    }

    @Test
    void parameterizedConstructorShouldWork() {

        Person person = new Person("Alice", 25);

        assertEquals("Alice", person.getName());
        assertEquals(25, person.getAge());
    }

    @Test
    void shouldRejectInvalidAge() {

        Person person = new Person("Bob", -10);

        assertEquals(0, person.getAge());
    }

    @Test
    void setterShouldUpdateAgeCorrectly() {

        Person person = new Person();

        person.setAge(40);

        assertEquals(40, person.getAge());
    }

}