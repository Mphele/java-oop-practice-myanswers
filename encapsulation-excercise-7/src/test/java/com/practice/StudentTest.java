package com.practice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void shouldStoreName() {

        Student student = new Student();

        student.setName("Alice");

        assertEquals("Alice", student.getName());
    }

    @Test
    void shouldAddValidScores() {

        Student student = new Student();

        student.addScore(80);
        student.addScore(90);

        assertEquals(2, student.getScores().size());
    }

    @Test
    void shouldRejectInvalidScores() {

        Student student = new Student();

        student.addScore(50);
        student.addScore(150); // invalid

        assertEquals(1, student.getScores().size());
    }

    @Test
    void shouldCalculateAverage() {

        Student student = new Student();

        student.addScore(80);
        student.addScore(100);

        assertEquals(90, student.getAverageScore());
    }

    @Test
    void averageShouldBeZeroIfNoScores() {

        Student student = new Student();

        assertEquals(0, student.getAverageScore());
    }

}