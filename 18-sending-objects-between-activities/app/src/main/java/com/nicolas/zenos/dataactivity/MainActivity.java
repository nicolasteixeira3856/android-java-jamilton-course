package com.nicolas.zenos.dataactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User("Nícolas Tiago", "nicolas@gmail.com");
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                intent.putExtra("name", "Nícolas Teixeira");
                intent.putExtra("age", 20);
                intent.putExtra("object", user);
                startActivity(intent);
            }
        });
    }
}