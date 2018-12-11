package com.savypan.festec;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.savypan.latte.activities.ProxyActivity;
import com.savypan.latte.app.Latte;
import com.savypan.latte.delegates.LatteDelegate;
import com.savypan.latte.ui.launcher.ILauncherListener;
import com.savypan.latte.ui.launcher.OnLauncherFinish;
import com.savypan.latteec.launcher.LauncherDelegate;
import com.savypan.latteec.launcher.LauncherScrollDelegate;
import com.savypan.latteec.main.EcBottomDelegate;
import com.savypan.latteec.sign.ISignListener;
import com.savypan.latteec.sign.SignUpDelegate;
import com.savypan.latteec.sign.SigninDelegate;

public class ExampleActivity extends ProxyActivity implements
    ISignListener, ILauncherListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Latte.getConfigurator().withActivity(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        //return new ExampleDelegate();
        return new LauncherDelegate();
        //return new LauncherScrollDelegate();
        //return new SignUpDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinish tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户已登录", Toast.LENGTH_LONG).show();
                startWithPop(new EcBottomDelegate());
                break;

            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                startWithPop(new SigninDelegate());
                break;


                default:
                    break;
        }
    }
}
