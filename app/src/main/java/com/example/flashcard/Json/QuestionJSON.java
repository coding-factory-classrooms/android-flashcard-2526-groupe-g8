package com.example.flashcard.Json;

import android.content.Context;

import com.example.flashcard.Theme;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.example.flashcard.Question;


public class QuestionJSON {

    private ArrayList<Question> questions;
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public static QuestionJSON loadFromJSON(Context context, int idJson) {
        Gson gson = new Gson();
        InputStream is = context.getResources().openRawResource(idJson);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return gson.fromJson(reader, QuestionJSON.class);
    }

    //Ca c'est du nommage Robin
    public static int jsonQuestionRawIdByTheme(Context ctx, Theme theme){
        String base = theme.getLink();
        int dot = base.lastIndexOf('.');
        if (dot > 0) base = base.substring(0, dot);
        return ctx.getResources().getIdentifier(base, "raw", ctx.getPackageName());
    }
}
