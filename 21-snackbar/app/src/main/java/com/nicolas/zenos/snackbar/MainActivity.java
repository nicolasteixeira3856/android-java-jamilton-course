package com.nicolas.zenos.snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button buttonSnackbar, closeSnackbar;
    //private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSnackbar = findViewById(R.id.openSnackbar);
        closeSnackbar = findViewById(R.id.closeSnackbar);

        buttonSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(
                  v,
                  "Botão pressionando",
                  Snackbar.LENGTH_LONG
                ).setAction("Confirmar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonSnackbar.setText("Abrir alterado");
                    }
                }).setActionTextColor(getResources().getColor(R.color.white))
                .show();

                //snackbar.show();
            }
        });

        /*closeSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });*/
    }
}