package com.example.flashcard;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class ListQuestionActivity extends AppCompatActivity {

    private ArrayList<Question> questions;
    private ListQuestionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_question);
        questions = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            questions.add(new Question("Qui est Novak Djokovic ?", Arrays.asList("Un joueur de tennis", "Un footballeur", "Un nageur", "Un rappeur"), 0,"facile","nan.mp3"));

        }

        adapter = new ListQuestionAdapter(questions);

        RecyclerView recyclerView = findViewById(R.id.ListeQuestions);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}