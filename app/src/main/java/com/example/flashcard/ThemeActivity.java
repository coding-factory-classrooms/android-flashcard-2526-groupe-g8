package com.example.flashcard;

import android.content.DialogInterface;
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

public class ThemeActivity extends AppCompatActivity {

    public static final String TAG = "themeActivity";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_theme);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        button = findViewById(R.id.enjoymentButton); // Id manquant pour le bouton
//
//        button.setOnClickListener(view -> {
//            MyBottomSheet sheet = new MyBottomSheet();
//            // affichage de la sheet
//            sheet.show(getSupportFragmentManager(), "difficulty");
//
//            //sheet.show(getSupportFragmentManager(), sheet.getTag());
//
//        };

    buttonEnjoyement = findViewById(R.id.enjoymentButton); // Id manquant pour le bouton
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showListView();
        }
    });

    }

    private void showListView() {
        String[] items = {
                "Facile",
                "Moyen",
                "Difficile",
        };
        Log.i("TAG", "showListView: ");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select diff");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Traitement du choix de l'utilisateur a faire ici
                Log.d(TAG, "onClick: " + which);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
