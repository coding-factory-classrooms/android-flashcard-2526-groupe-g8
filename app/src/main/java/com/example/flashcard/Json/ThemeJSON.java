package com.example.flashcard.Json;

import android.content.Context;

import com.example.flashcard.Theme;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ThemeJSON {

    private ArrayList<Theme> themes;
    public ArrayList<Theme> getThemes() {
        return themes;
    }
    public static ThemeJSON loadFromJSON(Context context, int idJson) {
        Gson gson = new Gson();
        InputStream is = context.getResources().openRawResource(idJson);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return gson.fromJson(reader, ThemeJSON.class);
    }
}
