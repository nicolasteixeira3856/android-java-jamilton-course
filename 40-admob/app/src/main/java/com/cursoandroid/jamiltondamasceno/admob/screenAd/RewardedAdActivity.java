package com.cursoandroid.jamiltondamasceno.admob.screenAd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cursoandroid.jamiltondamasceno.admob.R;
import com.cursoandroid.jamiltondamasceno.admob.config.ConfigAd;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class RewardedAdActivity extends AppCompatActivity {

    private Button btnReward;
    private TextView txtPoints;
    private ProgressBar pgbAd;
    private RewardedAd rewardedAd;
    private RewardedAdLoadCallback adLoadCallback;
    private RewardedAdCallback adCallback;
    private ConfigAd configAd;
    private int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_ad);

        initComponents();

    }

    private void initComponents(){

        configAd = new ConfigAd(this);

        btnReward = findViewById(R.id.btnReward);
        btnReward.setVisibility(View.GONE);

        txtPoints = findViewById(R.id.txtPoints);
        pgbAd = findViewById(R.id.pgbAd);
        points = 0;

        rewardedLoadCallback();
        configAd.adLoadCallback(adLoadCallback);
        rewardedAd = configAd.createAndLoadRewardedAd();

        eventRewardedAd();
        events();

        setTitle("Anúncio Premiado");

    }

    private RewardedAdLoadCallback rewardedLoadCallback(){

        adLoadCallback = new RewardedAdLoadCallback(){

            @Override
            public void onRewardedAdLoaded() {

                btnReward.setVisibility(View.VISIBLE);
                pgbAd.setVisibility(View.GONE);
            }
        };

        return adLoadCallback;
    }

    private void eventRewardedAd() {

        if (rewardedAd.isLoaded()) {
            adCallback = new RewardedAdCallback() {

                @Override
                public void onRewardedAdClosed() {

                    rewardedAd = configAd.createAndLoadRewardedAd();
                }

                @Override
                public void onUserEarnedReward(@NonNull RewardItem reward) {

                    points += reward.getAmount();
                    txtPoints.setText("PONTOS: " + points);

                    btnReward.setVisibility(View.GONE);
                    pgbAd.setVisibility(View.VISIBLE);
                }
            };
            rewardedAd.show(RewardedAdActivity.this, adCallback);
        }
    }

    private void events(){

        btnReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventRewardedAd();
            }
        });
    }

}
