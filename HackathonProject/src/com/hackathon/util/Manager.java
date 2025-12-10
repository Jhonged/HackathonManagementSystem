package com.hackathon.util;

import com.hackathon.model.Team;
import com.hackathon.model.TeamList;
import java.io.*;

public class Manager {

    // Save team list into CSV file
    public static void saveToCSV(TeamList list, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Team t : list.getAllTeams()) {
                double[] s = t.getScores();
                writer.println(t.getTeamName() + "," +
                               t.getUniversityName() + "," +
                               s[0] + "," + s[1] + "," + s[2]);
            }
            System.out.println("File saved successfully!");
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // Load data from CSV file
    public static TeamList loadFromCSV(String filename) {
        TeamList list = new TeamList();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                String uni = data[1];
                double s1 = Double.parseDouble(data[2]);
                double s2 = Double.parseDouble(data[3]);
                double s3 = Double.parseDouble(data[4]);

                double[] scores = {s1, s2, s3};

                Team t = new Team(name, uni, scores);
                list.addTeam(t);
            }
            System.out.println("File loaded successfully!");
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return list;
    }

    // Print report
    public static void printReport(TeamList list) {
        System.out.println("===== TEAM REPORT =====");
        for (Team t : list.getAllTeams()) {
            System.out.println(t.toString());
            System.out.println("------------------------");
        }
    }
}
