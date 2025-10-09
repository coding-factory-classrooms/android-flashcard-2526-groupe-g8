package com.example.flashcard;

import android.content.Intent;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button button;

    public static final String TAG = "MainActivity";

    @SuppressLint("MissingInflatedId") // affiche un avertissment pour les Id des élément manquent
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // button to go on AboutActivity
        Intent intent = new Intent(this, AboutActivity.class);

        Button aboutBtn = findViewById(R.id.aboutBtn);
        aboutBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("MainActivity", "About Click !");
                startActivity(intent);
            }
        });

        // button to go on ModeActivity

        Intent intent2 = new Intent(this, ModeActivity.class);

        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainActivity2", "About Click Mode !");
                startActivity(intent2);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d(TAG, "Hello Flashcard !");
    }
}