package com.savypan.festec;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.savypan.latte.app.Latte;
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
                .withAPIHost("http://127.0.0.1/")
                .configure();

        //Iconify.with(new FontAwesomeModule());
    }
}
