package com.savypan.latte.net.rx;

import android.content.Context;

import com.savypan.latte.net.RestClient;
import com.savypan.latte.net.RestCreator;
import com.savypan.latte.net.callback.IError;
import com.savypan.latte.net.callback.IFailure;
import com.savypan.latte.net.callback.IRequest;
import com.savypan.latte.net.callback.ISuccess;
import com.savypan.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by savypan on 2018/11/8.
 */

public class RxRestClientBuilder {

    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();

    private RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    private File mFile = null;

    RxRestClientBuilder() {
    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String path) {
        mFile = new File(path);
        return this;
    }

    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), raw);
        return this;
    }

    public final RxRestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClient build(){
        return new RxRestClient(mUrl,
                PARAMS,
                mBody,
                mLoaderStyle,
                mContext,
                mFile);
    }
}
