package com.savypan.latteec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by savypan on 2018/11/7.
 */

public enum EcIcons implements Icon {
    icon_scan('\ue606'),
    icon_alipay('\ue606')
    ;

    private char character;
    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
