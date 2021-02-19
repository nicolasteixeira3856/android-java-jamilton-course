package com.nicolas.zenos.recyclerview.activity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nicolas.zenos.recyclerview.R;
import com.nicolas.zenos.recyclerview.activity.model.Movie;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Movie> listMovies;

    public Adapter(List<Movie> list) {
        this.listMovies = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_list, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie movie = listMovies.get(position);
        holder.title.setText(movie.getMovieTitle());
        holder.year.setText(movie.getYear());
        holder.genre.setText(movie.getGenre());
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView year;
        TextView genre;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textTitle);
            year = itemView.findViewById(R.id.textYear);
            genre = itemView.findViewById(R.id.textGenre);
        }
    }

}
