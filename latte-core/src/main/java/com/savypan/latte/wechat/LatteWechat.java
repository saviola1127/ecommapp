package com.savypan.latte.wechat;

import android.app.Activity;
import com.savypan.latte.app.ConfigKeys;
import com.savypan.latte.app.Latte;
import com.savypan.latte.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class LatteWechat {
	public static final String APP_ID = Latte.getConfiguration(ConfigKeys.WECHAT_APPID);
	public static final String APP_SEC = Latte.getConfiguration(ConfigKeys.WECHAT_APPSEC);

	private final IWXAPI WXAPI;
	private IWeChatSignInCallback mSigninCallback = null;

	private static final class Holder {
		private static final LatteWechat INSTANCE = new LatteWechat();
	}

	public static LatteWechat getInstance() {
		return Holder.INSTANCE;
	}

	private LatteWechat() {
		final Activity activity = Latte.getConfiguration(ConfigKeys.ACTIVITY);
		WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
		WXAPI.registerApp(APP_SEC);
	}

	public final IWXAPI getWXAPI() {
		return WXAPI;
	}

	public final void signIn() {
		final SendAuth.Req req = new SendAuth.Req();
		req.scope = "snsapi_userinfo";
		req.scope = "random_state";
		WXAPI.sendReq(req);
	}

	public IWeChatSignInCallback getSigninCallback() {
		return mSigninCallback;
	}

	public LatteWechat onSigninSucess(IWeChatSignInCallback callback) {
		this.mSigninCallback = callback;
		return this;
	}
}
