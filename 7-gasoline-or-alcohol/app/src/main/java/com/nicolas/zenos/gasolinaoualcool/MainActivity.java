package com.nicolas.zenos.gasolinaoualcool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editAlcohol, editGasoline;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editAlcohol     = findViewById(R.id.alcohol);
        editGasoline    = findViewById(R.id.gasoline);
        textResult      = findViewById(R.id.result);
    }

    public void calculate(View view) {
        String priceAlcohol     = editAlcohol.getText().toString();
        String priceGasoline    = editGasoline.getText().toString();
        Boolean validatedFiels = validate(priceAlcohol, priceGasoline);
        if (validatedFiels) {
            Double valueAlcohol = Double.parseDouble(priceAlcohol);
            Double valueGasoline = Double.parseDouble(priceGasoline);
            //Valor alcool / valor gasolina >= 0.7 é melhor utilizar gasolina
            Double result = valueAlcohol / valueGasoline;
            if (result > 0.7){
                textResult.setText("Melhor utilizar gasolina!");
            } else {
                textResult.setText("Melhor utilizar alcool!");
            }
        } else {
            textResult.setText("Preencha os campos primeiro!");
        }
    }

    public boolean validate(String priceAlcohol, String priceGasoline) {
        Boolean validate = true;

        if (priceAlcohol == null || priceAlcohol.equals("")) {
            validate = false;
        } else if (priceGasoline == null || priceGasoline.equals("")) {
            validate = false;
        }

        return validate;
    }
}