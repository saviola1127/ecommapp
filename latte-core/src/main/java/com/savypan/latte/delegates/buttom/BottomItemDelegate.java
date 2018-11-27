package com.savypan.latte.delegates.buttom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;
import com.savypan.latte.R;
import com.savypan.latte.delegates.LatteDelegate;


public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {

	private long mExitTime = 0;
	private static final int EXIT_TIME = 2000;

	@Override
	public void onResume() {
		super.onResume();

		//re-request touch focus when fragment is re-created
		final View rootView = getView();
		if (rootView != null) {
			rootView.setFocusableInTouchMode(true);
			rootView.requestFocus();
			rootView.setOnKeyListener(this);
		}
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
			&& event.getKeyCode() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - mExitTime) > EXIT_TIME) {
				Toast.makeText(getContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_LONG).show();
				mExitTime = System.currentTimeMillis();
			} else {
				_mActivity.finish();

				if (mExitTime != 0) {
					mExitTime = 0;
				}

			}
			return true;
		}

		return false;
	}
}
