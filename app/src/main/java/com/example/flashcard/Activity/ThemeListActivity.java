package com.example.flashcard.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcard.R;
import com.example.flashcard.Theme;
import com.example.flashcard.ThemeAdapter;
import com.example.flashcard.Json.ThemeJSON;
import com.example.flashcard.Utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

public class ThemeListActivity extends AppCompatActivity {

    private List<Theme> themes;
    private ThemeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_theme_list);

        UiUtils.updateTitleName(this, "Thèmes");

        themes = new ArrayList<>();

        //Get json theme

        ThemeJSON themeData = ThemeJSON.loadFromJSON(this, R.raw.json_theme);
        themes = themeData.getThemes();

        adapter = new ThemeAdapter(themes);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}