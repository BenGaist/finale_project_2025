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
import android.view.View;

public class MainActivity2 extends AppCompatActivity {
    ArrayList<Question> questionsList;
    int currentQuestionIndex;
    int score;
    TextView leftTextView;
    TextView rightTextView;
    TextView welcomeText;
    Button seeScoreButton;
    Button QuitButton;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new androidx.core.view.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            }
        });

        QuitButton = findViewById(R.id.quitButton);

        QuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        currentQuestionIndex = 0;
        score = 0;

        leftTextView = findViewById(R.id.leftTextView);
        rightTextView = findViewById(R.id.rightTextView);
        welcomeText = findViewById(R.id.welcomeText);
        seeScoreButton = findViewById(R.id.seeScoreButton);

        username = getIntent().getStringExtra("username");
        welcomeText.setText("Hello " + username + "!");

        Button lessThan = findViewById(R.id.lessThanButton);
        Button equal = findViewById(R.id.equalButton);
        Button greaterThan = findViewById(R.id.greaterThanButton);

        lessThan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer('<');
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer('=');
            }
        });

        greaterThan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer('>');
            }
        });

        seeScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToScoreScreen();
            }
        });

        questionsList = populateQuestions(10);
        showQuestion();
    }

    private ArrayList<Question> populateQuestions(int count) {
        ArrayList<Question> list = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < count; i++) {
            int left = rand.nextInt(100);
            int right = rand.nextInt(100);
            list.add(new Question(left, right));
        }

        return list;
    }

    private void showQuestion() {
        Question current = questionsList.get(currentQuestionIndex);
        leftTextView.setText(String.valueOf(current.getLeft()));
        rightTextView.setText(String.valueOf(current.getRight()));
    }

    private void checkAnswer(char selected) {
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
            showGameOverDialog();
        }
    }

    private void showGameOverDialog() {

        String[] motivations = {
                "Great job!", "Keep it up!", "You’re getting smarter!", "You rock!", "Nice brain power!"
        };

        String motivation = motivations[new Random().nextInt(motivations.length)];
        int percentage = (score * 100) / questionsList.size();

        GameResultDatabaseHelper db = new GameResultDatabaseHelper(this);
        db.insertResult(username, percentage, motivation);

        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        intent.putExtra("username",username);
        startActivity(intent);
        finish();
    }

    private void goToScoreScreen() {
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        intent.putExtra("username",username);
        startActivity(intent);
        finish();
    }
}
