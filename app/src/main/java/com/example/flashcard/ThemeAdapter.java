package com.example.flashcard;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> implements View.OnClickListener {

    private List<Theme> Themes;

    public ThemeAdapter(List<Theme> themes) {
        Themes = themes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_theme,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Theme theme = Themes.get(position);
        holder.Title.setText(theme.getTitle());
        holder.Description.setText(theme.getDescription());
        holder.itemView.setTag(theme);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return Themes.size();
    }

    @Override
    public void onClick(View view) {
        Log.i("Action" , "On Click");




    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView Title;
        final TextView Description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.titleTheme);
            Description = itemView.findViewById(R.id.descriptionTheme);

        }
    };

}
