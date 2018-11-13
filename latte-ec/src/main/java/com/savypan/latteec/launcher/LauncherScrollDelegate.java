package com.savypan.latteec.launcher;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.savypan.latte.delegates.LatteDelegate;
import com.savypan.latte.ui.launcher.LauncherHolderCreator;
import com.savypan.latte.util.storage.LattePreference;
import com.savypan.latteec.R;

import java.util.ArrayList;

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    private void initBanner() {
        INTEGERS.add(com.savypan.latteec.R.mipmap.launcher_01);
        INTEGERS.add(com.savypan.latteec.R.mipmap.launcher_02);
        INTEGERS.add(com.savypan.latteec.R.mipmap.launcher_03);
        INTEGERS.add(com.savypan.latteec.R.mipmap.launcher_04);
        INTEGERS.add(com.savypan.latteec.R.mipmap.launcher_05);

        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        if ( position == INTEGERS.size() - 1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCH_APP.name(), true);

            //检查用户是否已经登陆
        }
    }
}
