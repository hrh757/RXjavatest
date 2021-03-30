package com.example.rxjava.Util;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ycsoft.smartbox.newstore.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NotificationBarUtil {

    public enum NotificationType {
        //        支付成功
        PAYSUCCESS,
        //        已接单
        ORDERSHAVEBEENRECEIVED,
        //        反馈成功
        FEEDBACKSUCCEEDED
    }

    private Handler handler = new Handler();

    private Context context;

    public NotificationBarUtil(Context context) {
        this.context = context;
    }


    /**
     * 酒店提示框
     *
     * @param view
     * @param type
     */
    public void checkHotelData(View view, NotificationType type) {
        String topTips = "";
        String bottomTips = "";
        int resId = 0;
        switch (type) {
            case PAYSUCCESS:
                topTips = "支付成功!";
                bottomTips = "等待服务员接单，请稍候……";
                resId = R.drawable.icon_notfication_paysuccess;
                break;
            case FEEDBACKSUCCEEDED:
                topTips = "反馈成功!";
                bottomTips = "感谢您的反馈，酒店将竭诚为您服务";
                resId = R.drawable.icon_notfication_feedback;
                break;
            case ORDERSHAVEBEENRECEIVED:
                topTips = "已接单!";
                bottomTips = "服务员已接单，请等待送达……";
                resId = R.drawable.icon_notfication_receipt;
                break;
        }
        showHotelTips(view, topTips, bottomTips, resId);
    }

    /**
     * 商家提示框
     *
     * @param view
     * @param type
     */
    public void checkBusinessData(View view, NotificationType type) {
        String topTips = "";
        String bottomTips = "";
        int resId = 0;
        switch (type) {
            case PAYSUCCESS:
                topTips = "支付成功!";
                bottomTips = "等待对方接单，请稍候……";
                resId = R.drawable.icon_notfication_paysuccess;
                break;
            case FEEDBACKSUCCEEDED:
                topTips = "反馈成功!";
                bottomTips = "感谢您的反馈，酒店将竭诚为您服务";
                resId = R.drawable.icon_notfication_feedback;
                break;
            case ORDERSHAVEBEENRECEIVED:
                topTips = "已接单!";
                bottomTips = "对方已接单，请等待送达……";
                resId = R.drawable.icon_notfication_receipt;
                break;
        }
        showHotelTips(view, topTips, bottomTips, resId);
    }

    private void showHotelTips(View v, String topTips, String bottomTips, int resId) {
        View view = View.inflate(context, R.layout.dialog_notification_bar, null);
        final PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //透明背景
        popupWindow.setOutsideTouchable(false);//点击外部不能消失
        popupWindow.setFocusable(false);
        popupWindow.setAnimationStyle(R.style.popwin_top_anim_style);
        NotificationBarPopup popup = new NotificationBarPopup(view, popupWindow);
        popup.ivIcon.setImageResource(resId);
        popup.tvTopTips.setText(topTips);
        popup.tvBottpmTips.setText(bottomTips);
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                popupWindow.dismiss();
            }
        }, 5000);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
    }


    public class NotificationBarPopup {
        @BindView(R.id.ivIcon)
        ImageView ivIcon;
        @BindView(R.id.tvTopTips)
        TextView tvTopTips;
        @BindView(R.id.tvBottpmTips)
        TextView tvBottpmTips;


        private PopupWindow window;

        public NotificationBarPopup(View view, PopupWindow popupWindow) {
            ButterKnife.bind(this, view);
            window = popupWindow;
        }


    }

}
