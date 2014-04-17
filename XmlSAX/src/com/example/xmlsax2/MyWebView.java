package com.example.xmlsax2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mywebview);
		
		Bundle bundle = MyWebView.this.getIntent().getExtras();
		
		String myURL = bundle.getString("Website");
		
        WebView myBrowser=(WebView)findViewById(R.id.webview1);  
        
        WebSettings websettings = myBrowser.getSettings();
        websettings.setSupportZoom(true);
        websettings.setBuiltInZoomControls(true);
        websettings.setJavaScriptEnabled(true);
        myBrowser.setWebViewClient(new WebViewClient()); 
        myBrowser.loadUrl(myURL);
	}
}