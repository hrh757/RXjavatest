package com.example.rxjava.network;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 *
 * 网络工具类
 */

public class NetworkUtil {
    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        boolean isConnected = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
            isConnected = true;
        } else if (cm.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET).isConnected()) {
            isConnected = true;
        }
        return isConnected;
    }

/*    *//**
     * 判断有线网是否已经连上
     *
     * @param context
     * @return
     *//*
    public static boolean isEthernetConnected(Context context) {
        @SuppressLint("WrongConstant") EthernetManager ethernetManager = (EthernetManager) context.getSystemService(ContextSupport.ETHERNET_SERVICE);
        return ethernetManager.getLinkState();
    }*/
}
