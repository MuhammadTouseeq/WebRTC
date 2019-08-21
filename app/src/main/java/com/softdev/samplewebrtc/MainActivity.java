package com.softdev.samplewebrtc;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    private WebView mWebView;
    private String url = "https://appr.tc/r/658450307";
    private Button goOnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        goOnButton = (Button) findViewById(R.id.gotoButton);
        mWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            webSettings.setSafeBrowsingEnabled(true);
        }

        goOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.getSettings().setLoadsImagesAutomatically(true);
                mWebView.getSettings().setLoadWithOverviewMode(true);
                mWebView.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
                mWebView.setScrollbarFadingEnabled(true);
                mWebView.getSettings().setAllowFileAccess(true);
                mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                mWebView.getSettings().setDomStorageEnabled(true);
                mWebView.getSettings().setAppCacheEnabled(true);
                mWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
                //carichiamo finalmete la url
                mWebView.setWebChromeClient(new WebChromeClient(){
                    // Need to accept permissions to use the camera
                    @Override
                    public void onPermissionRequest(final PermissionRequest request) {
                       runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    request.grant(request.getResources());
                                }
                            }
                        });
                    }
                });
                mWebView.loadUrl(url);
            }
        });


    }
}
