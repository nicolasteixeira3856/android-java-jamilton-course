package com.cursoandroid.jamiltondamasceno.admob.screenAd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cursoandroid.jamiltondamasceno.admob.R;
import com.cursoandroid.jamiltondamasceno.admob.config.ConfigAd;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

public class NativeAdActivity extends AppCompatActivity {

    private ConfigAd configAd;
    private AdLoader.Builder builderAd;
    private AdLoader adLoader;
    private TemplateView small, medium, custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ad);

        initComponents();

    }

    private void initComponents(){

        configAd = new ConfigAd(this);
        builderAd = configAd.nativeAd();

        small = findViewById(R.id.smallTemplate);
        medium = findViewById(R.id.mediumTemplate);
        custom = findViewById(R.id.customTemplate);

        loadNativeAdTemplates();

        setTitle("Anúncio Nativo");

    }

    private void loadNativeAdTemplates(){
        builderAd.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {

                small.setNativeAd(unifiedNativeAd);
                medium.setNativeAd(unifiedNativeAd);
                custom.setNativeAd(unifiedNativeAd);

            }
        }).build();

        adLoader = builderAd.build();
        adLoader.loadAd(configAd.adRequest());
    }


}
