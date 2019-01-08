package com.savypan.latteec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.savypan.latte.delegates.LatteDelegate;
import com.savypan.latte.delegates.buttom.BottomItemDelegate;
import com.savypan.latteec.R;
import com.savypan.latteec.main.sort.content.ContentDelegate;
import com.savypan.latteec.main.sort.list.VerticalListDelegate;

public class SortDelegate extends BottomItemDelegate {


	@Override
	public Object setLayout() {
		return R.layout.delegate_sort;
	}

	@Override
	public void onBindView(Bundle savedInstanceState, View rootView) {

	}

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {
		super.onLazyInitView(savedInstanceState);
		final VerticalListDelegate listDelegate = new VerticalListDelegate();
		loadRootFragment(R.id.vertical_list_container, listDelegate);
		replaceLoadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1), false);

	}
}
