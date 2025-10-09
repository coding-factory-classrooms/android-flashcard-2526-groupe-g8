package com.example.flashcard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ModeActivity extends AppCompatActivity {
    public String difficulty = "";
    public int compteur = 0;
    public static final String TAG = "themeActivity";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mode);

        // button one question

        Intent intent2 = new Intent(this, ListQuestionActivity.class);

        Button oneQuestionButton = findViewById(R.id.oneQuestionButton);
        oneQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainActivity2", "About Click Mode !");
                startActivity(intent2);
            }
        });

        // button multiple question

        Intent intent3 = new Intent(this, ListQuestionActivity.class);

        Button multipleQuestionButton = findViewById(R.id.multipleQuestionButton);
        multipleQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainActivity2", "About Click Mode !");
                // int compteur = 0;
                if (difficulty.isEmpty() || compteur>0){ // compteur pour savoir si on a déjà choisi un diff
                    showListView();
                    compteur+= 1;
                } else {
                    startActivity(intent3);
                }

            }

            // AlerteTag for multiple

            void showListView() {
                String[] items = {
                        "Facile",
                        "Moyen",
                        "Difficile",
                };
                Log.i("TAG", "showListView: ");
                AlertDialog.Builder builder = new AlertDialog.Builder(ModeActivity.this);
                builder.setTitle("Select diff");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: " + which);
                        // String difficulty = "";
                        switch (which) {
                            case 0:
                                difficulty = "easy";
                                break;
                            case 1:
                                difficulty = "medium";
                                break;
                            case 2:
                                difficulty = "hard";
                                break;
                        }

                        Intent intent = new Intent(ModeActivity.this, ListQuestionActivity.class);
                        intent.putExtra("difficulty" , difficulty);
                        startActivity(intent);
                        Log.d(TAG, "onClick: " + which);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



}
