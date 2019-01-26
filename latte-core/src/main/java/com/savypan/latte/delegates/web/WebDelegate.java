package com.savypan.latte.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.savypan.latte.app.ConfigKeys;
import com.savypan.latte.app.Latte;
import com.savypan.latte.delegates.LatteDelegate;
import com.savypan.latte.delegates.web.route.RouteKey;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public abstract class WebDelegate extends LatteDelegate {

    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAvailable = false;
    private LatteDelegate mTopDelegate = null;

    public WebDelegate() {

    }

    public abstract IWebViewInitializer setInitializer();

    public String getUrl() {
        if (mUrl == null) {
            throw new NullPointerException("WebView is NULL!");
        }
        return mUrl;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle args = getArguments();
        mUrl = args.getString(RouteKey.URL.name());
        initWebView();
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);

                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());

                final String name = Latte.getConfiguration(ConfigKeys.JAVASCRIPT_INT);
                mWebView.addJavascriptInterface(LatteWebInterface.create(this), name);
                mIsWebViewAvailable = true;
            } else {
                throw new NullPointerException("Initializer is NULL!");
            }
        }
    }


    public void setTopDelegate(LatteDelegate delegate) {
        mTopDelegate = delegate;
    }


    public LatteDelegate getTopDelegate() {
        if (mTopDelegate == null) {
            mTopDelegate = this;
        }
        return mTopDelegate;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }

    public WebView getWebView() {
        if (mWebView != null) {
            return mIsWebViewAvailable ? mWebView : null;
        } else {
          throw new NullPointerException("WebView is NULL!");
        }
    }
}
