package com.pushpal.jokinggraphically;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class JokeActivity extends AppCompatActivity {

    private final String TAG = JokeActivity.class.getSimpleName();
    TextView jokeDisplay;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_joke);

        jokeDisplay = findViewById(R.id.tv_joke);

        startInterstitialAd();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String joke = extras.getString("joke", "Hey it's a bad joke!");
            jokeDisplay.setText(joke);
        }
    }

    private void startInterstitialAd() {
        MobileAds.initialize(this, getResources().getString(R.string.ad_mob_app_id));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.d(TAG, "The interstitial wasn't loaded yet. Error: " + errorCode);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                // Load the next interstitial.
                // mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }
}
