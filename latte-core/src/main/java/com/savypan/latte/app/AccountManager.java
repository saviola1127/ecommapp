package com.savypan.latte.app;

import com.savypan.latte.util.storage.LattePreference;

public class AccountManager {

	private enum SignTag {
		SIGN_TAG
	}

	/**
	 * 保存用户登录状态
	 * @param state
	 */
	public static void setSignState(boolean state) {
		LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
	}

	private static boolean isSignin() {
		return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
	}

	public static void checkAccount(IUserChecker checker) {
		if (isSignin()) {
			checker.onSignin();
		} else {
			checker.onNotSignin();
		}
	}
}
