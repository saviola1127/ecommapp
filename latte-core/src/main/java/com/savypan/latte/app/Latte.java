package com.savypan.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by savypan on 2018/11/7.
 */

public final class Latte {

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplication() {
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT);
    }
}
