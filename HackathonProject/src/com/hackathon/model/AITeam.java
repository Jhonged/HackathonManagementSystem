package com.hackathon.model;

public class AITeam extends Team {

    public AITeam(String teamName, String universityName, double[] scores) {
        super(teamName, universityName, scores);
    }

    // Override weighted average (AI teams use stronger weighting for innovation)
    @Override
    public double calculateWeightedAverage(double[] weights) {
        double base = super.calculateWeightedAverage(weights);
        return base * 1.05;  // AI teams: 5% bonus multiplier
    }

    @Override
    public String toString() {
        return "[AI Team] " + getTeamName() +
               " (" + getUniversityName() + ")" +
               "\nScores: " + getScores()[0] + " | " + getScores()[1] + " | " + getScores()[2];
    }
}
