package com.ycsoft.commonlib.api;

public interface IProgressCallback {
    //下载进度监听
    void onProgress(String fileName, int progress, long total);
}
