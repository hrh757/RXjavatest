package com.example.rxjava.Util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.LayoutRes;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ycsoft.commonlib.common.CommonConstants;
import com.ycsoft.commonlib.util.CommonToolUtil;
import com.ycsoft.smartbox.newstore.R;
import com.ycsoft.smartbox.newstore.event.Event;
import com.ycsoft.smartbox.newstore.listener.onDialogDismissListener;
import com.ycsoft.smartbox.newstore.listener.onOrderOverListener;
import com.ycsoft.smartbox.newstore.listener.onTwiceDialogDismissListener;
import com.ycsoft.smartbox.newstore.model.FetchPayStatusEntity;
import com.ycsoft.smartbox.newstore.network.BaseObserver;
import com.ycsoft.smartbox.newstore.network.BaseResponse;
import com.ycsoft.smartbox.newstore.network.httpEngine;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

//import com.ycsoft.smartbox.newstore.api.ApiService;
//import com.ycsoft.smartbox.newstore.api.response.order.FetchPayStatusEntity;

/**
 * 支付弹框
 */
public class PayDialogUtil {

    public static final String TYPE_EXTERNAL_SHOP = "external_shop";
    public static final String TYPE_HOTEL = "hotel";

    private String type; // hotle,external_shop
    private Context context;

    private onDialogDismissListener dismissListener;


    private onOrderOverListener overListener;


    private onTwiceDialogDismissListener onTwiceDialogDismissListener;

    private String strFlag = "";

    //    订单ID
    private String orderId = "";

    private Handler mHandler = new Handler();

    /**
     * 是否删除订单，默认没有下单成功需要删除订单
     */
    public boolean deleteOrder = true;

    private PopupWindow window;

    public void setOnTwiceDialogDismissListener(com.ycsoft.smartbox.newstore.listener.onTwiceDialogDismissListener onTwiceDialogDismissListener) {
        this.onTwiceDialogDismissListener = onTwiceDialogDismissListener;
    }

    public void setOnOrderOverListener(onOrderOverListener overListener) {
        this.overListener = overListener;
    }

    public void setDismissListener(onDialogDismissListener dismissListener) {
        this.dismissListener = dismissListener;
    }

    public PayDialogUtil(Context context) {
        this(context,TYPE_HOTEL);
    }

    public PayDialogUtil(Context context, String type) {
        EventBus.getDefault().register(this);
        this.context = context;
        this.type = type;
    }

    /**
     * 挂账 支付宝 微信
     */
    @SuppressLint("SupportAnnotationUsage")
    @LayoutRes
    public void paymentBusinessPopupWinowThree(View v) {
        strFlag = "Three";
        View view = View.inflate(context, R.layout.dialog_payment_business, null);
        final PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //透明背景
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_popupwindow));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        final PaymentBusinessPopup popup = new PaymentBusinessPopup(view, popupWindow);
        popup.rlViewAlipay.requestFocus();
        window = popupWindow;
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (dismissListener != null) {
                    dismissListener.onDialogDismiss(popupWindow, strFlag, popup.llPay);
                }
            }
        });
    }

    /**
     * 挂账
     */
    @SuppressLint("SupportAnnotationUsage")
    @LayoutRes
    public void paymentBusinessPopupWinowHotel(View v) {
        strFlag = "Hotel";
        View view = View.inflate(context, R.layout.dialog_payment_business, null);
        final PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //透明背景
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_popupwindow));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        final PaymentBusinessPopup popup = new PaymentBusinessPopup(view, popupWindow);
        popup.rlViewHotel.requestFocus();
        popup.rlViewAlipay.setVisibility(View.GONE);
        popup.rlViewWechat.setVisibility(View.GONE);
        window = popupWindow;
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (dismissListener != null) {
                    dismissListener.onDialogDismiss(popupWindow, strFlag, popup.llPay);
                }
            }
        });
    }

    /**
     * 支付宝
     */
    @SuppressLint("SupportAnnotationUsage")
    @LayoutRes
    public void paymentBusinessPopupWinowAlipay(View v) {
        strFlag = "Alipay";
        View view = View.inflate(context, R.layout.dialog_payment_business, null);
        final PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //透明背景
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_popupwindow));
        popupWindow.setOutsideTouchable(true);
