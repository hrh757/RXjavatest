package com.example.rxjava.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.rxjava.common.myApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * 基Activity，所有的Activity继承该Activity以方便统一管理
 */

public abstract class BaseActivity extends AppCompatActivity {
    Unbinder binder;
    private int num;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((myApplication) getApplication()).addActivity(this);
        setContentView(getLayoutId());
        binder=  ButterKnife.bind(this);

        initActivity();
        initView();
        initData();
    }

    protected abstract int getLayoutId();

    /**
     * 初始化Activity，在此设置布局文件
     */
    protected abstract void initActivity();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化显示数据
     */
    protected abstract void initData();

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        binder.unbind();

        ((myApplication) getApplication()).removeActivity(this);
        onClear();
    }

    public void onClear(){

    }

 /*   private overonEvent listener;

    public void setEventListener(overonEvent mEvent){
        this.listener=mEvent;
    }

    public interface  overonEvent{
        void overwriteEvent(int num);
    };*/

}
