package com.webapp.jokes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JokeViewAdaptor extends RecyclerView.Adapter<JokeViewHolder> {

    Context context;
    List<Joke> jokes;

    public JokeViewAdaptor(Context context, List<Joke> jokes) {
        this.context = context;
        this.jokes = jokes;
    }

    @NonNull
    @Override
    public JokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JokeViewHolder(LayoutInflater.from(context).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull JokeViewHolder holder, int position) {
        holder.textView.setText(jokes.get(position).getJoke());

    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }
}
