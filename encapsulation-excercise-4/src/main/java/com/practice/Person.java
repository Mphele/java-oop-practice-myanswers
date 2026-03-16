package com.practice;

public class Person {

    private String name;
    private int age;
    private double height= 0;
    private double weight = 0;

    public double getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        if(age>0 && age<130){
            this.age = age;
        }

    }

    public void setHeight(double height) {
        if(height>0){
            this.height = height;
        }
    }

    public void setWeight(double weight) {
        if(weight>0){
            this.weight = weight;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdult(){
        return (getAge()>18);
    }

    public double calculateBMI(){
        return getWeight()/(Math.pow(height, 2));
    }

}

