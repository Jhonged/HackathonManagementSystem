package com.hackathon.model;

public class TestTeam {
    public static void main(String[] args) {
        double[] scores = {85.0, 90.0, 78.0};
        double[] weights = {0.4, 0.4, 0.2};

        Team t = new Team("Cyber Warriors", "SEGi University", scores);

        System.out.println(t.toString());
        System.out.println("Weighted Score: " + t.calculateWeightedAverage(weights));
    }
}
