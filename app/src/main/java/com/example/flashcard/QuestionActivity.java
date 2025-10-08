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
import java.util.List;

import Question.Question;

public class QuestionActivity extends AppCompatActivity {

    private Question currentQuestion;
    private int questionIndex = 0;
    private Question[] questions;
    private MediaPlayer mediaQuestion, winSound, loseSound;
    private boolean isValidated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        TextView txtQuestion = findViewById(R.id.NameTextQuestion);
        RadioGroup rg = findViewById(R.id.radioGroupQuestion);
        Button btnValidate = findViewById(R.id.validateButton);
        Button btnPlaySong = findViewById(R.id.buttonPlaySong1);
        Button btnNextQuestion = findViewById(R.id.NextQuestionBTN);
        final int[] totalAnswer = {0};

        mediaQuestion = MediaPlayer.create(this, R.raw.oui);
        winSound = MediaPlayer.create(this, R.raw.oui);
        loseSound = MediaPlayer.create(this, R.raw.nan);

        Intent intent = new Intent(this, MainActivity.class);
        //hide Button NexQuestion
        btnNextQuestion.setVisibility(View.GONE);


        //JSON
        QuestionJSON questionData = QuestionJSON.loadFromJSON(this);
        List<Question> questions1 = questionData.getQuestions();



        questions = new Question[]{
                new Question("Qui est Novak Djokovic ?", Arrays.asList("Un joueur de tennis", "Un footballeur", "Un nageur", "Un rappeur"), 0,"facile","nan.mp3"),
                new Question("Comment s'appelle Celestin ?", Arrays.asList("Jean", "Pierre", "Marine", "Eric"), 0,"facile" , "oui.mp3")
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
            //verif if user select answer
            if (selectedId == -1) {
                Toast.makeText(this, "Choisis une réponse !", Toast.LENGTH_SHORT).show();
                return;
            }
            //if user not validate answer
            if (!isValidated) {
                RadioButton selected = findViewById(selectedId);
                int selectedIndex = rg.indexOfChild(selected);

                if (currentQuestion.isCorrect(selectedIndex)) {
                    winSound.seekTo(0);
                    winSound.start();
                    totalAnswer[0]++;
                    Toast.makeText(this, "Bonne réponse", Toast.LENGTH_SHORT).show();
                } else {
                    loseSound.seekTo(0);
                    loseSound.start();
                    Toast.makeText(this, "Mauvaise réponse", Toast.LENGTH_SHORT).show();
                }
                //change name and boolean for BTN
                isValidated = true;
                btnValidate.setText("Question suivante");

            } else {
                //Next question
                questionIndex++;
                if (questionIndex < questions.length) {
                    loadQuestion(txtQuestion, rg);
                    btnValidate.setText("Valider");
                    isValidated = false;
                } else {
                    Toast.makeText(this, "Fin du quiz !", Toast.LENGTH_LONG).show();

                    intent.putExtra("scoreValue", totalAnswer);
                    intent.putExtra("TotalQuestion", questions.length);
                    startActivity(intent);
                }
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