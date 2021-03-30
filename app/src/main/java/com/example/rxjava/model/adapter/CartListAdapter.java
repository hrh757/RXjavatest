package com.example.rxjava.model.adapter;

import android.graphics.Color;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ycsoft.commonlib.util.ToastUtil;
import com.ycsoft.smartbox.newstore.R;
import com.ycsoft.smartbox.newstore.model.GoodsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

//import com.ycsoft.smartbox.newstore.api.response.commodity.GoodsBean;


/**
 * 购物车列表页--外部商家
 */
public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private List<GoodsBean> list = new ArrayList<>();


    public CartListAdapter() {
    }


    public void setData(List<GoodsBean> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.list = list;
        notifyDataSetChanged();
    }

    public List<GoodsBean> getData() {
        return list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_cart_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GoodsBean entity = list.get(position);
        holder.tvGoodsName.setText(entity.getGoods_name());
        holder.tvGoodsName.setTag(position);
        holder.tvGoodsPrice.setText("￥" + entity.getPrice().doubleValue());
        holder.tvGoodsCountVar.setText(String.valueOf(entity.getOrderCount()));
        holder.tvGoodsCount.setText("X" + entity.getOrderCount());
        holder.tvGoodsName.setTag(position);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.modifyCountViews)
        LinearLayout modifyCountViews;
        @BindView(R.id.goodsName)
        TextView tvGoodsName;
        @BindView(R.id.goodsPrice)
        TextView tvGoodsPrice;
        @BindView(R.id.btnReduce)
        ImageView btnReduce;
        @BindView(R.id.btnIncrease)
        ImageView btnIncrease;
        @BindView(R.id.goodsCount)
        TextView tvGoodsCount;
        @BindView(R.id.goodsCountVar)
        TextView tvGoodsCountVar;
        @BindView(R.id.cl_item)
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
                                int position = (int) tvGoodsName.getTag();
                                GoodsBean data = list.get(position);
                                if (data.getOrderCount() > 0) {
                                    btnIncrease.setImageResource(R.drawable.icon_sc_increase_normal);
                                    btnReduce.setImageResource(R.drawable.icon_sc_reduce_focused);
                                    focusedOnIncrease = false;
                                    return true;
                                }
                                break;
                            case KeyEvent.KEYCODE_DPAD_RIGHT:
                                if (focusedOnIncrease) {
                                    return false;
                                }
                                btnIncrease.setImageResource(R.drawable.icon_sc_increase_focused);
                                btnReduce.setImageResource(R.drawable.icon_sc_reduce_normal);
                                focusedOnIncrease = true;
                                return true;
                        }
                    }
                    return false;
                }
            });

        }


        @OnFocusChange(R.id.cl_item)
        void onFocusChange(View view, boolean focused) {
            focusedOnIncrease = true;
            if (focused) {
                modifyCountViews.setVisibility(View.VISIBLE);
                tvGoodsCount.setVisibility(View.GONE);
                btnIncrease.setImageResource(R.drawable.icon_sc_increase_focused);
                btnReduce.setImageResource(R.drawable.icon_sc_reduce_normal);
                tvGoodsPrice.setTextColor(Color.WHITE);
            } else {
                modifyCountViews.setVisibility(View.GONE);
                tvGoodsCount.setVisibility(View.VISIBLE);
                tvGoodsPrice.setTextColor(Color.parseColor("#E1A056"));
            }
        }

        @OnClick(R.id.cl_item)
        void onClick() {
            int position = (int) tvGoodsName.getTag();
            GoodsBean data = list.get(position);
            int orderCount = data.getOrderCount();
            if (focusedOnIncrease) {
                if (orderCount < data.getStock())
                    orderCount++;
                else {
                    ToastUtil.showToast(this.itemView.getContext(),"不能再加了",true);
                }
            } else if (orderCount > 0) {
                orderCount--;
            }

            if (listener != null && orderCount != data.getOrderCount()) {
                data.setOrderCount(orderCount);
                listener.onCartGoodsCountChanged(data);
            }
            data.setOrderCount(orderCount);
            tvGoodsCountVar.setText(orderCount > 0 ? orderCount + "" : "0");
            tvGoodsCount.setText("X" + orderCount);
        }

    }


    private CartGoodsCountChangeListener listener;

    public void setCartGoodsCountChangeListener(CartGoodsCountChangeListener listener) {
        this.listener = listener;
    }

    public interface CartGoodsCountChangeListener {
        void onCartGoodsCountChanged(GoodsBean data);
    }
}


