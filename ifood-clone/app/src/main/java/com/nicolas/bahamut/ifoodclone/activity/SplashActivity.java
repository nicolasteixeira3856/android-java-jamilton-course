package com.nicolas.bahamut.ifoodclone.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.nicolas.bahamut.ifoodclone.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                abrirAutenticacao();
            }
        }, 3000);

    }

    private void abrirAutenticacao(){
        Intent i = new Intent(SplashActivity.this, AutenticacaoActivity.class);
        startActivity(i);
        finish();
    }

}
