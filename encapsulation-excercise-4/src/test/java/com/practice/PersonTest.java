package com.practice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void shouldStoreName() {

        Person person = new Person();

        person.setName("Alice");

        assertEquals("Alice", person.getName());
    }

    @Test
    void shouldStoreValidAge() {

        Person person = new Person();

        person.setAge(25);

        assertEquals(25, person.getAge());
    }

    @Test
    void shouldRejectInvalidAge() {

        Person person = new Person();

        person.setAge(30);
        person.setAge(-5);

        assertEquals(30, person.getAge());
    }

    @Test
    void isAdultShouldReturnTrueForAdults() {

        Person person = new Person();

        person.setAge(20);

        assertTrue(person.isAdult());
    }

    @Test
    void isAdultShouldReturnFalseForMinors() {

        Person person = new Person();

        person.setAge(15);

        assertFalse(person.isAdult());
    }

    @Test
    void shouldCalculateBMI() {

        Person person = new Person();

        person.setHeight(1.75);
        person.setWeight(70);

        double bmi = person.calculateBMI();

        assertEquals(22.86, bmi, 0.01);
    }

}