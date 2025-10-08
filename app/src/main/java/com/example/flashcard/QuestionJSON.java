package com.example.flashcard;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import Question.Question;


public class QuestionJSON {

    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    // Store a JSON file in Raw/
    public static QuestionJSON loadFromJSON(Context context) {
        Gson gson = new Gson();
        InputStream is = context.getResources().openRawResource(R.raw.joui);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return gson.fromJson(reader, QuestionJSON.class);
    }
}
