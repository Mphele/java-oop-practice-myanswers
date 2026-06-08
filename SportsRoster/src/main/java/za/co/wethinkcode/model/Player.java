package za.co.wethinkcode.model;

import java.util.Objects;

public class Player {

    private int jerseyNUmber;
    private String name;
    private boolean isInjured;

    public Player(int jerseyNumber, String name, boolean isInjured){
        if(name ==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }

        if (jerseyNumber<1){
            throw new IllegalArgumentException();
        }

        this.jerseyNUmber = jerseyNumber;
        this.name=name;
        this.isInjured = isInjured;
    }

    public int getJerseyNUmber() {
        return jerseyNUmber;
    }

    public String getName() {
        return name;
    }

    public boolean isInjured() {
        return isInjured;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return jerseyNUmber == player.jerseyNUmber;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(jerseyNUmber);
    }
}
