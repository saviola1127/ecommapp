package com.savypan.latte.util.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by savypan on 2018/11/12.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener iTimerListener = null;
    public BaseTimerTask(ITimerListener iTimerListener) {
        this.iTimerListener = iTimerListener;
    }

    @Override
    public void run() {
        if (iTimerListener != null) {
            iTimerListener.onTimer();
        }
    }
}
