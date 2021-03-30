package com.example.rxjava.network;

import android.util.Log;

import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 基类observer
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse> {
    private static final String TAG = BaseObserver.class.getSimpleName();

    public String errMsg = "";
    private Disposable disposable;

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
    }

    @Override
    public void onNext(BaseResponse response) {
        response.toString();
        if (response.getStatus() && response.getCode() == 200) {
            onSuccess((T)  response);
        } else {
            onFailure((Throwable) (new Exception(response.getMessage())), response.getMessage());
        }
    }


    @Override
    public void onError(Throwable e) {
        if (e instanceof IOException) {
            errMsg = "网络请求出错！！！！！";
        } else if (e instanceof JsonSyntaxException) {
            errMsg = "返回JSON数据格式有误，反序列化异常！";
        }
        Log.e(TAG, errMsg);
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void stopRequest() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public abstract void onSuccess(T baseResponse);
    public abstract void onFailure(Throwable e, String errorMsg);


}