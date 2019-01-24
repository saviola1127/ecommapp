package com.savypan.latteec.main;

import android.graphics.Color;
import com.savypan.latte.delegates.buttom.BaseBottomDelegate;
import com.savypan.latte.delegates.buttom.BottomItemDelegate;
import com.savypan.latte.delegates.buttom.BottomTabBean;
import com.savypan.latte.delegates.buttom.ItemBuilder;
import com.savypan.latteec.main.discovery.DiscoveryDelegate;
import com.savypan.latteec.main.index.IndexDelegate;
import com.savypan.latteec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

public class EcBottomDelegate extends BaseBottomDelegate {
	@Override
	public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
		final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
		items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
		items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
		items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoveryDelegate());
		items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
		items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
		return builder.addItems(items).build();
	}

	@Override
	public int setIndexDelegate() {
		return 0;
	}

	@Override
	public int setClickedColor() {
		return Color.parseColor("#ffff8800");
	}
}
