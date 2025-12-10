package com.hackathon.util;

import com.hackathon.model.Team;
import com.hackathon.model.TeamList;

public class TestManager {
    public static void main(String[] args) {

        TeamList list = new TeamList();

        Team t1 = new Team("Cyber Warriors", "SEGi University", new double[]{85, 90, 78});
        Team t2 = new Team("AI Masters", "UM", new double[]{88, 92, 80});

        list.addTeam(t1);
        list.addTeam(t2);

        // Save to CSV
        Manager.saveToCSV(list, "data/teams.csv");

        // Load from CSV
        TeamList loaded = Manager.loadFromCSV("data/teams.csv");

        // Print report
        Manager.printReport(loaded);
    }
}
