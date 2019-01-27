package com.savypan.latteec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.savypan.latte.delegates.buttom.BottomItemDelegate;
import com.savypan.latte.net.RestClient;
import com.savypan.latte.net.callback.ISuccess;
import com.savypan.latte.ui.recycler.MultipleItemEntity;
import com.savypan.latteec.R;
import com.savypan.latteec.R2;


import java.util.ArrayList;

import butterknife.BindView;

public class CartDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_cart)
    RecyclerView mRecyclerView = null;

    //private final static CartAdapter mAdapter = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("http://mock.fulingjie.com/mock/api/shop_cart.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                        final ArrayList<MultipleItemEntity> data =
                                new CartDataConverter()
                                        .setJsonData(response)
                                        .convert();

                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final CartAdapter adapter = new CartAdapter(data);
                        mRecyclerView.setAdapter(adapter);

                    }
                })
                .build()
                .get();
    }
}
