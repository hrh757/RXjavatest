package com.ycsoft.commonlib.util;

import android.content.Context;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Jeremy on 2016/10/15.
 * Toast提示工具
 */

public class ToastUtil {

    private static Toast toast;
    /**
     * 是否是调试模式
     */
    public static AtomicBoolean isDebug = new AtomicBoolean(true);

    public static void showToast(Context context,
                                 String content, boolean isShort) {
        if (toast == null) {
            if (isShort) {
                toast = Toast.makeText(context.getApplicationContext(),
                        content,
                        Toast.LENGTH_SHORT);
            } else {
                toast = Toast.makeText(context.getApplicationContext(),
                        content,
                        Toast.LENGTH_LONG);
            }
        } else {
            toast.setText(content);
            if (isShort && toast.getDuration() != Toast.LENGTH_SHORT) {
                toast.setDuration(Toast.LENGTH_SHORT);
            } else if (!isShort && toast.getDuration() == Toast.LENGTH_SHORT) {
                toast.setDuration(Toast.LENGTH_LONG);
            }

        }
        toast.show();
    }

    public static void showToast(Context context,
                                 int res, boolean isShort) {
        if (toast == null) {
            if (isShort) {
                toast = Toast.makeText(context.getApplicationContext(),
                        res,
                        Toast.LENGTH_SHORT);
            } else {
                toast = Toast.makeText(context,
                        res,
                        Toast.LENGTH_LONG);
            }
        } else {
            toast.setText(res);
            if (isShort && toast.getDuration() != Toast.LENGTH_SHORT) {
                toast.setDuration(Toast.LENGTH_SHORT);
            } else if (!isShort && toast.getDuration() == Toast.LENGTH_SHORT) {
                toast.setDuration(Toast.LENGTH_LONG);
            }

        }
        toast.show();
    }

    public static void showDebugToast(Context context,
                                      String content, boolean isShort) {
        if (isDebug.get()) {
            if (toast == null) {
                if (isShort) {
                    toast = Toast.makeText(context.getApplicationContext(),
                            content,
                            Toast.LENGTH_SHORT);
                } else {
                    toast = Toast.makeText(context.getApplicationContext(),
                            content,
                            Toast.LENGTH_LONG);
                }
            } else {
                toast.setText(content);
                if (isShort && toast.getDuration() != Toast.LENGTH_SHORT) {
                    toast.setDuration(Toast.LENGTH_SHORT);
                } else if (!isShort && toast.getDuration() == Toast.LENGTH_SHORT) {
                    toast.setDuration(Toast.LENGTH_LONG);
                }

            }
            toast.show();
        }
    }
}
