package com.example.flashcard.Json;

import android.content.Context;

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
}
