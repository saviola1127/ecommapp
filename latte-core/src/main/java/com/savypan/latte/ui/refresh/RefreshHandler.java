package com.savypan.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.savypan.latte.app.Latte;
import com.savypan.latte.net.RestClient;
import com.savypan.latte.net.callback.ISuccess;
import com.savypan.latte.ui.recycler.DataConvertor;
import com.savypan.latte.ui.recycler.MultipleRecyclerAdapter;

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener
, BaseQuickAdapter.RequestLoadMoreListener{

	private final SwipeRefreshLayout REFRESH_LAYOUT;
	private final PagingBean BEAN;
	private final RecyclerView RECYCLERVIEW;
	private MultipleRecyclerAdapter mAdapter = null;
	private final DataConvertor CONVERTOR;

	private RefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
						  RecyclerView recyclerView,
						  DataConvertor convertor,
						  PagingBean bean) {
		this.REFRESH_LAYOUT = swipeRefreshLayout;
		this.RECYCLERVIEW = recyclerView;
		this.CONVERTOR = convertor;
		this.BEAN = bean;
		REFRESH_LAYOUT.setOnRefreshListener(this);
	}

	public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
										RecyclerView recyclerView,
										DataConvertor convertor) {
		return new RefreshHandler(swipeRefreshLayout, recyclerView, convertor, new PagingBean());
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
		BEAN.setDelayed(1000);
		RestClient.builder()
			.url(url)
			.success(new ISuccess() {
				@Override
				public void onSuccess(String response) {
					//Toast.makeText(Latte.getApplication(), response, Toast.LENGTH_LONG).show();
					final JSONObject object = JSON.parseObject(response);
					BEAN.setTotal(object.getInteger("total"))
							.setPageSize(object.getInteger("page_size"));
					mAdapter = MultipleRecyclerAdapter.create(CONVERTOR.setJsonData(response));
					mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
					RECYCLERVIEW.setAdapter(mAdapter);

					BEAN.addIndex();
				}
			})
			.build()
			.get();
	}

	@Override
	public void onLoadMoreRequested() {

	}
}
