package com.savypan.latteec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.savypan.latte.delegates.buttom.BottomItemDelegate;
import com.savypan.latte.net.RestClient;
import com.savypan.latte.net.callback.ISuccess;
import com.savypan.latte.ui.recycler.BaseDecoration;
import com.savypan.latte.ui.recycler.MultipleFields;
import com.savypan.latte.ui.recycler.MultipleItemEntity;
import com.savypan.latte.ui.refresh.RefreshHandler;
import com.savypan.latteec.R;
import com.savypan.latteec.R2;
import com.savypan.latteec.main.EcBottomDelegate;

import java.util.ArrayList;

import butterknife.BindView;

public class IndexDelegate extends BottomItemDelegate {

	@BindView(R2.id.rv_index)
	RecyclerView mRecyclerView = null;

	@BindView(R2.id.srl_index)
	SwipeRefreshLayout mRefreshLayout = null;

	@BindView(R2.id.tb_index)
	Toolbar mToolbar = null;

	@BindView(R2.id.icon_index_scan)
	IconTextView mIconScan = null;

	@BindView(R2.id.et_search_view)
	AppCompatEditText mSearchView = null;


	private RefreshHandler mRefreshHandler = null;

	private void initRefreshLayout() {
		mRefreshLayout.setColorSchemeResources(
			android.R.color.holo_blue_bright,
			android.R.color.holo_orange_light,
			android.R.color.holo_red_light
			);

		mRefreshLayout.setProgressViewOffset(true, 120, 300);
	}

	private void initRecyclerView() {
		final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
		mRecyclerView.setLayoutManager(manager);
		mRecyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));

		final EcBottomDelegate ecBottomDelegate = getParentDelegate();
		mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
	}

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {
		super.onLazyInitView(savedInstanceState);
		initRefreshLayout();
		initRecyclerView();
		mRefreshHandler.firstPage("http://mock.fulingjie.com/mock/api/index.php");
	}

	@Override
	public Object setLayout() {
		return R.layout.delegate_index;
	}

	@Override
	public void onBindView(Bundle savedInstanceState, View rootView) {
		//mRefreshHandler = new RefreshHandler(mRefreshLayout);
		mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConvertor());
//		RestClient.builder()
//				.url("http://mock.fulingjie.com/mock/api/index.php")
//				.success(new ISuccess() {
//					@Override
//					public void onSuccess(String response) {
//
//						Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
//
//						final IndexDataConvertor convertor = new IndexDataConvertor();
//						convertor.setJsonData(response);
//						ArrayList<MultipleItemEntity> list = convertor.convert();
//						if (list != null) {
//							final String imageUrl = list.get(1).getField(MultipleFields.IMAGE_URL);
//							Toast.makeText(getContext(), imageUrl, Toast.LENGTH_LONG).show();
//						}
//					}
//				})
//				.build()
//				.get();
	}
}
