package com.example.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;



import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EsterEggActivity extends AppCompatActivity {

    Button button;

// Values of displacements
    int[] displacementPosX = {50,20,-60};
    int[] displacementPosY = {20,-120,80};
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ester_egg);

        Intent intent = new Intent(this , RewardActivity.class);

        Button validDynamicBtn = findViewById(R.id.validDynamicBtn);
        validDynamicBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (counter < 10) {
                int idx = counter % displacementPosX.length;   // loop advance
                v.setTranslationX(displacementPosX[idx]);
                v.setTranslationY(displacementPosY[idx]);
                counter++;
            } else {
                    startActivity(intent);
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}