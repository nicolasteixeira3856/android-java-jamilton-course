package com.cursoandroid.jamiltondamasceno.admob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cursoandroid.jamiltondamasceno.admob.config.ConfigAd;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    private Button btnRewardedAd;
    private Button btnNativeAd;
    private AdView adView;
    private InterstitialAd interstitialAd;
    private ConfigAd configAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initComponents();
    }

    private void initComponents(){

        configAd = new ConfigAd(this);

        btnNativeAd = findViewById(R.id.btnNativeAd);
        btnRewardedAd = findViewById(R.id.btnRewardedAd);
        adView = findViewById(R.id.bannerAd);
        interstitialAd = configAd.createInterstitialAd();

        events();

        //Carrega o bloco de anúncio
        adView.loadAd(configAd.adRequest());

    }

    private void events() {

        btnRewardedAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configAd.eventInterstitialAd(0);
            }
        });

        btnNativeAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configAd.eventInterstitialAd(1);
            }
        });

    }

}