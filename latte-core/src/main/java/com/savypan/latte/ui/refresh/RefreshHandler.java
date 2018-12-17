package com.savypan.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;
import com.savypan.latte.app.Latte;
import com.savypan.latte.net.RestClient;
import com.savypan.latte.net.callback.ISuccess;

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

	private final SwipeRefreshLayout REFRESH_LAYOUT;

	public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout) {
		REFRESH_LAYOUT = swipeRefreshLayout;
		REFRESH_LAYOUT.setOnRefreshListener(this);
	}

	@Override
	public void onRefresh() {
		refresh();
	}

	private void refresh() {
		REFRESH_LAYOUT.setRefreshing(true);

		Latte.getHandler().postDelayed(new Runnable() {
			@Override
			public void run() {
				REFRESH_LAYOUT.setRefreshing(false);
			}
		}, 2000);
	}

	public void firstPage(String url) {
		RestClient.builder()
			.url(url)
			.success(new ISuccess() {
				@Override
				public void onSuccess(String response) {
					Toast.makeText(Latte.getApplication(), response, Toast.LENGTH_LONG).show();
				}
			})
			.build()
			.get();
	}
}
