package com.example.flashcard;

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

public class RewardActivity extends AppCompatActivity {
    Button bouton;

    // score value to print in message


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reward);

        // Button to go to retry
        Intent intent = new Intent(this , QuestionActivity.class);

        Button retryBtn = findViewById(R.id.retryBtn);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
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

        // Button to go to home
        Intent intent3 = new Intent(this , RewardActivity.class);

        Button shareBtn = findViewById(R.id.shareBtn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // recuperation of score
                Intent srcIntent = getIntent();
                int totalAns = srcIntent.getIntExtra("scoreValue",0);
                int maxQuestion = srcIntent.getIntExtra("totalQuestion" , 0);

                TextView scoretxt = findViewById(R.id.scoreText);
                scoretxt.setText(totalAns + "" + "/" + maxQuestion);
                // sharing score message
                Intent sendMessageIntent = new Intent();
                sendMessageIntent.setAction(Intent.ACTION_SEND);
                sendMessageIntent.putExtra(Intent.EXTRA_TEXT, "J'ai eu" + totalAns +"/" + maxQuestion + " au quiz Difficile sur l'app FlashCard !");
                sendMessageIntent.setType("text"); // indicates the type of content shared via the Intent (the MIME type).

                Intent shareIntent = Intent.createChooser(sendMessageIntent, null);
                startActivity(shareIntent);

            }
        });



//        // sharing score pic
//        Intent shareIntent = new Intent();
//        shareIntent.setAction(Intent.ACTION_SEND);
//// Example: content://com.google.android.apps.photos.contentprovider/...
//        shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
//        shareIntent.setType("image/jpeg");
//        startActivity(Intent.createChooser(shareIntent, null));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}