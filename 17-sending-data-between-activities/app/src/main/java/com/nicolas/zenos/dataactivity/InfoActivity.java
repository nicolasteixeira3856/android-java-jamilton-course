package com.nicolas.zenos.dataactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Telephony;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InfoActivity extends AppCompatActivity {

    private TextView textName, textAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        textName = findViewById(R.id.name);
        textAge = findViewById(R.id.age);

        Bundle data = getIntent().getExtras();
        String name = data.getString("name");
        int age = data.getInt("age");

        textName.setText(name);
        textAge.setText(String.valueOf(age));
    }
}