package com.savypan.latteec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.savypan.latte.delegates.LatteDelegate;
import com.savypan.latte.net.RestClient;
import com.savypan.latte.net.callback.ISuccess;
import com.savypan.latte.ui.recycler.MultipleItemEntity;
import com.savypan.latteec.R;
import com.savypan.latteec.R2;
import com.savypan.latteec.main.sort.SortDelegate;

import java.util.List;

import butterknife.BindView;

public class VerticalListDelegate extends LatteDelegate {

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);

        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //data lazy loading
        RestClient.builder()
                .url("http://mock.fulingjie.com/mock/api/sort_list.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final List<MultipleItemEntity> data = new VerticalListDataConverter()
                                .setJsonData(response)
                                .convert();

                        final SortDelegate delegate = getParentDelegate();

                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