//        popupWindow.setFocusable(true);
        final PaymentBusinessPopup popup = new PaymentBusinessPopup(view, popupWindow);
        popup.rlViewHotel.setVisibility(View.GONE);
        popup.rlViewAlipay.requestFocus();
        popup.rlViewWechat.setVisibility(View.GONE);
        window = popupWindow;
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (dismissListener != null) {
                    dismissListener.onDialogDismiss(popupWindow, strFlag, popup.llPay);
                }
            }
        });
    }

    /**
     * 支付宝和微信
     */
    @SuppressLint("SupportAnnotationUsage")
    @LayoutRes
    public void paymentBusinessPopupWinowAlipayWechat(View v) {
        strFlag = "AlipayWechat";
        View view = View.inflate(context, R.layout.dialog_payment_business, null);
        final PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //透明背景
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_popupwindow));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        final PaymentBusinessPopup popup = new PaymentBusinessPopup(view, popupWindow);
        popup.rlViewAlipay.requestFocus();
        popup.rlViewHotel.setVisibility(View.GONE);
        window = popupWindow;
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (dismissListener != null) {
                    dismissListener.onDialogDismiss(popupWindow, strFlag, popup.llPay);
                }
            }
        });
    }

    /**
     * 微信
     */
    @SuppressLint("SupportAnnotationUsage")
    @LayoutRes
    public void paymentBusinessPopupWinowWechat(View v) {
        strFlag = "Wechat";
        View view = View.inflate(context, R.layout.dialog_payment_business, null);
        final PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //透明背景
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_popupwindow));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        final PaymentBusinessPopup popup = new PaymentBusinessPopup(view, popupWindow);
        popup.rlViewWechat.requestFocus();
        popup.rlViewHotel.setVisibility(View.GONE);
        popup.rlViewAlipay.setVisibility(View.GONE);
        window = popupWindow;
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (dismissListener != null) {
                    dismissListener.onDialogDismiss(popupWindow, strFlag, popup.llPay);
                }
            }
        });
    }


    /**
     * 酒店二次确认
     *
     * @param v
     * @param orderId
     * @param isSubmission 是否提交 挂账数据
     */
    @SuppressLint("SupportAnnotationUsage")
    @LayoutRes
    public void paymentBusinessPopupTwoHotel(View v, final String orderId, final boolean isSubmission) {
        this.orderId = orderId;
        strFlag = "TwoHotel";
        View view = View.inflate(context, R.layout.dialog_payment_two_confirmation, null);
        final PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //透明背景
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_popupwindow));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        final TwoConfirmationPopup popup = new TwoConfirmationPopup(view, popupWindow);
        popup.btnTips.requestFocus();
        popup.rlViewHotelTwo.setVisibility(View.VISIBLE);
        window = popupWindow;
        popup.btnTips.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view1) {
                                                 if (isSubmission) {
                                                     PayDialogUtil.this.confirmInBill(orderId);
                                                     return;
                                                 }
                                                 if (onTwiceDialogDismissListener != null) {
                                                     onTwiceDialogDismissListener.onClick(window, popup.btnTips.getId(), strFlag);
                                                 }
                                             }
                                         }
        );

        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (onTwiceDialogDismissListener != null) {
                    onTwiceDialogDismissListener.onDialogDismiss(popupWindow, strFlag, popup.llPay);
                }
            }
        });
    }

    /**
     * 支付宝二次确认
     */
    @SuppressLint("SupportAnnotationUsage")
    @LayoutRes
    public void paymentBusinessPopupTwoAlipay(View v, String orderId) {
        this.orderId = orderId;
        strFlag = "TwoAlipay";
        View view = View.inflate(context, R.layout.dialog_payment_two_confirmation, null);
        final PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //透明背景
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_popupwindow));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        final TwoConfirmationPopup popup = new TwoConfirmationPopup(view, popupWindow);
        popup.rlViewAlipayTwo.setVisibility(View.VISIBLE);

        //加载二维码
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.pic_list_null);
        Glide.with(context)
                .load(CommonConstants.BASE_BUSINESS_URL_PREFIX + String.format(CommonConstants.PAYMENT_ALIPAY_SUBFIX, orderId))
                .apply(options)
                .into(popup.qrAlipay);

        //5s后去获取支付状态
        mHandler.postDelayed(removeCallbacks, 5000);
        window = popupWindow;
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PayDialogUtil.this.stopPollPayStatus();
                if (onTwiceDialogDismissListener != null) {
                    onTwiceDialogDismissListener.onDialogDismiss(popupWindow, strFlag, popup.llPay);
                }
            }
        });
    }


    /**
     * 事件响应方法
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event event){
        switch (event.getNum()){
            case 3:
                Toast.makeText(context,"xxx", Toast.LENGTH_LONG).show();
                window.dismiss();
                break;

                }

    }

    /**
     * 微信二次确认
     */
    @SuppressLint("SupportAnnotationUsage")
    @LayoutRes
    public void paymentBusinessPopupTwoWechat(View v, String orderId) {

        this.orderId = orderId;
        strFlag = "TwoWechat";
        View view = View.inflate(context, R.layout.dialog_payment_two_confirmation, null);
        final PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        //透明背景
//        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_popupwindow));
        popupWindow.setOutsideTouchable(true);
//        popupWindow.setFocusable(true);
        final TwoConfirmationPopup popup = new TwoConfirmationPopup(view, popupWindow);
        popup.rlViewWechatTwo.setVisibility(View.VISIBLE);

        //加载二维码
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.pic_list_null);
        Glide.with(context)
                .load(CommonConstants.BASE_BUSINESS_URL_PREFIX +
                        String.format(CommonConstants.PAYMENT_WECHAT_SUBFIX, CommonToolUtil.getMac(), orderId, type))
                .apply(options)
                .into(popup.qrWechatPay);

        window = popupWindow;

        //5s后去获取支付状态
        mHandler.postDelayed(removeCallbacks, 5000);
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PayDialogUtil.this.stopPollPayStatus();
                if (onTwiceDialogDismissListener != null) {
                    onTwiceDialogDismissListener.onDialogDismiss(popupWindow, strFlag, popup.llPay);
                }
            }
        });
    }


    public void showOrderPay(int id, String orderId, View view) {

        switch (id) {
//                挂账
            case R.id.rlViewHotelTwo:
            case R.id.rlViewHotel:
            case R.id.btnTips:
                paymentBusinessPopupTwoHotel(view, orderId, true);
                break;
//                支付宝
            case R.id.rlViewAlipayTwo:
            case R.id.rlViewAlipay:
            case R.id.qrAlipay:
                paymentBusinessPopupTwoAlipay(view, orderId);
                break;
//                微信
            case R.id.rlViewWechatTwo:
            case R.id.rlViewWechat:
            case R.id.qrWechatPay:
                paymentBusinessPopupTwoWechat(view, orderId);
                break;

        }
    }

    public class PaymentBusinessPopup {
        @BindView(R.id.rlViewHotel)
        View rlViewHotel;
        @BindView(R.id.rlViewAlipay)
        View rlViewAlipay;
        @BindView(R.id.rlViewWechat)
        View rlViewWechat;
        @BindView(R.id.llPay)
        View llPay;

        private PopupWindow window;

        public PaymentBusinessPopup(View view, PopupWindow popupWindow) {
            ButterKnife.bind(this, view);
            window = popupWindow;
        }

        @OnFocusChange({R.id.rlViewHotel, R.id.rlViewAlipay, R.id.rlViewWechat})
        void onFocusChange(View view, boolean focused) {
            if (focused) {
                UiUtil.viewScaleZoomIn(view, false, 1.1F, 1.1F);
            } else {
                UiUtil.viewScaleZoomIn(view, true, 1.1F, 1.1F);
            }
        }

        @OnClick({R.id.rlViewHotel, R.id.rlViewAlipay, R.id.rlViewWechat})
        void OnClick(View view) {
            if (view != null && dismissListener != null) {
                dismissListener.onClick(window, view.getId(), strFlag);
            }
        }
    }

    public class TwoConfirmationPopup {
        @BindView(R.id.rlViewHotelTwo)
        View rlViewHotelTwo;
        @BindView(R.id.btnTips)
        Button btnTips;
        @BindView(R.id.rlViewAlipayTwo)
        View rlViewAlipayTwo;
        @BindView(R.id.qrAlipay)
        ImageView qrAlipay;
        @BindView(R.id.rlViewWechatTwo)
        View rlViewWechatTwo;
        @BindView(R.id.qrWechatPay)
        ImageView qrWechatPay;


        @BindView(R.id.llPay)
        View llPay;

        private PopupWindow window;

        public TwoConfirmationPopup(View view, PopupWindow popupWindow) {
            ButterKnife.bind(this, view);
            window = popupWindow;
        }


        @OnClick({R.id.btnTips})
        void OnClick(View view) {
            if (view != null && onTwiceDialogDismissListener != null) {
                onTwiceDialogDismissListener.onClick(window, view.getId(), strFlag);
            }
        }
    }

    /**
     * 获取订单支付状态
     */
    private void fetchPayStatus(String orderId) {
//        ApiService.INSTANCE
//        FetchPayStatusEntity
        httpEngine.fetchPayStatus(orderId,type,new BaseObserver<BaseResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            public void onNext(FetchPayStatusEntity fetchPayStatusEntity) {
                if (fetchPayStatusEntity.getPay_status() == 2) {
                    delayToCloseDialog();
                } else  if (mHandler != null){
                    //每5s去轮询获取支付状态
                    mHandler.postDelayed(removeCallbacks, 5000);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSuccess(BaseResponse baseResponse) {
                FetchPayStatusEntity fetchPayStatusEntity;
                fetchPayStatusEntity = new Gson().fromJson(new Gson().toJson(baseResponse.getData()),  new TypeToken<FetchPayStatusEntity>() {}.getType());
                if (fetchPayStatusEntity.getPay_status() == 2) {
                    delayToCloseDialog();
                } else  if (mHandler != null){
                    //每5s去轮询获取支付状态
                    mHandler.postDelayed(removeCallbacks, 5000);
                }
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }


    /**
     * 下单成功，延时关闭对话框
     */
    public void delayToCloseDialog() {
        deleteOrder = false;

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (window != null) {
                    window.dismiss();
                }
                if (overListener != null) {
                    overListener.onOrderOver(window);
                }
            }
        }, 2000);//记账成功，2s后关闭对话框
    }

    /**
     * 确认记账
     *
     * @param orderId 订单id号
     */
    private void confirmInBill(String orderId) {
        Observable<Object> objectObservable = httpEngine.confirmInBill(orderId,
       new BaseObserver<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
//           public void onNext(Object o) {

            public void onNext(BaseResponse o) {
                if (onTwiceDialogDismissListener != null) {
                    onTwiceDialogDismissListener.onClick(window, 0, strFlag);
                }
                delayToCloseDialog();
            }

            @Override
            public void onError(Throwable e) {
                if (window != null) {
                    ToastUtil.showToast(context, "记账失败！", false);
                    window.dismiss();
                }

            }

            @Override
            public void onComplete() {

            }

           @Override
           public void onSuccess(Object demo) {

           }

           @Override
           public void onFailure(Throwable e, String errorMsg) {

           }
       });
    }

    private Runnable removeCallbacks = new Runnable() {
        @Override
        public void run() {
            PayDialogUtil.this.fetchPayStatus(orderId);
        }
    };

    private void stopPollPayStatus(){
        mHandler.removeCallbacks(removeCallbacks);
        mHandler = null;
    }

}
