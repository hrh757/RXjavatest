package com.ycsoft.commonlib.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class ScaleUtil {

    /**
     * @param restore    是否是还原操作
     * @param targetView 要缩放的view
     * @param xPercent   x轴缩放比例
     * @param yPercent   y轴缩放比例
     * @param duration   过程时间长度（单位：毫秒）
     */
    public static void scale(boolean restore, View targetView, float xPercent, float yPercent, long duration) {
        if (restore) {
            ObjectAnimator animatorX = ObjectAnimator.ofFloat(targetView, "scaleX", xPercent, 1f);
            animatorX.setDuration(duration);
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(targetView, "scaleY", yPercent, 1f);
            animatorY.setDuration(duration);
            AnimatorSet animSet = new AnimatorSet();
            animSet.play(animatorX).with(animatorY);
            animSet.start();
        } else {
            ObjectAnimator animatorX = ObjectAnimator.ofFloat(targetView, "scaleX", 1f, xPercent);
            animatorX.setDuration(duration);
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(targetView, "scaleY", 1f, yPercent);
            animatorY.setDuration(duration);
            AnimatorSet animSet = new AnimatorSet();
            animSet.play(animatorX).with(animatorY);
            animSet.start();
        }
    }
}
