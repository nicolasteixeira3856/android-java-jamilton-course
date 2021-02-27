package com.nicolas.zenos.myanotations;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private AnotationPreferences preferences;
    private EditText editAnotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAnotation = findViewById(R.id.editAnotation);

        preferences = new AnotationPreferences(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Validar se foi digitado algo
                String text = editAnotation.getText().toString();
                if( text.equals("") ){
                    Snackbar.make(view, "Preencha a anotaçao!", Snackbar.LENGTH_LONG).show();
                } else {
                    preferences.saveAnotation( text );
                    Snackbar.make(view, "Anotaçao salva com sucesso!", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        //Recuperar anotation
        String anotation = preferences.loadAnotation();
        if( !anotation.equals("") ){
            editAnotation.setText( anotation );
        }
    }
}