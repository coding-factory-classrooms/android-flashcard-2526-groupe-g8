package com.example.flashcard;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import Question.Question;


public class QuestionJSON {

    private ArrayList<Question> questions;
    public ArrayList<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public static QuestionJSON loadFromJSON(Context context, int idJson) {
        Gson gson = new Gson();
        InputStream is = context.getResources().openRawResource(idJson);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return gson.fromJson(reader, QuestionJSON.class);
    }
}
