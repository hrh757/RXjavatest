package com.example.rxjava.model.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ycsoft.smartbox.newstore.R;
import com.ycsoft.smartbox.newstore.Util.UiUtil;

import butterknife.ButterKnife;
import butterknife.OnFocusChange;

//import com.ycsoft.smartbox.newstore.utils.UiUtil;

public class PayViewAdapter extends RecyclerView.Adapter<PayViewAdapter.Holder> {


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pay_hotel, parent, false);
        } else if (viewType == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pay_alipay, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pay_wechat, parent, false);
        }
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return position;

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
//        if (position == 2) {
//            holder.view.requestFocus();
//        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }


    public class Holder extends RecyclerView.ViewHolder {

//        @BindView(R.id.rlView)
//        View view;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @OnFocusChange({R.id.rlViewHotel, R.id.rlViewAlipay, R.id.rlViewWechat})
    void onFocusChange(View view, boolean focused) {
        if (focused) {
            UiUtil.viewScaleZoomIn(view, false, 1.05F, 1.05F);
        } else {
            UiUtil.viewScaleZoomIn(view, true, 1.05F, 1.05F);
        }
    }
}
