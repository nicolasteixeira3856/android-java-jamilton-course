package com.nicolas.zenos.cardview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.nicolas.zenos.cardview.R;
import com.nicolas.zenos.cardview.adapter.Adapter;
import com.nicolas.zenos.cardview.model.Posts;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Posts> postsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        //Define layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ////Pode setar na vertical e horizontal, mudando o jeito que a lista da scroll
        layoutManager.setOrientation(LinearLayout.HORIZONTAL);
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        //Define adapter
        this.preparePosts();
        Adapter adapter = new Adapter(postsList);
        recyclerView.setAdapter(adapter);
    }

    public void preparePosts(){
        Posts posts = new Posts("Nícolas Teixeira", "Viagem daora!", R.drawable.imagem1);
        this.postsList.add(posts);

        posts = new Posts("Tiago Teixeira", "Nova Zelândia!", R.drawable.imagem2);
        this.postsList.add(posts);

        posts = new Posts("Percival", "Viagem daora!", R.drawable.imagem3);
        this.postsList.add(posts);

        posts = new Posts("Viktor", "Viagem daora!", R.drawable.imagem4);
        this.postsList.add(posts);
    }
}