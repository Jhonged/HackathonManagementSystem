package com.hackathon.model;

public class Team {
    private String teamName;
    private String universityName;
    private double[] scores;

    public Team(String teamName, String universityName, double[] scores) {
        this.teamName = teamName;
        this.universityName = universityName;
        this.scores = scores;
    }
    
    public String getCategory() {
        if (this instanceof CyberSecurityTeam) {
            return "Cyber Security";
        } else if (this instanceof AITeam) {
            return "Artificial Intelligence";
        } else {
            return "Unknown Category";
        }
    }
    
    public String getTeamName() {
        return teamName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public double[] getScores() {
        return scores;
    }

    public double calculateWeightedAverage(double[] weights) {
        double total = 0;
        double weightSum = 0;

        for (int i = 0; i < scores.length; i++) {
            total += scores[i] * weights[i];
            weightSum += weights[i];
        }
        return total / weightSum;
    }
    
    public void setScores(double[] scores) {
        this.scores = scores;
    }
    
    public double getWeightedScore() {
        double[] weights = {0.4, 0.4, 0.2};
        return calculateWeightedAverage(weights);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Team Name: ").append(teamName).append("\n");
        sb.append("University: ").append(universityName).append("\n");
        sb.append("Scores: ");
        for (double s : scores) {
            sb.append(s).append(" ");
        }
        return sb.toString();
    }
}
