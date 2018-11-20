package com.savypan.latteec.sign;

import android.accounts.AccountManager;
import android.database.sqlite.SQLiteConstraintException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.savypan.latteec.database.DatabaseManager;
import com.savypan.latteec.database.UserProfile;

import java.util.Random;

public class SignHandler {

	public static void onSignup(String response, ISignListener signListener) {
		final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");

		final long userId = profileJson.getLong("userId") + new Random().nextInt(100) + 1;
		final String name = profileJson.getString("name");
		final String avatar = profileJson.getString("avatar");
		final String gender = profileJson.getString("gender");
		final String address = profileJson.getString("address");

		final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
		try {
			DatabaseManager.getInstance().getDao().insert(profile);
		} catch (SQLiteConstraintException e) {
			e.printStackTrace();
		}

//		//已经注册并登录成功了
//		AccountManager.setSignState(true);
//		signListener.onSignInSuccess();
		com.savypan.latte.app.AccountManager.setSignState(true);
		signListener.onSignUpSuccess();
	}

	public static void onSignIn(String response, ISignListener signListener) {
		final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
		final long userId = profileJson.getLong("userId") + new Random().nextInt(100) + 1;
		final String name = profileJson.getString("name");
		final String avatar = profileJson.getString("avatar");
		final String gender = profileJson.getString("gender");
		final String address = profileJson.getString("address");

		final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
		DatabaseManager.getInstance().getDao().insert(profile);

		//已经注册并登录成功了
		com.savypan.latte.app.AccountManager.setSignState(true);
		signListener.onSignInSuccess();
	}
}
