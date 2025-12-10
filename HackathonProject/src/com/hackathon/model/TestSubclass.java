package com.hackathon.model;

public class TestSubclass {
    public static void main(String[] args) {
        double[] scores = {85, 90, 78};
        double[] weights = {0.4, 0.4, 0.2};

        Team t1 = new CyberSecurityTeam("CS Defenders", "SEGi", scores);
        Team t2 = new AITeam("AI Innovators", "UM", scores);

        System.out.println("Cyber Security Weighted Score: " + t1.calculateWeightedAverage(weights));
        System.out.println("AI Team Weighted Score: " + t2.calculateWeightedAverage(weights));
    }
}
