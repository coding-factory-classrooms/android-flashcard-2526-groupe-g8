package com.example.flashcard;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcard.Activity.MainActivity;
import com.example.flashcard.Activity.ModeActivity;
import com.example.flashcard.Activity.QuestionActivity;
import com.example.flashcard.Utils.AudioKit;

import java.util.ArrayList;

public class ListQuestionAdapter extends RecyclerView.Adapter<ListQuestionAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<Question> Questions;

    public ListQuestionAdapter(ArrayList<Question> questions) {

        Questions = questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //permet d'initialiser la liste au début
        View view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.item_listquestions, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = Questions.get(position);
        holder.title.setText("Jouir c'est cool");

        holder.itemView.setTag(question);
        holder.itemView.setOnClickListener(this);

        holder.jouirBtn.setOnClickListener(v ->
                AudioKit.play(v.getContext(), AudioKit.Sfx.QUESTION, question.getAudioFile())
        );
    }

    @Override
    public int getItemCount() {

        return Questions.size();
    }

    //@Override
    public void onClick(View view) {
        Question question = (Question) view.getTag();
        Log.d("QuestionAdapter", "Question cliquée : "
                + question.getQuestionText()
                + " — Réponses : " + question.getOptions()
                + " — Bonne réponse : " + question.getAudioFile());

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(question);

        // Création de l’intent
        Context context = view.getContext();
        Intent intent = new Intent(context, QuestionActivity.class);
        intent.putExtra("questions", questions);
        context.startActivity(intent);
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView title; //
        final Button jouirBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemQtitle);
            jouirBtn = itemView.findViewById(R.id.jouirBtn);
        }
    }
}
