package com.savypan.latteec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.savypan.latte.delegates.LatteDelegate;
import com.savypan.latte.net.RestClient;
import com.savypan.latte.net.callback.ISuccess;
import com.savypan.latte.util.log.LatteLogger;
import com.savypan.latteec.R;
import com.savypan.latteec.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpDelegate extends LatteDelegate {

    @BindView(R2.id.edit_signup_name)
    TextInputEditText mName = null;

    @BindView(R2.id.edit_signup_email)
    TextInputEditText mEmail = null;

    @BindView(R2.id.edit_signup_phone)
    TextInputEditText mPhone = null;

    @BindView(R2.id.edit_signup_password)
    TextInputEditText mPassword = null;

    @BindView(R2.id.edit_signup_repwd)
    TextInputEditText mRepassword = null;

//    @BindView(R2.id.tv_link_signin)
//    TextInputEditText mName = null;

    @OnClick(R2.id.btn_signup)
    void onClickSignup() {
        if (checkForm()) {
            Log.e("SAVY", "checkForm passed");
            RestClient.builder()
                .url("http://mock.fulingjie.com/mock/data/user_profile.json")
//                .params("name", mName.getText().toString())
//                .params("email", mEmail.getText().toString())
//                .params("phone", mPhone.getText().toString())
//                .params("password", mPassword.getText().toString())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        LatteLogger.json("USER_PROFILE", response);
                        Log.e("SAVY", "response is " + response);
                        SignHandler.onSignup(response, mISignListener);
                    }
                })
                .build()
                .post();
            //Toast.makeText(getContext(), "验证通过", Toast.LENGTH_LONG).show();
        } else {
            Log.e("SAVY", "checkForm not passed");
        }
    }


    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.tv_link_signin)
    void onClickSignin() {
        start(new SigninDelegate());
    }

    private boolean checkForm() {
        final String name = mName.getText().toString().trim();
        final String email = mEmail.getText().toString().trim();
        final String phone = mPhone.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        final String repwd = mRepassword.getText().toString().trim();

        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (repwd.isEmpty() || repwd.length() < 6 || !(repwd.equals(password))) {
            mRepassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRepassword.setError(null);
        }

        return isPass;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_signup;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {

    }
}
