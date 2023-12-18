package com.techzohaib.vclock

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {
    lateinit var mAdView : AdView
    @SuppressLint("MissingInflatedId", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()

        val delay = 3000L

        mAdView.postDelayed({
            mAdView.loadAd(adRequest)
        }, delay)

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