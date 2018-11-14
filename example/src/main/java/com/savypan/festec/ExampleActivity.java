package com.savypan.festec;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.savypan.latte.activities.ProxyActivity;
import com.savypan.latte.app.Latte;
import com.savypan.latte.delegates.LatteDelegate;
import com.savypan.latteec.launcher.LauncherDelegate;
import com.savypan.latteec.launcher.LauncherScrollDelegate;
import com.savypan.latteec.sign.SigninDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        //return new ExampleDelegate();
        //return new LauncherDelegate();
        //return new LauncherScrollDelegate();
        return new SigninDelegate();
    }
}
