package com.webapp.jokes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.JokeHolder> {

    private Context context;
    private List<Joke> jokeList;

    public JokeAdapter(Context context, List<Joke> jokeList) {
        this.context = context;
        this.jokeList = jokeList;
    }

    @NonNull
    @Override
    public JokeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
       return new JokeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JokeHolder holder, int position) {
        Joke joke = jokeList.get(position);
        holder.textView.setText(joke.getJoke());

    }

    @Override
    public int getItemCount() {
        return jokeList.size();
    }

    public class JokeHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public JokeHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
        }
    }
}
