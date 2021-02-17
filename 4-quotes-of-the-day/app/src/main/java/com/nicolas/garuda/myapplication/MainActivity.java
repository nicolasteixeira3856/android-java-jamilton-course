package com.nicolas.garuda.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pickRandomQuote (View view) {
        String [] quotes = {
                "Enfrente os problemas e leve a melhor!",
                "Para chegar ao topo, o que importa é começar!",
                "De nada adianta ter sonhos, se você não se empenhar em correr atrás",
                "Preste atenção nas oportunidades que estão à sua frente!"
        };
        int number = new Random().nextInt(4);
        TextView textResult = findViewById(R.id.textResult);
        textResult.setText(quotes[number]);
    }
}