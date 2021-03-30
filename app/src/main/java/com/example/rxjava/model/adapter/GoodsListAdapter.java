package com.example.rxjava.model.adapter;

import android.util.LruCache;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ycsoft.commonlib.util.ToastUtil;
import com.ycsoft.smartbox.newstore.R;
import com.ycsoft.smartbox.newstore.Util.UiUtil;
import com.ycsoft.smartbox.newstore.model.GoodsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

//import com.ycsoft.smartbox.newstore.api.response.commodity.GoodsBean;
//import com.ycsoft.smartbox.newstore.utils.UiUtil;


/**
 * 商家首页
 */
public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.ViewHolder> {

    private List<GoodsBean> list = new ArrayList<>();

    private LruCache<Integer, List<GoodsBean>> lruCache = new LruCache<>(6);

    public GoodsListAdapter() {
    }

    public LruCache<Integer, List<GoodsBean>> getLruCache() {
        return lruCache;
    }

    public void setData(int categoryId, List<GoodsBean> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.list = list;
        lruCache.put(categoryId, list);
        notifyDataSetChanged();
    }

    public List<GoodsBean> getData() {
        return list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commodity_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //数据绑定
        GoodsBean data = list.get(position);
        holder.commodityName.setText(data.getGoods_name());
        holder.commodityName.setTag(position);
        holder.commodityRemaining.setText("库存：" + data.getStock() + data.getSpce());
        holder.commodityPrice.setText(data.getPrice().doubleValue() + "");
        holder.commodityNum.setText(data.getOrderCount() > 0 ? data.getOrderCount() + "" : "");
        if (data.getOrderCount() == 0) {
            holder.commodityReduce.setVisibility(View.GONE);
        } else {
            holder.commodityReduce.setVisibility(View.VISIBLE);
        }
        Glide.with(holder.commodityIcon).load(data.getPicture()).into(holder.commodityIcon);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.commodityIcon)
        ImageView commodityIcon;
        @BindView(R.id.commodityName)
        TextView commodityName;
        @BindView(R.id.commodityRemaining)
        TextView commodityRemaining;
        @BindView(R.id.commodityPrice)
        TextView commodityPrice;
        @BindView(R.id.commodityNum)
        TextView commodityNum;

        @BindView(R.id.commodityIncrease)
        ImageView commodityIncrease;
        @BindView(R.id.commodityReduce)
        ImageView commodityReduce;
        @BindView(R.id.viewGroup)
        View viewGroup;

        private boolean focusedOnIncrease = true;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            viewGroup.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                        switch (keyEvent.getKeyCode()) {
                            case KeyEvent.KEYCODE_DPAD_LEFT:
                                if (!focusedOnIncrease) {
                                    return false;
                                }
                                int position = (int) commodityName.getTag();
                                GoodsBean data = list.get(position);
                                if (data.getOrderCount() > 0) {
                                    commodityIncrease.setImageResource(R.drawable.icon_item_increase_normal);
                                    commodityReduce.setImageResource(R.drawable.icon_item_reduce_focused);
                                    focusedOnIncrease = false;
                                    return true;
                                }
                                break;
                            case KeyEvent.KEYCODE_DPAD_RIGHT:
                                if (focusedOnIncrease) {
                                    return false;
                                }
                                commodityIncrease.setImageResource(R.drawable.icon_item_increase_focused);
                                commodityReduce.setImageResource(R.drawable.icon_item_reduce_normal);
                                focusedOnIncrease = true;
                                return true;
                        }
                    }
                    return false;
                }
            });

        }


        @OnFocusChange(R.id.viewGroup)
        void onFocusChange(View view, boolean focused) {
            focusedOnIncrease = true;
            if (focused) {
                commodityIncrease.setImageResource(R.drawable.icon_item_increase_focused);
                UiUtil.viewScaleZoomIn(viewGroup, false, 1.05F, 1.05F);
            } else {
                UiUtil.viewScaleZoomIn(viewGroup, true);
                commodityIncrease.setImageResource(R.drawable.icon_item_increase_normal);
                commodityReduce.setImageResource(R.drawable.icon_item_reduce_normal);
            }
        }

        @OnClick(R.id.viewGroup)
        void onClick() {
            int position = (int) commodityName.getTag();
            GoodsBean data = list.get(position);
            int orderCount = data.getOrderCount();
            if (focusedOnIncrease) {
                if (orderCount < data.getStock())
                    orderCount++;
                else {
                    ToastUtil.showToast(this.itemView.getContext(), "不能再加了", true);
                }
            } else if (orderCount > 0) {
                orderCount--;
            }

            if (listener != null && orderCount != data.getOrderCount()) {
                data.setOrderCount(orderCount);
                listener.onCountChanged(data);
            }
            data.setOrderCount(orderCount);
            commodityNum.setText(orderCount > 0 ? orderCount + "" : "");
            if (orderCount <= 0) {
                commodityReduce.setVisibility(View.GONE);
                focusedOnIncrease = true;
                commodityIncrease.setImageResource(R.drawable.icon_item_increase_focused);
                commodityReduce.setImageResource(R.drawable.icon_item_reduce_normal);
            } else {
                commodityReduce.setVisibility(View.VISIBLE);
            }


        }

    }


    private OrderCountChangeListener listener;

    public void setCountChangeListener(OrderCountChangeListener listener) {
        this.listener = listener;
    }

    public interface OrderCountChangeListener {
        void onCountChanged(GoodsBean data);
    }
}


