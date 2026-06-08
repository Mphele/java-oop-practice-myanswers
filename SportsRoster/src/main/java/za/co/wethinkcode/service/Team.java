package za.co.wethinkcode.service;

import za.co.wethinkcode.model.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String teamName;
    private int maxSquadSize;
    private List<Player> roster;

    public Team(String teamName, int maxSquadSize){
        if(teamName==null||teamName.isEmpty()){
            throw new IllegalArgumentException();
        }

        if(maxSquadSize<1){
            throw new IllegalArgumentException();
        }

        this.teamName = teamName;
        this.maxSquadSize = maxSquadSize;
        roster = new ArrayList<>();
    }

    public String getTeamName() {
        return teamName;
    }

    public int getMaxSquadSize() {
        return maxSquadSize;
    }

    public List<Player> geAllPlayers() {
        return new ArrayList<>(roster);
    }

    public int getCurrentSquadSize(){
        return roster.size();
    }

    public void signPlayer(Player player){
        if(player==null){
            throw new IllegalArgumentException();
        }

        for(Player player1: roster){
            if (player==player1){
                throw new IllegalArgumentException();
            }
        }

        if(getCurrentSquadSize()==getMaxSquadSize()){
            throw new IllegalStateException();
        }

        roster.add(player);
    }

    public List<Player> getAvailablePlayers(){
        List<Player> availablePlayers = new ArrayList<>();

        for(Player player:roster){
            if(!player.isInjured()){
                availablePlayers.add(player);
            }
        }
        return availablePlayers;
    }
}
