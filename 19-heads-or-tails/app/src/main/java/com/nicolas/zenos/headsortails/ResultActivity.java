package com.nicolas.zenos.headsortails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class ResultActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        image = findViewById(R.id.imageResult);
        int number = new Random().nextInt(2);
        if (number == 0) {
            image.setImageResource(R.drawable.moeda_cara);
        } else {
            image.setImageResource(R.drawable.moeda_coroa);
        }
    }

    public void finish(View view) {
        finish();
    }
}