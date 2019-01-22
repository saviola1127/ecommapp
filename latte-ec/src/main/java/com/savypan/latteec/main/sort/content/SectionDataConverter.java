package com.savypan.latteec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SectionDataConverter {

    final List<SectionBean> convert(String json) {

        final List<SectionBean> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("section");

            final SectionBean sectionBean = new SectionBean(true, title);
            sectionBean.setId(id);
            sectionBean.setIsMore(true);

            dataList.add(sectionBean);

            final JSONArray goods = data.getJSONArray("goods");
            final int goodsSize = goods.size();
            for (int j = 0; j < goodsSize; j++) {
                final JSONObject contentItem = goods.getJSONObject(j);
                final int gId = contentItem.getInteger("goods_id");
                final String gName = contentItem.getString("goods_name");
                final String gThumb = contentItem.getString("goods_thumb");

                //获取内容
                final SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.setGoodsId(gId);
                itemEntity.setGoodsName(gName);
                itemEntity.setGoodsThumb(gThumb);

                dataList.add(new SectionBean(itemEntity));
            }
            //商品内容循环结束
        }
        //Section循环结束

        return dataList;
    }
}
