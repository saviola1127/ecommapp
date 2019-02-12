package com.savypan.latteec.main.cart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.savypan.latte.ui.recycler.DataConvertor;
import com.savypan.latte.ui.recycler.ItemType;
import com.savypan.latte.ui.recycler.MultipleFields;
import com.savypan.latte.ui.recycler.MultipleItemEntity;
import com.savypan.latte.util.log.LatteLogger;

import java.util.ArrayList;

public class CartDataConverter extends DataConvertor {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject object = dataArray.getJSONObject(i);
            final String thumb = object.getString("thumb");
            final String desc = object.getString("desc");
            final String title = object.getString("title");
            final int id = object.getInteger("id");
            final int count = object.getInteger("count");
            final double price = object.getDouble("price");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, CartItemType.CART_ITEM)
                    .setField(CartItemFields.ID, id)
                    .setField(CartItemFields.TITLE, title)
                    .setField(CartItemFields.IMAGE_URL, thumb)
                    .setField(CartItemFields.COUNT, count)
                    .setField(CartItemFields.PRICE, price)
                    .setField(CartItemFields.DESC, desc)
                    .setField(CartItemFields.IS_SELECTED, false)
                    .setField(CartItemFields.POSITION, i)
                    .build();

            dataList.add(entity);
        }
        return dataList;
    }
}
