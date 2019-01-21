package com.savypan.latte.ui.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.savypan.latte.R;
import com.savypan.latte.ui.banner.BannerCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by savypan on 2018/12/18.
 */

public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
    implements BaseQuickAdapter.SpanSizeLookup, OnItemClickListener{

    private boolean mIsInitBanner = false;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();


    public static MultipleRecyclerAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(DataConvertor convertor) {
        return new MultipleRecyclerAdapter(convertor.convert());
    }

    private void init() {
        //set different item layout
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);

        //set border listeners
        setSpanSizeLookup(this);
        openLoadAnimation();

        //多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        //return super.createBaseViewHolder(view);
        return MultipleViewHolder.create(view);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        final String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;

        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = entity.getField(MultipleFields.TEXT);
                holder.setText(R.id.text_single, text);

                break;

            case ItemType.BANNER:
                if (!mIsInitBanner) {
                    bannerImages = entity.getField(MultipleFields.BANNER);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler);

                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }

                break;

            case ItemType.IMAGE:
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.img_sinle));

                break;


            case ItemType.TEXT_IMAGE:
                text = entity.getField(MultipleFields.TEXT);
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);

                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.img_multiple));
                holder.setText(R.id.tv_multiple, text);

                break;

            default:
                break;
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }

    @Override
    public void onItemClick(int position) {

    }
}
