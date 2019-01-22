package com.savypan.latteec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.savypan.latte.delegates.LatteDelegate;
import com.savypan.latte.ui.recycler.ItemType;
import com.savypan.latte.ui.recycler.MultipleFields;
import com.savypan.latte.ui.recycler.MultipleItemEntity;
import com.savypan.latte.ui.recycler.MultipleRecyclerAdapter;
import com.savypan.latte.ui.recycler.MultipleViewHolder;
import com.savypan.latteec.R;
import com.savypan.latteec.main.sort.SortDelegate;
import com.savypan.latteec.main.sort.content.ContentDelegate;

import java.util.List;

public class SortRecyclerAdapter extends MultipleRecyclerAdapter {

    private final SortDelegate DELEGATE;
    private int mPrePosition = 0;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;

        //添加垂直布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity entity) {

        super.convert(holder, entity);

        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String text = entity.getField(MultipleFields.TEXT);
                final boolean isClicked = entity.getField(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPos = holder.getAdapterPosition();
                        if (mPrePosition != currentPos) {
                            //还原上一个
                            getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);

                            //更新选中的部分
                            entity.setField(MultipleFields.TAG, true);
                            notifyItemChanged(currentPos);
                            mPrePosition = currentPos;

                            final int contentId = getData().get(currentPos).getField(MultipleFields.ID);
                            showContent(contentId);
                            //Toast.makeText(mContext, "contentId is " + contentId, Toast.LENGTH_LONG).show();

                        }
                    }
                });

                if (!isClicked) {
                    line.setVisibility(View.INVISIBLE);
                    //兼容各种机型
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.wechat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_background));
                } else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }

                holder.setText(R.id.tv_vertical_item_name, text);

                break;

        }
    }

    private void showContent(int contentId) {
        final ContentDelegate delegate = ContentDelegate.newInstance(contentId);
        switchContent(delegate);
    }

    private void switchContent(ContentDelegate delegate) {
        final LatteDelegate contentDelegate = DELEGATE.findChildFragment(ContentDelegate.class);
        if (contentDelegate != null) {
            contentDelegate.replaceFragment(delegate, false);
        }
    }
}
