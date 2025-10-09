package com.example.flashcard.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.flashcard.R;

public class ListQuestionsChooserActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "ListQuestionsChooserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.itemQtitle).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    private void navigateToMain(String questionText) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("title", questionText);

        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.itemQtitle) {
            navigateToMain("question 1");
        //} else if (id == R.id.japanButton) {
        //    navigateToMain(176f, R.drawable.flag_japan, "Y");
        //} else if (id == R.id.brazilButton) {
        //    navigateToMain(6.20f, R.drawable.flag_brazil, "R");
        }
    }
}
