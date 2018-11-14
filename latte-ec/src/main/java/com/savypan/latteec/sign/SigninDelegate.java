package com.savypan.latteec.sign;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;

import com.savypan.latte.delegates.LatteDelegate;
import com.savypan.latteec.R;
import com.savypan.latteec.R2;

public class SigninDelegate extends LatteDelegate {

    @BindView(R2.id.edit_signin_email)
    TextInputEditText mEmail = null;

    @BindView(R2.id.edit_signin_password)
    TextInputEditText mPassword = null;

    @OnClick(R2.id.btn_signin)
    void onClickSignin() {
        if (checkForm()) {
//            RestClient.builder()
//                    .url("sign_up")
//                    .params("", "")
//                    .success(new ISuccess() {
//                        @Override
//                        public void onSuccess(String response) {
//
//                        }
//                    })
//                    .build()
//                    .post();
            Toast.makeText(getContext(), "登录成功", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R2.id.icon_signin_wechat)
    void onClickWechat() {

    }

    @OnClick(R2.id.tv_link_signup)
    void onClickLinkSignup() {
        start(new SignUpDelegate());
    }

    private boolean checkForm() {
        //final String name = mName.getText().toString().trim();
        final String email = mEmail.getText().toString().trim();
        //final String phone = mPhone.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        //final String repwd = mRepassword.getText().toString().trim();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_signin;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {

    }
}
