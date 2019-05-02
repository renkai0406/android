package com.axone.vsmusic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.axone.vsmusic.R;
import com.axone.vsmusic.task.MatchMusicTask;

public class WebMusicActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;
    private TextView webLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_music);

        webView = (WebView) findViewById(R.id.web_view);
        webView.setVisibility(View.GONE);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        WebSettings wSet = webView.getSettings();
        wSet.setJavaScriptEnabled(true);
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        progressBar = (ProgressBar) findViewById(R.id.web_progress);
        webLabel = (TextView) findViewById(R.id.web_label);

        Intent intent = getIntent();

        boolean load = intent.getBooleanExtra("load", true);
        String name = intent.getStringExtra("name");
        String location = intent.getStringExtra("location");

        if(load){
            MatchMusicTask matchMusicTask = new MatchMusicTask(this);
            matchMusicTask.execute(name, null, location);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }
}
