package com.ycsoft.commonlib.mvp;

import android.content.Context;

public interface BaseView<T> {

    void setPresenter(T presenter);

    Context getCtx();

    boolean isActive();

}
