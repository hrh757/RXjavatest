package hrh.commonlib.commonlib.observer;

import android.util.Log;

import hrh.commonlib.commonlib.api.IProgressCallback;

import java.io.File;

import io.reactivex.observers.DefaultObserver;

public abstract class DownloadObserver extends DefaultObserver<File> implements IProgressCallback {
    private static final String TAG = DownloadObserver.class.getSimpleName();

    @Override
    public void onNext(File file) {
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "文件下载失败！");
        e.printStackTrace();
    }

    //可以重写，具体可由子类实现
    @Override
    public void onComplete() {
    }
}
