package com.example.flashcard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListQuestionAdapter extends RecyclerView.Adapter<ListQuestionAdapter.ViewHolder> {

    private ArrayList<Question> questions;

    public ListQuestionAdapter(ArrayList<Question> questions) {

        this.questions = questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //permet d'initialiser la liste au début
        View view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.item_listquestions, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {

        return questions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView image; //
        final TextView title; //

        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            Question question = questions.get(position);
            holder.title.setText(question.getQuestionText());
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.itemQimage); //
            title = itemView.findViewById(R.id.itemQtitle); //
        }
    }
}
