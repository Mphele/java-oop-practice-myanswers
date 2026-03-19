package com.practice;
import java.util.ArrayList;


public class Student {

    private String name;
    private ArrayList<Integer> scores = new ArrayList<>();

    public Student(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addScore(int score) {

        if(0<=score && score<=100){
            this.scores.add(score);
        }

    }

    public ArrayList<Integer> getScores() {
        return new ArrayList<Integer>(scores);
    }

    public double getAverageScore(){

        if(getScores().isEmpty()){
            return 0.0;
        }
        int length = getScores().size();
        double sum =0;

        for(int score:getScores()){
            sum+=score;
        }

        return sum/length;
    }
}
