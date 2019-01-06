package com.savypan.latte.delegates;

/**
 * Created by savypan on 2018/11/7.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {

    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
