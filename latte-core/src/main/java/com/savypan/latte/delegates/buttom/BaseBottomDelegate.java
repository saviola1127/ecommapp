package com.savypan.latte.delegates.buttom;

import com.savypan.latte.delegates.LatteDelegate;

import java.util.ArrayList;

public abstract class BaseBottomDelegate extends LatteDelegate {

	private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
	private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();

}
