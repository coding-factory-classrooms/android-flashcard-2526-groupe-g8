package com.example.flashcard;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

import Question.Question;

public class QuestionActivity extends AppCompatActivity {

    private Question currentQuestion;
    private int questionIndex = 0;
    private Question[] questions;
    private MediaPlayer mediaQuestion, winSound, loseSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        TextView txtQuestion = findViewById(R.id.NameTextQuestion);
        RadioGroup rg = findViewById(R.id.radioGroupQuestion);
        Button btnValidate = findViewById(R.id.validateButton);
        Button btnPlaySong = findViewById(R.id.buttonPlaySong1);
        final int[] totalAnswer = {0};

        mediaQuestion = MediaPlayer.create(this, R.raw.oui);
        winSound = MediaPlayer.create(this, R.raw.oui);
        loseSound = MediaPlayer.create(this, R.raw.nan);

        Intent intent = new Intent(this, MainActivity.class);

        questions = new Question[]{
                new Question("Qui est Novak Djokovic ?", Arrays.asList("Un joueur de tennis", "Un footballeur", "Un nageur", "Un rappeur"), 0),
                new Question("Comment s'appelle Celestin ?", Arrays.asList("Jean", "Pierre", "Marine", "Eric"), 0)
        };

        loadQuestion(txtQuestion, rg);

        btnPlaySong.setOnClickListener(v -> {
            if (mediaQuestion.isPlaying()) {
                mediaQuestion.pause();
                mediaQuestion.seekTo(0);
            }
            mediaQuestion.start();
        });

        btnValidate.setOnClickListener(v -> {
            int selectedId = rg.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Choisis une réponse !", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton selected = findViewById(selectedId);
            int selectedIndex = rg.indexOfChild(selected);

            if (currentQuestion.isCorrect(selectedIndex)) {
                winSound.seekTo(0);
                winSound.start();
                totalAnswer[0]++ ;
                Toast.makeText(this, "Bonne réponse", Toast.LENGTH_SHORT).show();
            } else {
                loseSound.seekTo(0);
                loseSound.start();
                Toast.makeText(this, "Mauvaise réponse", Toast.LENGTH_SHORT).show();
            }


            // Next Question or end game
            questionIndex++;
            if (questionIndex < questions.length) {
                loadQuestion(txtQuestion, rg);
            } else {
                Toast.makeText(this, "Fin du quiz !", Toast.LENGTH_LONG).show();
                //transfer data for dysplay result
                intent.putExtra("scoreValue" , totalAnswer);
                intent.putExtra("TotalQuestion" , questions.length);
                //put question no correct in a new string


                startActivity(intent);
            }
        });
    }

    private void loadQuestion(TextView txtQuestion, RadioGroup rg) {
        currentQuestion = questions[questionIndex];
        txtQuestion.setText(currentQuestion.getQuestionText());
        for (int i = 0; i < rg.getChildCount(); i++) {
            RadioButton rb = (RadioButton) rg.getChildAt(i);
            rb.setText(currentQuestion.getOptions().get(i));
        }
        rg.clearCheck();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaQuestion != null) mediaQuestion.release();
        if (winSound != null) winSound.release();
        if (loseSound != null) loseSound.release();
    }
}