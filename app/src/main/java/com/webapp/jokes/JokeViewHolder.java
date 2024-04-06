package com.webapp.jokes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JokeViewHolder extends RecyclerView.ViewHolder {
    TextView textView;


    public JokeViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textview);
    }
}
