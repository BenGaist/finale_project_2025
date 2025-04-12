package com.example.finale_project_2025;

public class Result {
    private String username;
    private int score;
    private String motivation;

    public Result(String username, int score, String motivation) {
        this.username = username;
        this.score = score;
        this.motivation = motivation;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public String getMotivation() {
        return motivation;
    }
}
