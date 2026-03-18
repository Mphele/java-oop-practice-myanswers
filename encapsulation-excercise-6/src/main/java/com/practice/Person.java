package com.practice;

public class Person {

    private String name;
    private int age;

   private static int totalPeople;

   public Person(){
       this("unknown", 0);

   }

   public Person(String name, int age){
       totalPeople+=1;
       if(age>0 && age<130){
           this.age = age;
       }else{
           this.age =0;
       }

       this.name = name;

   }

    public int getAge() {
        return age;
    }

    public String getName() {
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

    public static int getTotalPeople() {
        return totalPeople;
    }
}