package com.example.guessgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Start : AppCompatActivity() {
    //variable that holds the adds to be displayed
    private lateinit var mAdView: AdView
    private lateinit var mInterstitialAd: InterstitialAd


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val Start = findViewById<Button>(R.id.btn1)
        //initializing the ad banner by finding it by id
        mAdView = findViewById(R.id.adView)
        initializeAds()
        loadBannerAd()
        loadInterstitialAd()


        Start.setOnClickListener{
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }

    }
    //Load the three functions
    //initializing(starting the ad) first function
    private fun initializeAds() {
        MobileAds.initialize(this) { }

    }

    //Load the banner
    private fun loadBannerAd() {
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    //load the interstitial add
    private fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("inter", adError.message)
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
                mInterstitialAd.show(this@Start)

            }
        })
    }
}