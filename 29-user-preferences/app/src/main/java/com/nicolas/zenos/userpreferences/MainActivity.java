package com.nicolas.zenos.userpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button save;
    private TextInputEditText inputName;
    private TextView textName;
    private static String ARCHIVE_PREFERENCE = "ArchivePreference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = findViewById(R.id.buttonSave);
        inputName = findViewById(R.id.inputName);
        textName = findViewById(R.id.textName);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences  preferences = getSharedPreferences(ARCHIVE_PREFERENCE, 0);
                SharedPreferences.Editor editor = preferences.edit();

                //Validar o nome
                if (inputName.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Preencha o nome", Toast.LENGTH_LONG).show();
                } else {
                    String name = inputName.getText().toString();
                    editor.putString("name", name);
                    editor.commit();
                    textName.setText("Olá, " + name);
                }
            }
        });

        //Recuperar dados salvos
        SharedPreferences  preferences = getSharedPreferences(ARCHIVE_PREFERENCE, 0);

        //Validar se temos o nome nas preferences
        if (preferences.contains("name")) {
            String name = preferences.getString("name", "usuário não definido");
            textName.setText("Olá, " + name);
        } else {
            textName.setText("Olá, usuário não definido");
        }
    }
}