package com.anioncode.witcher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private HorizontalScrollView webInitialization;
    private WitcherAdapterSound mAdapter;
    private FloatingActionButton titleText;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        setContentView(R.layout.activity_main);
        Window w = getWindow();
        MobileAds.initialize(this, "ca-app-pub-3788232558823244~4608224389");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView =  findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

//        AdView adView = new AdView(this);
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId("ca-app-pub-3788232558823244/4626845185");


        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        recyclerView = findViewById(R.id.item_sound);
        titleText = findViewById(R.id.fabbtn);
        webInitialization = findViewById(R.id.horizontalScrollView1);
        titleText.setOnClickListener((View v) -> {

            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.webdialog);

            WebView webView = (WebView) dialog.findViewById(R.id.WebView);
            webView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSeqLz1la-ruS30qZXyLBcb2_wpAZsaoq8CdxFZa9RrI4PHhww/viewform?usp=sf_link");
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setSupportZoom(true);
            webSettings.setBuiltInZoomControls(true);
            webSettings.setDisplayZoomControls(false);
            webSettings.setTextZoom(80);

            webView.setWebViewClient(new WebViewClient());

            dialog.setCancelable(true);

            dialog.show();

        });
        ArrayList<ModelWitcher> myDataset = new ArrayList<>();
        myDataset.add(new ModelWitcher("Poland", "raw1"));
        myDataset.add(new ModelWitcher("german", "raw2"));
        myDataset.add(new ModelWitcher("English", "raw3"));
        myDataset.add(new ModelWitcher("Spanish", "raw4"));
        myDataset.add(new ModelWitcher("Russian", "raw5"));
        myDataset.add(new ModelWitcher("Japan", "raw6"));
        myDataset.add(new ModelWitcher("France", "raw7"));


        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//
//        // specify an adapter (see also next example)
        mAdapter = new WitcherAdapterSound(myDataset, this);
        recyclerView.setAdapter(mAdapter);
    }
}
