
package com.example.flashcard;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Question.Question;

public class QuestionActivity extends AppCompatActivity {

    // UI vars
    private TextView txtQuestion;
    private RadioGroup rg;
    private Button btnValidate, btnPlaySong;

    // Data vars
    private ArrayList<Question> questions;

    private ArrayList<Question> wrongAnswer = new ArrayList<Question>();
    private Question currentQuestion;
    private int questionIndex = 0;
    private int totalAnswer = 0;

    // Gestion Vars
    private List<String> currentChoices;
    private int correctIndex = -1;
    private boolean isValidated = false;

    // Audio vars

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        txtQuestion = findViewById(R.id.NameTextQuestion);
        rg = findViewById(R.id.radioGroupQuestion);
        btnValidate = findViewById(R.id.validateButton);
        btnPlaySong = findViewById(R.id.buttonPlaySong1);

        // JSON
        QuestionJSON questionData = QuestionJSON.loadFromJSON(this, R.raw.json_joui);
        questions = questionData.getQuestions();;

        showQuestion();

        btnPlaySong.setOnClickListener(v -> AudioKit.play(this, AudioKit.Sfx.QUESTION, currentQuestion.getAudioFile()));
        btnValidate.setOnClickListener(v -> onValidateAnswer());

    }

    private void showQuestion() {
        currentQuestion = questions.get(questionIndex);

        // Set questionText (from json)
        txtQuestion.setText(currentQuestion.getQuestionText());

        // build choices
        currentChoices = buildChoices(currentQuestion);
        // find the good answer after the shuffle !! indexOf is magiiiiiikk
        correctIndex = currentChoices.indexOf(currentQuestion.getAnswer());

        // show RadioButton dynamicly because we are programmer or we not are you know or what quoicoubeh
        // Welcome in JouiQuizz's Studio dude

        //clear view
        rg.removeAllViews();
        //foreach
        for (String label : currentChoices) {
            RadioButton rb = new RadioButton(this);
            rb.setId(View.generateViewId()); //set id
            rb.setText(label); //set text
            rg.addView(rb); // A D D  I T !!
        }
        //And clear RB checked ? i need to verify if it's useless or not
        rg.clearCheck();
    }

    //TODO : Faire notre propre truc, la c'est gpt en attendant et jsuis pas sur du truc
    private List<String> buildChoices(Question q) {
        // combien de distracteurs ? (ex: 3, et on clamp sur la taille)
        int wantedDistractors = Math.min(3, q.getOptions() != null ? q.getOptions().size() : 0);

        List<String> pool = new java.util.ArrayList<>(q.getOptions()); // copie
        java.util.Collections.shuffle(pool);
        List<String> picks = pool.subList(0, wantedDistractors);
        List<String> choices = new java.util.ArrayList<>(picks);
        // on ajoute la bonne réponse
        choices.add(q.getAnswer());
        // on mélange le tout
        java.util.Collections.shuffle(choices);
        return choices;
    }

    private void onValidateAnswer() {
        int selectedId = rg.getCheckedRadioButtonId();
        //verif if user select answer
        if (selectedId == -1) {
            Toast.makeText(this, "Choisis une réponse !", Toast.LENGTH_SHORT).show();
            return;
        }
        //if user not validate answer
        if(!isValidated){
            RadioButton selected = findViewById(selectedId);
            int selectedIndex = rg.indexOfChild(selected);

            if (selectedIndex == correctIndex) {
                AudioKit.play(this, AudioKit.Sfx.WIN, null);
                totalAnswer++;
                Toast.makeText(this, "Bonne réponse", Toast.LENGTH_SHORT).show();
            } else {
                AudioKit.play(this, AudioKit.Sfx.LOSE, null);
                wrongAnswer.add(currentQuestion);
                Toast.makeText(this, "Mauvaise réponse", Toast.LENGTH_SHORT).show();
            }

            //change name and boolean for BTN
            isValidated = true;
            btnValidate.setText("Question suivante");
        }else{
            // suite
            questionIndex++;
            if (questionIndex < questions.size()) {
                showQuestion();
                btnValidate.setText("Valider");
                isValidated = false;
            } else {
                //TODO: Return REWARD
                Intent intent = new Intent(this, MainActivity.class);
                Toast.makeText(this, "Fin du quiz !", Toast.LENGTH_LONG).show();

                intent.putExtra("scoreValue", totalAnswer);
                intent.putExtra("TotalQuestion", questions.size());
                intent.putExtra("wrongAnswer", wrongAnswer);

                startActivity(intent);
                finish();
            }
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}