package com.techzohaib.vclock

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform

class MainActivity : AppCompatActivity() {
    private lateinit var mAdView: AdView
    private lateinit var consentInformation: ConsentInformation

    @SuppressLint("MissingInflatedId", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val params = ConsentRequestParameters
            .Builder()
            .build()

        consentInformation = UserMessagingPlatform.getConsentInformation(this )
        consentInformation.requestConsentInfoUpdate(
            this,
            params,
            ConsentInformation.OnConsentInfoUpdateSuccessListener {
                UserMessagingPlatform.loadAndShowConsentFormIfRequired(this ) { loadAndShowError ->
                    if(loadAndShowError != null) {
                        Log.d(TAG, loadAndShowError.message)
                    }
                    if(consentInformation.canRequestAds()) {
                        MobileAds.initialize(this ) {}
                        mAdView.loadAd(AdRequest.Builder().build())
                    }
                }
            },
            ConsentInformation.OnConsentInfoUpdateFailureListener {
                Log.d(TAG, it.message)
            }
        )


        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)



        val myBrowser = findViewById<WebView>(R.id.web)
        val animationView = findViewById<View>(R.id.animationView)

        myBrowser.settings.javaScriptEnabled = true
        myBrowser.webViewClient = WebViewClient()

        val delayMillis = 3000L

        // Post a delayed action on the WebView
        myBrowser.postDelayed({
            // Load a website (replace "https://example.com" with your URL)
            myBrowser.loadUrl("https://blogsyo.com/visaclock/USEmbassyISLPAK")
            animationView.visibility = View.GONE
            myBrowser.visibility = View.VISIBLE

        }, delayMillis)
    }
}