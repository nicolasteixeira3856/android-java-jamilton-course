package com.nicolas.zenos.recyclerview.activity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nicolas.zenos.recyclerview.R;
import com.nicolas.zenos.recyclerview.activity.RecyclerItemClickListener;
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
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Movie movie = listMovies.get(position);
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Item click: " + movie.getMovieTitle(),
                                        Toast.LENGTH_LONG
                                ).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Movie movie = listMovies.get(position);
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Long click: " + movie.getMovieTitle(),
                                        Toast.LENGTH_LONG
                                ).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Item pressionado",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        }
                )
        );
        recyclerView.setAdapter(adapter);
    }

    public void createMovies() {
        Movie movie = new Movie("Senhor dos Anéis - A Sociedade do Anel", "Fantasia", "2001");
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