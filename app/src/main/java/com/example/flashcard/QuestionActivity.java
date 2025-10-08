package com.example.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuestionActivity extends AppCompatActivity {

    public static final String TAG = "QuestionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Read of Information
        Intent srcIntent = getIntent();
        int EasyQuestion = srcIntent.getIntExtra("QuestionEasyLevel" , 0);
        int MediumQuestion = srcIntent.getIntExtra("QuestionMediumLevel" , 1);
        int HardQuestion = srcIntent.getIntExtra("QuestionDifficultLevel" , 2);

        String difficulty = srcIntent.getStringExtra("difficulty");

        Log.d(TAG, "onCreate: " + difficulty);
    }
}