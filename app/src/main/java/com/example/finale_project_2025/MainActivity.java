package com.example.finale_project_2025;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);


        HashMap<String, String> users = new HashMap<>();
        users.put("ben", "ben");
        users.put("alon", "alon");
        users.put("shai", "shai");
        users.put("ofer","ofer");
        users.put("nitzan","nitzan");
        users.put("karin","karin");
        users.put("yotam","yotam");
        users.put("kirby","kirby");
        users.put("yael","yael");


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString();

                if (users.containsKey(username) && users.get(username).equals(password)) {

                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid login details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}