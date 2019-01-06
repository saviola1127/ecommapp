package com.savypan.latteec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.savypan.latte.delegates.LatteDelegate;
import com.savypan.latteec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class ProductDetailDelegate extends LatteDelegate {

    public static ProductDetailDelegate create() {
        return new ProductDetailDelegate();
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_dp;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
