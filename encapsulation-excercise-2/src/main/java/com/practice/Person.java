package com.practice;

public class Person {

    private int age;

    public Person() {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0 && age < 130) {
            this.age = age;
        }
    }
}