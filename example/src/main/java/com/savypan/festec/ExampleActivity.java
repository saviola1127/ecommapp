package com.savypan.festec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.savypan.latte.activities.ProxyActivity;
import com.savypan.latte.app.Latte;
import com.savypan.latte.delegates.LatteDelegate;
import com.savypan.latteec.launcher.LauncherDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        //return new ExampleDelegate();
        return new LauncherDelegate();
    }
}
