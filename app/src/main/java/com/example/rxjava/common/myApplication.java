package com.example.rxjava.common;

import android.app.Application;
import android.content.Intent;
import android.os.Process;


import com.example.rxjava.UI.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Application类
 */

public class myApplication extends Application {
    private static final String TAG = myApplication.class.getSimpleName();
    private static myApplication mApplication;
    /**
     * 装Activity的集合
     */
    private List<BaseActivity> activities = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;


    }

    /**
     * 非Activity中可以通过该方法拿到Application单例对象
     *
     * @return
     */
    public static myApplication getInstance() {
        return mApplication;
    }

    /**
     * 添加activity到集合中
     *
     * @param baseActivity
     */
    public void addActivity(BaseActivity baseActivity) {
        activities.add(baseActivity);
    }

    /**
     * 移除一个Activity
     *
     * @param activity
     */
    public void removeActivity(BaseActivity activity) {
        activities.remove(activity);
    }

    /**
     * 退出app，结束当前app进程
     */
    public void exitApp() {
        if (activities.size() > 0) {
            for (BaseActivity baseActivity : activities) {
                if (baseActivity != null) {
                    baseActivity.finish();
                }
            }
        }
        Process.killProcess(Process.myPid());
    }

    /**
     * 返回主页
     */
    public void backHome() {
        exitApp();
    }

    public void playPointSong(Intent intent) {
        sendBroadcast(intent);
    }
}
