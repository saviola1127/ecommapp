package com.savypan.latte.ui.recycler;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * Created by savypan on 2018/12/13.
 */

public class MultipleItemEntityBuilder {

    public MultipleItemEntityBuilder() {
        FIELDS.clear();
    }

    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public final MultipleItemEntityBuilder setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    public final MultipleItemEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleItemEntityBuilder setFields(LinkedHashMap<?,?> map) {
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build() {
        return new MultipleItemEntity(FIELDS);
    }
}
