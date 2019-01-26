package com.savypan.latte.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.savypan.latte.delegates.IPageLoadListener;
import com.savypan.latte.delegates.web.chromeclient.WebChromeClientImpl;
import com.savypan.latte.delegates.web.client.WebViewClientImpl;
import com.savypan.latte.delegates.web.route.RouteKey;
import com.savypan.latte.delegates.web.route.Router;

public class WebDelegateImpl extends WebDelegate implements IWebViewInitializer{

    private IPageLoadListener mIPageLoadListener = null;

    public void setIPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKey.URL.name(), url);

        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);

        return delegate;
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl() != null) {
            //用原生的方式摸你Web跳转并进行页面加载
            Router.getInstance().loadPage(this, getUrl());

        }
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setIPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
