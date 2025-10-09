package com.example.flashcard;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListQuestionAdapter extends RecyclerView.Adapter<ListQuestionAdapter.ViewHolder> implements View.OnClickListener {

    private List<Question> Questions;

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
        holder.title.setText(question.getQuestionText());
        holder.difficulty.setText(question.getDifficulty());

        holder.itemView.setTag(question);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {

        return Questions.size();
    }

    //@Override
    public void onClick(View view) {
        Log.i("ListQuestionAdapter" , "On Click:class");
        switch (view.getId()) {
            case R.id.itemQlist:
                Context context = view.getContext();
                Question q = (Question) view.getTag();
                Intent intent = new Intent(context, MainActivity.class);

                context.startActivity(intent);
                break;
        }
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView title; //
        final TextView difficulty; //

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemQtitle); //
            difficulty = itemView.findViewById(R.id.itemQdifficulty); //

        }
    }
}
