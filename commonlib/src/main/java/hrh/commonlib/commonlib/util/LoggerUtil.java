package hrh.commonlib.commonlib.util;

import android.util.Log;

import hrh.commonlib.commonlib.BuildConfig;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by zhangjun on 2017/3/21.
 * 日志工具
 */

public class LoggerUtil {
    private static final String TAG = "LoggerUtil";
    /**
     * 当前是否为调试模式，默认true，打包时需要改为false
     */
    public static final AtomicBoolean isDebug = new AtomicBoolean(BuildConfig.DEBUG);
    //-----------类名作为tag-------------

    /**
     * 以类名作为TAG打印Verbose级别日志信息
     *
     * @param message
     * @param c
     */
    public static void v(Class c, String message) {
        if (isDebug.get()) {
            Log.v(c.getName(), message);
        }
    }

    /**
     * 以类名作为TAG打印Debug级别日志信息
     *
     * @param message
     * @param c
     */
    public static void d(Class c, String message) {
        if (isDebug.get()) {
            Log.d(c.getName(), message);
        }
    }

    /**
     * 以类名作为TAG打印Info级别日志信息
     *
     * @param message
     * @param c
     */
    public static void i(Class c, String message) {
        if (isDebug.get()) {
            Log.i(c.getName(), message);
        }
    }

    /**
     * 以类名作为TAG打印Warn级别日志信息
     *
     * @param message
     * @param c
     */
    public static void w(Class c, String message) {
        if (isDebug.get()) {
            Log.w(c.getName(), message);
        }
    }

    /**
     * 以类名作为TAG打印Error级别日志信息
     *
     * @param message
     * @param c
     */
    public static void e(Class c, String message) {
        if (isDebug.get()) {
            Log.e(c.getName(), message);
        }
    }
    //----------自定义tag-----------

    /**
     * 打印Verbose级别日志信息
     *
     * @param message
     * @param tag
     */
    public static void v(String tag, String message) {
        if (isDebug.get()) {
            Log.v(tag, message);
        }
    }

    /**
     * 打印Debug级别日志信息
     *
     * @param message
     * @param tag
     */
    public static void d(String tag, String message) {
        if (isDebug.get()) {
            Log.d(tag, message);
        }
    }

    /**
     * 打印Info级别日志信息
     *
     * @param message
     * @param tag
     */
    public static void i(String tag, String message) {
        if (isDebug.get()) {
            Log.i(tag, message);
        }
    }

    /**
     * 打印Warn级别日志信息
     *
     * @param message
     * @param tag
     */
    public static void w(String tag, String message) {
        if (isDebug.get()) {
            Log.w(tag, message);
        }
    }

    /**
     * 打印Error级别日志信息
     *
     * @param message
     * @param tag
     */
    public static void e(String tag, String message) {
        if (isDebug.get()) {
            Log.e(tag, message);
        }
    }
    //----------默认tag----------

    /**
     * 使用默认TAG打印Verbose级别日志信息
     *
     * @param message
     */
    public static void v(String message) {
        if (isDebug.get()) {
            Log.v(TAG, message);
        }
    }

    /**
     * 使用默认TAG打印Debug级别日志信息
     *
     * @param message
     */
    public static void d(String message) {
        if (isDebug.get()) {
            Log.d(TAG, message);
        }
    }

    /**
     * 使用默认TAG打印Info级别日志信息
     *
     * @param message
     */
    public static void i(String message) {
        if (isDebug.get()) {
            Log.i(TAG, message);
        }
    }

    /**
     * 使用默认TAG打印Warn级别日志信息
     *
     * @param message
     */
    public static void w(String message) {
        if (isDebug.get()) {
            Log.w(TAG, message);
        }
    }

    /**
     * 使用默认TAG打印Error级别日志信息
     *
     * @param message
     */
    public static void e(String message) {
        if (isDebug.get()) {
            Log.e(TAG, message);
        }
    }
}
