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
import java.util.List;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    ArrayList<Question> questionsList;
    int score;
    int questionCount;
    Question currentQuestion;
    TextView leftTextView;
    TextView rightTextView;
    TextView welcomeText;
    Button seeScoreButton;
    Button quitButton;
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


        leftTextView = findViewById(R.id.leftTextView);
        rightTextView = findViewById(R.id.rightTextView);
        welcomeText = findViewById(R.id.welcomeText);
        seeScoreButton = findViewById(R.id.seeScoreButton);
        quitButton = findViewById(R.id.quitButton);


        username = getIntent().getStringExtra("username");
        welcomeText.setText("Hello " + username + "!");


        score = 0;
        questionCount = 0;
        currentQuestion = populateQuestion();
        showQuestion(currentQuestion);


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


        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        seeScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
    }

    private Question populateQuestion() {

        Random rand = new Random();


        int left = rand.nextInt(100);
        int right = rand.nextInt(100);

        return new Question(left, right);
    }

    private void showQuestion(Question other) {

        leftTextView.setText(String.valueOf(other.getLeft()));
        rightTextView.setText(String.valueOf(other.getRight()));
    }

    private void checkAnswer(char selected)
    {

        if (currentQuestion.getCorrectAnswer() == selected) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong! Correct answer was " + currentQuestion.getCorrectAnswer(), Toast.LENGTH_SHORT).show();
        }

        questionCount++;

        currentQuestion = populateQuestion();
        showQuestion(currentQuestion);

    }

    private void showGameOverDialog() {
        String[] motivations = {
                "Great job!", "Keep it up!", "You’re getting smarter!", "You rock!", "Nice brain power!"
        };

        String motivation = motivations[new Random().nextInt(motivations.length)];
        int percentage = (score * 100) / questionCount;


        NoteDatabase db = NoteDatabase.getInstance(this);
        Note note = new Note(username, percentage, motivation);
        db.noteDao().insert(note);


        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    private void goToScoreScreen() {
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    private void showConfirmationDialog() {
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Finish Game?")
                .setMessage("Are you sure you want to end the game and see your score?")
                .setPositiveButton("Yes", new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(android.content.DialogInterface dialog, int which) {
                        showGameOverDialog();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
