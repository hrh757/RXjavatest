package com.example.rxjava.Util;

import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;

public class UiUtil {
    /**
     * View 缩小
     *
     * @param view     要进行缩小的View
     * @param isReback 是否还原
     */
    public static synchronized void viewScaleZoomOut(View view, boolean isReback) {
        ViewPropertyAnimator viewPropertyAnimator = view.animate();
        if (isReback) {
            viewPropertyAnimator.scaleX(1);
            viewPropertyAnimator.scaleY(1);
        } else {
            viewPropertyAnimator.scaleX(0.9F);
            viewPropertyAnimator.scaleY(0.9F);
        }
        viewPropertyAnimator.setDuration(150);
    }

    /**
     * View 放大
     *
     * @param view     要进行放大的View
     * @param isReback 是否还原
     */
    public static void viewScaleZoomIn(View view, boolean isReback) {
        ViewPropertyAnimator viewPropertyAnimator = view.animate();
        if (isReback) {
            viewPropertyAnimator.scaleX(1);
            viewPropertyAnimator.scaleY(1);
        } else {
            viewPropertyAnimator.scaleX(1.1F);
            viewPropertyAnimator.scaleY(1.1F);
        }
        viewPropertyAnimator.setDuration(150);
    }

    /**
     * View 放大
     *
     * @param view     要进行放大的View
     * @param isReback 是否还原
     * @param xPercent x轴放大百比例
     * @param yPercent y轴放大百比例
     */
    public static void viewScaleZoomIn(View view, boolean isReback, float xPercent, float yPercent) {
        if (view == null) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = view.animate();
        viewPropertyAnimator.setInterpolator(new LinearInterpolator());
        if (isReback) {
            viewPropertyAnimator.scaleX(1);
            viewPropertyAnimator.scaleY(1);
        } else {
            viewPropertyAnimator.scaleX(xPercent);
            viewPropertyAnimator.scaleY(yPercent);
        }
        viewPropertyAnimator.setDuration(200);
    }
}
