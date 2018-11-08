package com.savypan.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.savypan.latte.app.Latte;

/**
 * Created by savypan on 2018/11/9.
 */

public class DimenUtil {
    public static int getScreenWidth() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
