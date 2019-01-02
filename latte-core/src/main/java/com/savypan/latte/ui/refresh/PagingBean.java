package com.savypan.latte.ui.refresh;

/**
 * Created by savypan on 2019/1/2.
 */

public final class PagingBean {

    private int mPageIndex = 0;  //current page
    private int mTotal = 0; //total page number
    private int mPageSize = 0;  //size for each page
    private int mCurrentCount = 0; //already shown count
    private int mDelayed = 0; //delay for loading

    public int getPageIndex() {
        return mPageIndex;
    }

    public PagingBean setPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getTotal() {
        return mTotal;
    }

    public PagingBean setTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public PagingBean setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public PagingBean setCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public int getDelayed() {
        return mDelayed;
    }

    public PagingBean setDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }

    PagingBean addIndex() {
        mPageIndex ++;
        return this;
    }
}
