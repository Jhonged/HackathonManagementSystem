package com.hackathon.model;

public class CyberSecurityTeam extends Team {

    public CyberSecurityTeam(String teamName, String universityName, double[] scores) {
        super(teamName, universityName, scores);
    }

    // Override weighted average (Bonus for cybersecurity teams)
    @Override
    public double calculateWeightedAverage(double[] weights) {
        double base = super.calculateWeightedAverage(weights);
        return base + 2;  // Add small bonus
    }

    @Override
    public String toString() {
        return "[Cyber Security] " + getTeamName() +
               " (" + getUniversityName() + ")" +
               "\nScores: " + getScores()[0] + " | " + getScores()[1] + " | " + getScores()[2];
    }
}
