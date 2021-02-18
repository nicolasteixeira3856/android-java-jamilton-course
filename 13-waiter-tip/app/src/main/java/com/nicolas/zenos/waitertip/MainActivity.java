package com.nicolas.zenos.waitertip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText value;
    private TextView percentageText;
    private TextView tip;
    private TextView total;
    private SeekBar seekBarTip;

    private double percentage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value               = findViewById(R.id.value);
        tip                 = findViewById(R.id.tip);
        percentageText      = findViewById(R.id.percentage);
        total               = findViewById(R.id.total);
        seekBarTip          = findViewById(R.id.seekBarTip);

        seekBarTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percentage = progress;
                percentageText.setText(Math.round( percentage ) + " %");
                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void calculate(){
        String inputValue = value.getText().toString();
        if( inputValue == null || inputValue.equals("") ){
            Toast.makeText(
                    getApplicationContext(),
                    "Digite um valor primeiro!",
                    Toast.LENGTH_SHORT
            ).show();
        }else {
            //Converter string para double
            double typedValue = Double.parseDouble(inputValue);
            //calcula a gorjeta total
            double tipValue = typedValue * (percentage/100);
            double totalValue = tipValue + typedValue;
            //exibe a gorjeta e total
            tip.setText("R$ " + Math.round(tipValue));
            total.setText("R$ " + totalValue);
        }
    }
}