package com.savypan.festec;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.savypan.latte.app.Latte;
import com.savypan.latte.net.interceptor.DebugInterceptor;
import com.savypan.latteec.database.DatabaseManager;
import com.savypan.latteec.icon.FontECModule;

/**
 * Created by savypan on 2018/11/7.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontECModule())
                .withAPIHost("http://mock.fulingjie.com/mock/api/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withWechatAppId("")
                .withWechatAppSec("")
//                .withContext(this)
                .withHandler()
                .configure();

        DatabaseManager.getInstance().init(this);
        //Iconify.with(new FontAwesomeModule());
        initStetho();
    }

    private void initStetho() {
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
            .build()
        );
    }
}
