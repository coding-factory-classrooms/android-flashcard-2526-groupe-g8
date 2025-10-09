
package com.example.flashcard.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.flashcard.Json.QuestionJSON;
import com.example.flashcard.R;
import com.example.flashcard.Utils.AudioKit;
import com.example.flashcard.Utils.UiUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.flashcard.Question;

public class QuestionActivity extends AppCompatActivity {

    //region V A R S

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
    private String difficulty = null;

    // Gestion Vars
    private List<String> currentChoices;
    private int correctIndex = -1;
    private boolean isValidated = false;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        UiUtils.updateTitleName(this, "C'est l'heure de jouir !");

        txtQuestion = findViewById(R.id.NameTextQuestion);
        rg          = findViewById(R.id.radioGroupQuestion);
        btnValidate = findViewById(R.id.validateButton);
        btnPlaySong = findViewById(R.id.buttonPlaySong1);

        // JSON uniquement si l'index est 0
        QuestionJSON questionData = QuestionJSON.loadFromJSON(this, R.raw.json_joui);
        questions = questionData.getQuestions();;

        Intent intent = getIntent();

        if(difficulty == null) difficulty = intent.getStringExtra("difficulty");

        showQuestion();
        updateProgressTxt();
        updateBtnTxt();


        btnPlaySong.setOnClickListener(v -> AudioKit.play(this, AudioKit.Sfx.QUESTION, currentQuestion.getAudioFile()));
        btnValidate.setOnClickListener(v -> onValidateAnswer());
    }

    private void showQuestion() {
        currentQuestion = questions.get(questionIndex);
        // Set questionText (from json)
        txtQuestion.setText(currentQuestion.getQuestionText());
        // build choices
        //TODO: get difficulty !
        currentChoices = buildChoices(currentQuestion, difficulty);
        // find the good answer after the shuffle !! indexOf is magiiiiiikk
        correctIndex = currentChoices.indexOf(currentQuestion.getAnswer());

        // show RadioButton dynamicly because we are programmer or we not are you know or what quoicoubeh
        // Welcome in JouiQuizz's Studio dude
        //clear view
        rg.removeAllViews();
        //foreach
        for (String label : currentChoices) {
            RadioButton rb = new RadioButton(new ContextThemeWrapper(this, R.style.Widget_Flashcard_RadioButton), null, 0);
            rb.setId(View.generateViewId()); //set id
            rb.setText(label); //set text
            rg.addView(rb); // A D D  I T !!
        }
        //And clear RB checked ? i need to verify if it's useless or not
        rg.clearCheck();
    }


    private List<String> buildChoices(Question q, String d) {
        // how much fake options
        int wanted;

        switch (d) {
            case "easy":   wanted = 2; break;
            case "hard":   wanted = 4; break;
            default:       wanted = 3; //normal
        }

        // make pool of options
        List<String> pool = new ArrayList<>(q.getOptions());

        // Take the wanted number from the pool
        int take = Math.min(wanted, pool.size());
        List<String> choices = new ArrayList<>(pool.subList(0, take));

        // add the answer inside the choices
        choices.add(q.getAnswer());

        // S H U F F L A G E !
        Collections.shuffle(choices);

        return choices;
    }

    private void onValidateAnswer() {
        int selectedId = rg.getCheckedRadioButtonId();
        //verif if user select answer
        if (selectedId == -1) {
            Toast.makeText(this, "Choisis une réponse !", Toast.LENGTH_SHORT).show();
            return;
        }
        //if user not validate answer yet and he click
        if(!isValidated){
            RadioButton selected = findViewById(selectedId);
            int selectedIndex = rg.indexOfChild(selected);

            if (selectedIndex == correctIndex) {
                AudioKit.play(this, AudioKit.Sfx.WIN, null);
                totalAnswer++;
                Toast.makeText(this, "Bonne réponse", Toast.LENGTH_SHORT).show();
                highlightChoicesCorrect(selectedIndex);
            } else {
                AudioKit.play(this, AudioKit.Sfx.LOSE, null);
                wrongAnswer.add(currentQuestion);
                Toast.makeText(this, "Mauvaise réponse", Toast.LENGTH_SHORT).show();
                highlightChoicesWrong(selectedIndex, correctIndex);
            }

            //change name and boolean for BTN
            isValidated = true;
            updateBtnTxt();
        }else{
            // suite
            questionIndex++;
            if (questionIndex < questions.size()) {
                isValidated = false;
                showQuestion();
                updateBtnTxt();
                updateProgressTxt();
            } else {
                //TODO: Return to REWARD
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

    private void updateProgressTxt(){
        TextView progress = findViewById(R.id.progressTxt);
        int uiQuestionIndex = questionIndex + 1;
        progress.setText(uiQuestionIndex + "/" + questions.size());
    }
    private void updateBtnTxt(){
        if (!isValidated){
            btnValidate.setText("Valider !");
        }else{
            if (questionIndex >= questions.size()) {
                btnValidate.setText("Voir le résultat !");
            }
            btnValidate.setText("Question suivante !");
        }
    }

    //set green rb
    private void highlightChoicesCorrect(int selectedIndex) {
        int green = ContextCompat.getColor(this, R.color.app_orange);
        int white = ContextCompat.getColor(this, R.color.app_white);

        //for all the RB, set the right one to green and others in white
        for (int i = 0; i < rg.getChildCount(); i++) {
            RadioButton rb = (RadioButton) rg.getChildAt(i);
            rb.setTextColor(i == selectedIndex ? green : white);
            rb.setEnabled(false);
        }
    }

    //same thing ! but the answer in red, the good answer in green, and other in white !
    private void highlightChoicesWrong(int selectedIndex, int correctIndex) {
        int green = ContextCompat.getColor(this, R.color.app_orange);
        int red   = ContextCompat.getColor(this, R.color.app_red);
        int white = ContextCompat.getColor(this, R.color.app_white);

        for (int i = 0; i < rg.getChildCount(); i++) {
            RadioButton rb = (RadioButton) rg.getChildAt(i);
            if (i == correctIndex) {
                rb.setTextColor(green);
            } else if (i == selectedIndex) {
                rb.setTextColor(red);
            } else {
                rb.setTextColor(white);
            }
            rb.setEnabled(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}