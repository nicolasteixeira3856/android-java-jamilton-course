package com.nicolas.zenos.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBarHorizontal, progressBarCircular;
    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBarHorizontal = findViewById(R.id.progressBarHorizontal);
        progressBarCircular = findViewById(R.id.progressBarCircular);
        progressBarCircular.setVisibility(View.GONE);
    }

    public void loadProgressBar(View view) {
        this.progress = this.progress + 10;
        progressBarHorizontal.setProgress(this.progress);
        progressBarCircular.setVisibility(View.VISIBLE);
        if (this.progress == 100) {
            progressBarCircular.setVisibility(View.GONE);
        }
    }
}