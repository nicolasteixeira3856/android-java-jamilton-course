package com.cursoandroid.jamiltondamasceno.admob.config;

import android.content.Context;
import android.content.Intent;

import com.cursoandroid.jamiltondamasceno.admob.R;
import com.cursoandroid.jamiltondamasceno.admob.screenAd.NativeAdActivity;
import com.cursoandroid.jamiltondamasceno.admob.screenAd.RewardedAdActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class ConfigAd {

    final Context context;
    private AdRequest adRequest;
    private InterstitialAd interstitialAd;
    private RewardedAd rewardedAd;
    private AdLoader adLoader;
    private RewardedAdLoadCallback adLoadCallback;

    public ConfigAd(final Context context) {
        this.context = context;

        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
    }
/*------------------------------------------------------------------------------------------------------------------------*/

    public AdRequest adRequest(){

        return adRequest = new AdRequest.Builder().build();
    }

/*------------------------------------------------------------------------------------------------------------------------*/

    public InterstitialAd createInterstitialAd(){

        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(context.getString(R.string.interstitial_ad_test_id));
        interstitialAd.loadAd(adRequest());

        return interstitialAd;
    }

    public void eventInterstitialAd(final int screen){

        if (interstitialAd.isLoaded()){
            interstitialAd.show();
        }

        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {

                createInterstitialAd();

                if (screen == 0){
                    context.startActivity(new Intent(context, RewardedAdActivity.class));
                }else {
                    context.startActivity(new Intent(context, NativeAdActivity.class));
                }

            }
        });
    }

/*------------------------------------------------------------------------------------------------------------------------*/

    public void adLoadCallback(RewardedAdLoadCallback adLoadCallback){
            this.adLoadCallback = adLoadCallback;
    }

    public RewardedAd createAndLoadRewardedAd(){

        rewardedAd = new RewardedAd(context, context.getString(R.string.reward_ad_test_id));
        rewardedAd.loadAd(adRequest(), adLoadCallback);

        return rewardedAd;

    }

/*------------------------------------------------------------------------------------------------------------------------*/

    public AdLoader.Builder nativeAd(){

        AdLoader.Builder builder = new AdLoader.Builder(context, context.getString(R.string.native_ad_test_id));

        return builder;
    }

}
