package com.example.finale_project_2025;

public class Question {
    private int left;
    private int right;
    private char correctAnswer; // '<', '=', '>'

    // Constructor: called when we create a new question
    public Question(int left, int right) {
        this.left = left;
        this.right = right;
        this.correctAnswer = calculateAnswer(); // this determines what's correct
    }

    private char calculateAnswer() {
        if (left < right) return '<';
        else if (left == right) return '=';
        else return '>';
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public char getCorrectAnswer() {
        return correctAnswer;
    }
}
