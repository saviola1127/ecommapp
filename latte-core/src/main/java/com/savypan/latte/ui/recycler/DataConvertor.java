package com.savypan.latte.ui.recycler;

import java.util.ArrayList;

/**
 * Created by savypan on 2018/12/11.
 */

public abstract class DataConvertor {

    protected final ArrayList<MultipleItemEntity> ENTITYS = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConvertor setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("Data is NULL!");
        }

        return mJsonData;
    }
}
