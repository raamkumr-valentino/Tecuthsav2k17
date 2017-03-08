package com.edu.tce.tecuthsav17.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.edu.tce.tecuthsav17.R;

import static com.edu.tce.tecuthsav17.constants.constant.LOADING;
import static com.edu.tce.tecuthsav17.constants.constant.REGISTRATION;
import static com.edu.tce.tecuthsav17.constants.constant.passURL;

public class Register_Screen extends AppCompatActivity
{
    private WebView registerScreen;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__screen);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(toolbar !=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(REGISTRATION);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
        String registration_url = getIntent().getStringExtra(passURL);
        registerScreen = (WebView) findViewById(R.id.event_register_form);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(LOADING);
        progressDialog.setCancelable(false);
        progressDialog.show();
        initWebView();
        registerScreen.loadUrl(registration_url);
    }

    private void initWebView()
    {
        registerScreen.getSettings().setJavaScriptEnabled(true);
        registerScreen.setWebChromeClient(new MyWebChromeClient(this));
        registerScreen.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                registerScreen.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.dismiss();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });
    }

    private class MyWebChromeClient extends WebChromeClient {
        Context context;
        MyWebChromeClient(Context context) {
            super();
            this.context = context;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
