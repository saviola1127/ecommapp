package com.savypan.latte.delegates.web.event;

import com.savypan.latte.util.log.LatteLogger;

public class UndefinedEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefinedEvent", params);
        return null;
    }
}
