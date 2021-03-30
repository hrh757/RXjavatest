package com.example.rxjava.Util;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by dushicai on 2018/1/20.
 * 延时执行工具
 */
public abstract class ActionDelayTool implements Runnable {

    private Handler mHandler;
    private long mDelayTime;

    public ActionDelayTool(long delayTime) {
        mDelayTime = delayTime;
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 添加一次延时执行行为,默认不清理
     */
    public void tip() {
        mHandler.postDelayed(this, mDelayTime);
    }

    public void tip(long delayTime) {

        if (delayTime < 0) delayTime = 0;

        mHandler.postDelayed(this, delayTime);
    }

    public void tip(boolean clean) {

        if (clean) clean();

        tip();
    }


    public void tip(boolean clean, long delayTime) {

        if (clean) clean();

        tip(delayTime);
    }

    /**
     * 清理
     */
    public void clean() {
        mHandler.removeCallbacksAndMessages(null);
    }

}
