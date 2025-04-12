package com.example.finale_project_2025;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView welcomeText;
    Button resultButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        // Ensure the layout has android:id="@+id/main"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        resultButton = findViewById(R.id.seeScoreButton);

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGameOverDialog();
            }
        });
        currentQuestionIndex = 0;
        score = 0;

        // Correct assignment for both text views
        leftTextView = findViewById(R.id.leftTextView);
        rightTextView = findViewById(R.id.rightTextView);

        welcomeText = findViewById(R.id.welcomeText);
        String name = getIntent().getStringExtra("username");

        welcomeText.setText("Hello " + name + "!");


        findViewById(R.id.lessThanButton).setOnClickListener(v -> checkAnswer('<'));
        findViewById(R.id.equalButton).setOnClickListener(v -> checkAnswer('='));
        findViewById(R.id.greaterThanButton).setOnClickListener(v -> checkAnswer('>'));

        // Populate questions and show the first one
        questionsList = populateQuestions(10);
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
        leftTextView.setText(String.valueOf(current.getLeft()));
        rightTextView.setText(String.valueOf(current.getRight()));
    }

    public void checkAnswer(char selected) {
        Question current = questionsList.get(currentQuestionIndex);

        if (current.getCorrectAnswer() == selected) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong! Correct answer was " + current.getCorrectAnswer(), Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;

        if (currentQuestionIndex < questionsList.size()) {
            showQuestion();
        } else {
            showGameOverDialog(); // implement this later
        }
    }

    // You may implement this function to show results in a dialog
    private void showGameOverDialog() {
        String name = getIntent().getStringExtra("username");

        // 1. Choose a motivational sentence from a built-in pool
        String[] motivations = {
                "Keep going!", "You're getting better!", "Great job!", "Well done!", "Nice try!"
        };
        String randomMotivation = motivations[new Random().nextInt(motivations.length)];

        // 2. Save the result into the database
        GameResultDatabaseHelper dbHelper = new GameResultDatabaseHelper(this);
        dbHelper.insertResult(name, score, randomMotivation);

        // 3. Move to MainActivity3 and pass username
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        intent.putExtra("username", name);
        startActivity(intent);

        // 4. Optional: finish this screen so they can't go back
        finish();
    }
}