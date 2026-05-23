package com.fitlife.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView web;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        web = new WebView(this);

        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);          // app logic
        s.setDomStorageEnabled(true);          // localStorage (data persistence)
        s.setDatabaseEnabled(true);
        s.setMediaPlaybackRequiresUserGesture(false); // allow timer beep sounds
        s.setLoadWithOverviewMode(true);
        s.setUseWideViewPort(false);

        // Keep navigation inside the WebView
        web.setWebViewClient(new WebViewClient());
        // Enables alert()/confirm() dialogs used by the app
        web.setWebChromeClient(new WebChromeClient());

        web.loadUrl("file:///android_asset/index.html");
        setContentView(web);
    }

    // Use the in-app screens for the hardware back button
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && web != null && web.canGoBack()) {
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (web != null) web.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (web != null) web.onResume();
    }
}
