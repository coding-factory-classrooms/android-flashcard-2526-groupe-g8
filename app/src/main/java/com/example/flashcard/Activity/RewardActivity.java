package com.example.flashcard.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcard.ListQuestionAdapter;
import com.example.flashcard.Question;
import com.example.flashcard.R;
import com.example.flashcard.Utils.UiUtils;

import java.util.ArrayList;

public class RewardActivity extends AppCompatActivity {
    Button bouton;
    // score value to print in message

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reward);

        UiUtils.updateTitleName(this, "GouiGoui man");

        // print of score and difficulty
        // recuperation of score
        Intent srcIntent = getIntent();
        int totalAns = srcIntent.getIntExtra("scoreValue",0);
        int maxQuestion = srcIntent.getIntExtra("totalQuestion" , 0);
        String difficulty = srcIntent.getStringExtra("difficulty");

        TextView scoretxt = findViewById(R.id.scoreText);
        TextView difficulytxt = findViewById(R.id.difficultyText);
        TextView commentTxt = findViewById(R.id.commentTxt);

        int correct = totalAns;
        int total   = maxQuestion;
        double ratio = (total > 0) ? (double) correct / total : 0.0;
        int percent  = (int) Math.round(ratio * 100.0);

        String comment;
        if (percent < 50) {
            comment = "Tu pues la merde";
        } else if (percent <= 75) {
            comment = "Ça va en vrai";
        } else { // >75%
            comment = "T'es bon mon frr";
        }

        scoretxt.setText(String.format(java.util.Locale.getDefault(),
                "Score : %d/%d (%d%%)", correct, total, percent));
        difficulytxt.setText("Difficulté choisie : " + difficulty);
        commentTxt.setText(comment);


        ArrayList<Question> wrongAnswer = srcIntent.getParcelableArrayListExtra("wrongAnswer");


        RecyclerView recyclerView = findViewById(R.id.ListeQuestions);

        // wrongAnswer contains only questions failed
        ListQuestionAdapter adapter = new ListQuestionAdapter(wrongAnswer);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // hide if none bad answer
        if (wrongAnswer.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
        }


        // Button to go to retry
        Intent intent = new Intent(this , QuestionActivity.class);
        intent.putExtra("questions", wrongAnswer);
        Button retryBtn = findViewById(R.id.retryBtn);

        if (wrongAnswer.isEmpty()) {
            retryBtn.setVisibility(View.GONE);
        } else {
            retryBtn.setVisibility(View.VISIBLE);
        }

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!wrongAnswer.isEmpty()){
                    startActivity(intent);
                }
            }
        });

        // Button to go to home
        Intent intent2 = new Intent(this , MainActivity.class);

        Button homeBtn = findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });

        // Ester egg button
        Intent intent3 = new Intent(this , EsterEggActivity.class);

        Button esterEggBtn = findViewById(R.id.esterEggBtn);
        esterEggBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent3);
            }
        });

        // Button to go to home
        Intent intent4 = new Intent(this , RewardActivity.class);

        Button shareBtn = findViewById(R.id.shareBtn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // sharing score message
                Intent sendMessageIntent = new Intent();
                sendMessageIntent.setAction(Intent.ACTION_SEND);
                sendMessageIntent.putExtra(Intent.EXTRA_TEXT, "J'ai eu " + totalAns +"/" + maxQuestion + " au quiz Difficile sur l'app FlashCard !");
                sendMessageIntent.setType("text/plain"); // indicates the type of content shared via the Intent (the MIME type).

                Intent shareIntent = Intent.createChooser(sendMessageIntent, null);
                startActivity(shareIntent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}