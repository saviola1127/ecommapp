package com.savypan.latte.wechat.template;

import com.savypan.latte.wechat.BaseWXEntryActivity;
import com.savypan.latte.wechat.LatteWechat;

public class WXEntryTemplate extends BaseWXEntryActivity {

	@Override
	protected void onResume() {
		super.onResume();
		finish();
		overridePendingTransition(0, 0);
	}

	@Override
	public void onSignInSuccess(String userInfo) {
		LatteWechat.getInstance().getSigninCallback().onSignInSuccess(userInfo);
	}
}
