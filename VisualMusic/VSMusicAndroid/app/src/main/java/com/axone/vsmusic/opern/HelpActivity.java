package com.axone.vsmusic.opern;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.axone.vsmusic.R;

/** @class HelpActivity
 *  The HelpActivity displays the help.html file in the assets directory.
 */
public class HelpActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opern_help);
        WebView view = (WebView) findViewById(R.id.help_webview);
        view.getSettings().setJavaScriptEnabled(false);
        view.loadUrl("file:///android_asset/help.html");
    }
}

