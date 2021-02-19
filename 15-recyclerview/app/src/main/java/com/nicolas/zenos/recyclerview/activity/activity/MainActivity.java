package com.nicolas.zenos.recyclerview.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.nicolas.zenos.recyclerview.R;
import com.nicolas.zenos.recyclerview.activity.adapter.Adapter;
import com.nicolas.zenos.recyclerview.activity.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Movie> listMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Recylcer
        recyclerView = findViewById(R.id.recyclerView);
        //Listagem de filmes
        this.createMovies();
        //Configurar adapter
        Adapter adapter = new Adapter(listMovies);
        //Configurar recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    public void createMovies() {
        Movie movie = new Movie("Senhor dos Anéis - A Sociedaed do Anel", "Fantasia", "2001");
        this.listMovies.add(movie);

        movie = new Movie("Senhor dos Anéis - As Duas Torres", "Fantasia", "2002");
        this.listMovies.add(movie);

        movie = new Movie("Senhor dos Anéis - O Retorno do Rei", "Fantasia", "2003");
        this.listMovies.add(movie);

        movie = new Movie("Mulan", "Desenho", "2002");
        this.listMovies.add(movie);

        movie = new Movie("X-men", "Ação", "2000");
        this.listMovies.add(movie);

        movie = new Movie("O Hobbit", "Fantasia", "2012");
        this.listMovies.add(movie);
    }
}