package com.example.finale_project_2025;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    ArrayList<Question> questionsList;
    int currentQuestionIndex;
    int score;
    TextView leftTextView;
    TextView rightTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        currentQuestionIndex = 0;
        score = 0;

        leftTextView = findViewById(R.id.leftTextView);
        leftTextView = findViewById(R.id.rightTextView);

        questionsList = populateQuestions(10); // for example
        showQuestion();







    }


    public ArrayList<Question> populateQuestions(int numberOfQuestionsToCreate) {
        ArrayList<Question> list = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < numberOfQuestionsToCreate; i++) {
            int left = rand.nextInt(100); // number between 0 and 99
            int right = rand.nextInt(100);

            list.add(new Question(left, right));
        }

        return list;
    }

    public void showQuestion() {
        Question current = questionsList.get(currentQuestionIndex);

        leftTextView.setText(String.valueOf(current.getLeftNumber()));
        rightTextView.setText(String.valueOf(current.getRightNumber()));
    }

    public void checkAnswer(char selected) {
        Question current = questionsList.get(currentQuestionIndex);
        if (current.getCorrectAnswer().equals(selected)) {
            score++;
        }

        currentQuestionIndex++;

        if (currentQuestionIndex < questionsList.size()) {
            showQuestion();
        } else {
            showGameOverDialog(); // We’ll build this later
        }
    }


}