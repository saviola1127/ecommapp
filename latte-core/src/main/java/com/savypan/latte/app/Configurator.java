package com.savypan.latte.app;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.savypan.latte.delegates.web.event.Event;
import com.savypan.latte.delegates.web.event.EventManager;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by savypan on 2018/11/7.
 */

public final class Configurator {

    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();

    private Configurator(){
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);
        //Log.e("SAVY", "handler => " + HANDLER.toString());
        LATTE_CONFIGS.put(ConfigKeys.HANDLER.name(), HANDLER);
    }

    private void initIcons() {
        //Log.d("DEBUG", "init Icons");
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer iconifyInitializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                iconifyInitializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withContext(Context context) {
        LATTE_CONFIGS.put(ConfigKeys.APPLICATION_CONTEXT, context);
        return this;
    }

    public final Configurator withHandler() {
        LATTE_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
        return this;
    }


    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withWechatAppId(String appId) {
        LATTE_CONFIGS.put(ConfigKeys.WECHAT_APPID, appId);
        return this;
    }

    public final Configurator withWechatAppSec(String appSec) {
        LATTE_CONFIGS.put(ConfigKeys.WECHAT_APPSEC, appSec);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    public Configurator withJavascriptInterface(@NonNull String name) {
        LATTE_CONFIGS.put(ConfigKeys.JAVASCRIPT_INT, name);
        return this;
    }

    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }

    public Configurator withWebHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.WEB_HOST, host);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final void configure() {
        initIcons();
        //LATTE_CONFIGS.put(ConfigKeys.HANDLER.name(), HANDLER);
        Logger.addLogAdapter(new AndroidLogAdapter());
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public final Configurator withAPIHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST.name(), host);
        return this;
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }
}
