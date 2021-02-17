package com.nicolas.zenos.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectRock(View view) {
        this.selectedOption("rock");
    }

    public void selectPaper(View view) {
        this.selectedOption("paper");
    }

    public void selectScissor(View view) {
        this.selectedOption("scissor");
    }

    public void selectedOption(String selectedOption) {
        ImageView imageResult = findViewById(R.id.imageResult);
        TextView result = findViewById(R.id.resultText);
        int number = new Random().nextInt(3);
        String[] options = {"rock", "paper", "scissor"};
        String appOption = options[number];
        switch (appOption){
            case "rock":
                imageResult.setImageResource(R.drawable.pedra);
                break;
            case "paper":
                imageResult.setImageResource(R.drawable.papel);
                break;
            case "scissor":
                imageResult.setImageResource(R.drawable.tesoura);
                break;
        }

        System.out.println("Opcao do app: " + appOption);

        if(
            (appOption.equals("scissor") && selectedOption.equals("paper")) ||
            (appOption.equals("paper") && selectedOption.equals("rock")) ||
            (appOption.equals("rock") && selectedOption.equals("scissor"))
        ){//App winner
            result.setText("Você perdeu :( ");
        }else if(
            (selectedOption.equals("scissor") && appOption.equals("paper")) ||
            (selectedOption.equals("paper") && appOption.equals("rock")) ||
            (selectedOption.equals("rock") && appOption.equals("scissor"))
        ){//User winner
            result.setText("Você ganhou :) ");
        }else{//Draw
            result.setText("Empatamos ;) ");
        }
    }
}