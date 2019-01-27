package com.savypan.latteec.main.cart;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;
import com.savypan.latte.ui.recycler.ItemType;
import com.savypan.latte.ui.recycler.MultipleItemEntity;
import com.savypan.latte.ui.recycler.MultipleRecyclerAdapter;
import com.savypan.latte.ui.recycler.MultipleViewHolder;
import com.savypan.latte.util.log.LatteLogger;
import com.savypan.latteec.R;

import java.util.List;

public class CartAdapter extends MultipleRecyclerAdapter {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected CartAdapter(List<MultipleItemEntity> data) {
        super(data);

        addItemType(CartItemType.CART_ITEM, R.layout.item_cart);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);

        switch (holder.getItemViewType()) {
            case CartItemType.CART_ITEM:
                final String thumb = entity.getField(CartItemFields.IMAGE_URL);
                final int id = entity.getField(CartItemFields.ID);
                final String title = entity.getField(CartItemFields.TITLE);
                final String desc = entity.getField(CartItemFields.DESC);
                final int count = entity.getField(CartItemFields.COUNT);
                final double price = entity.getField(CartItemFields.PRICE);

                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_cart_name);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_cart_cnt);

                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext).load(thumb).into(imgThumb);

                break;


                default:
                    break;
        }
    }
}
