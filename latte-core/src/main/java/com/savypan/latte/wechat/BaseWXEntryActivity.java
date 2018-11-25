package com.savypan.latte.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.savypan.latte.net.RestClient;
import com.savypan.latte.net.callback.IError;
import com.savypan.latte.net.callback.IFailure;
import com.savypan.latte.net.callback.ISuccess;
import com.savypan.latte.util.log.LatteLogger;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

public abstract class BaseWXEntryActivity extends BaseWXActivity {

	public abstract void onSignInSuccess(String userInfo);

	@Override
	public void onResp(BaseResp baseResp) {
		final String code = ((SendAuth.Resp)baseResp).code;
		final StringBuilder authUrl = new StringBuilder();
		authUrl
			.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
			.append(LatteWechat.APP_ID)
			.append("&secret=")
			.append(LatteWechat.APP_SEC)
			.append("&code=")
			.append(code)
			.append("&grant_type=authorization_code");

		LatteLogger.d("authUrl", authUrl.toString());
		getAuth(authUrl.toString());
	}

	@Override
	public void onReq(BaseReq baseReq) {

	}

	private void getAuth(String authUrl) {
		RestClient
			.builder()
			.url(authUrl)
			.success(new ISuccess() {
				@Override
				public void onSuccess(String response) {

					final JSONObject authObj = JSON.parseObject(response);
					final String accessToken = authObj.getString("access_token");
					final String openId = authObj.getString("openid");

					final StringBuilder userInfoUrl = new StringBuilder();
					userInfoUrl
						.append("https://api.weixin.qq.com/sns/userinfo?access_token=")
						.append(accessToken)
						.append("&openid=")
						.append(openId)
						.append("&lang=")
						.append("zh_CN");

					LatteLogger.d("userInfoUrl", userInfoUrl.toString());
					getUserInfo(userInfoUrl.toString());

				}
			})
			.build()
			.get();
	}

	private void getUserInfo(String userInfoUrl) {
		RestClient
			.builder()
			.url(userInfoUrl)
			.success(new ISuccess() {
				@Override
				public void onSuccess(String response) {
					onSignInSuccess(response);
				}
			})
			.failure(new IFailure() {
				@Override
				public void onFailure() {

				}
			})
			.error(new IError() {
				@Override
				public void onError(int code, String msg) {

				}
			})
			.build()
			.get();
	}
}
