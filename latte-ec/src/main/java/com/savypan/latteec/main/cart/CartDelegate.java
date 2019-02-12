package com.savypan.latteec.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.savypan.latte.delegates.buttom.BottomItemDelegate;
import com.savypan.latte.net.RestClient;
import com.savypan.latte.net.callback.ISuccess;
import com.savypan.latte.ui.recycler.MultipleItemEntity;
import com.savypan.latteec.R;
import com.savypan.latteec.R2;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CartDelegate extends BottomItemDelegate {

    //购物车数量标记
    private int mCurrentCnt = 0;
    private int mTotalCount = 0;

    @BindView(R2.id.icon_cart_selall)
    IconTextView mIconSelAll = null;

    @BindView(R2.id.rv_cart)
    RecyclerView mRecyclerView = null;

    @OnClick(R2.id.icon_cart_selall)
    void onClickSelectAll() {
        final int tag = (int) mIconSelAll.getTag();
        if (tag == 0) {
            mIconSelAll.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
        } else {
            mIconSelAll.setTextColor(Color.GRAY);
            mIconSelAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
        }
        //更新RecyclerView的显示状态
        mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
    }

    @OnClick(R2.id.tv_top_cart_delete)
    void onClickRemoveSelected() {
        final List<MultipleItemEntity> data =  mAdapter.getData();
        //找到要删除的数据
        final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        for (MultipleItemEntity entity:data) {
            final boolean isSelected = entity.getField(CartItemFields.IS_SELECTED);
            if (isSelected) {
                deleteEntities.add(entity);
            }
        }

        for (MultipleItemEntity entity : deleteEntities) {
            int removePosition = 0;
            final int entityPos = entity.getField(CartItemFields.POSITION);
            if (entityPos > mCurrentCnt - 1) {
                removePosition = entityPos - (mTotalCount - mCurrentCnt);
            } else {
                removePosition = entityPos;
            }

            if (removePosition <= mAdapter.getItemCount()) {
                mAdapter.remove(removePosition);
                mCurrentCnt = mAdapter.getItemCount();
                //
                mAdapter.notifyItemRangeChanged(removePosition, mAdapter.getItemCount());
            }
        }
    }

    @OnClick(R2.id.tv_top_cart_clear)
    void onClickClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
    }

    private CartAdapter mAdapter = null;

    //private final static CartAdapter mAdapter = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelAll.setTag(0);
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
                        mAdapter = new CartAdapter(data);
                        mRecyclerView.setAdapter(mAdapter);

                    }
                })
                .build()
                .get();
    }
}
