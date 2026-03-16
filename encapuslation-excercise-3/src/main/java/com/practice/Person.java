package com.practice;

public class Person {

    private String name;
    private int age;
    private double height;

    public Person(){
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if(age>0 && age<130){
            this.age = age;
        }
    }

    public void setHeight(double height) {
        if(height>0 && height<300){
            this.height = height;
        }
    }
}