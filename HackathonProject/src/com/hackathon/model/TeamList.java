package com.hackathon.model;

import java.util.ArrayList;

public class TeamList {
    private ArrayList<Team> teams;

    public TeamList() {
        teams = new ArrayList<>();
    }

    public void addTeam(Team t) {
        teams.add(t);
    }

    public boolean removeTeam(String teamName) {
        for (Team t : teams) {
            if (t.getTeamName().equalsIgnoreCase(teamName)) {
                teams.remove(t);
                return true;
            }
        }
        return false;
    }

    public Team searchTeam(String teamName) {
        for (Team t : teams) {
            if (t.getTeamName().equalsIgnoreCase(teamName)) {
                return t;
            }
        }
        return null;
    }

    public ArrayList<Team> getAllTeams() {
        return teams;
    }
    
    public boolean updateTeam(String oldName, Team newTeam) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTeamName().equalsIgnoreCase(oldName)) {
                teams.set(i, newTeam);  // replace with new object
                return true;
            }
        }
        return false; // team not found
    }
    
    public boolean deleteTeam(String name) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTeamName().equalsIgnoreCase(name)) {
                teams.remove(i);
                return true;
            }
        }
        return false;
    }

}
