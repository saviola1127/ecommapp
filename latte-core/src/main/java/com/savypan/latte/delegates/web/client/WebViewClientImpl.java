package com.savypan.latte.delegates.web.client;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.savypan.latte.delegates.web.WebDelegate;
import com.savypan.latte.delegates.web.route.Router;
import com.savypan.latte.util.log.LatteLogger;

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate) {
        DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);
        //return super.shouldOverrideUrlLoading(view, url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }
}
