package com.savypan.latte.delegates.web.client;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.savypan.latte.app.ConfigKeys;
import com.savypan.latte.app.Latte;
import com.savypan.latte.delegates.IPageLoadListener;
import com.savypan.latte.delegates.web.WebDelegate;
import com.savypan.latte.delegates.web.route.Router;
import com.savypan.latte.ui.LatteLoader;
import com.savypan.latte.util.log.LatteLogger;
import com.savypan.latte.util.storage.LattePreference;

import java.util.logging.Handler;

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final android.os.Handler HANDLER = Latte.getHandler();

    public void setIPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

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

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    //获取浏览器cookie并且开启同步
    private void syncCookie() {
        final CookieManager manager = CookieManager.getInstance();
        /**
         * 注意这里的cookie和API请求的cookie是不一样的
         * 这个在网页中不可见
         */
        final String webHost = Latte.getConfiguration(ConfigKeys.WEB_HOST);
        if (webHost != null) {

            if (manager.hasCookies()) {
                final String cookie = manager.getCookie(webHost);
                if (!TextUtils.isEmpty(cookie)) {
                    LattePreference.addCustomAppProfile("cookie", cookie);
                }
            }
        }

    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        syncCookie();

        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        }, 1000);
    }
}
