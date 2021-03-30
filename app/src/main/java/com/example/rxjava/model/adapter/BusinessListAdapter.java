package com.example.rxjava.model.adapter;

import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ycsoft.smartbox.newstore.R;
import com.ycsoft.smartbox.newstore.Util.UiUtil;
import com.ycsoft.smartbox.newstore.model.BusinessBean;
import com.ycsoft.smartbox.newstore.ui.view.EvaluateView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;

//import com.ycsoft.smartbox.newstore.utils.UiUtil;
//import com.ycsoft.smartbox.newstore.view.EvaluateView;

/**
 * 商家首页
 */
public class BusinessListAdapter extends RecyclerView.Adapter<BusinessListAdapter.Holder> {

    private List<BusinessBean> list= new ArrayList<BusinessBean>();
    private LruCache<Integer, List<BusinessBean>> lruCache = new LruCache<>(6);
    private RecyclerViewClickListener<BusinessBean> onItemClickListener;

    public BusinessListAdapter(List<BusinessBean> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.list = list;
    }

    public RecyclerViewClickListener<BusinessBean> getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(RecyclerViewClickListener<BusinessBean> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<BusinessBean> getList() {
        return list;
    }

    public LruCache<Integer, List<BusinessBean>> getLruCache() {
        return lruCache;
    }

    public void setList(int categoryId , List<BusinessBean> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.list = list;
        lruCache.put(categoryId,list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_framelayout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final BusinessBean businessBean = list.get(position);
        holder.starEvaluate.setShowTrue( businessBean.getRate());
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        holder.tvScore.setText(String.format(Locale.CHINA,"好评 %s",percentInstance.format(businessBean.getRate())));
        holder.tvBusinessName.setText(businessBean.getShop_name());
        holder.tvSalesVolume.setText(convertSalesNum(businessBean.getSell_num()));
        holder.tvDescribe.setText(businessBean.getBusiness_describe());
        loadImage(holder, businessBean.getTop());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onRecycleViewClick(businessBean);
                }
            }
        });
    }

    private void loadImage(Holder holder, List<BusinessBean.TopBean> list) {
        if (list == null || list.isEmpty()){
            return;
        }
        int size = list.size();
        Glide.with(holder.ivFirst)
                    .load(list.get(0).getPicture())
                    .into(holder.ivFirst);
         if (size >= 2){
            Glide.with(holder.ivSecond)
                    .load(list.get(1).getPicture())
                    .into(holder.ivSecond);
        }else if (size >= 3){
            Glide.with(holder.ivThird)
                    .load(list.get(2).getPicture())
                    .into(holder.ivThird);
        }
    }

    private String convertSalesNum(int num){
        String text;
        if (num>10000){
            double i = (double) num / 10000;
            text = String.format(Locale.CHINA,"销量 %f",i);
        }else {
            text = String.format(Locale.CHINA,"销量 %d",num);
        }
        return text;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.starEvaluate)
        EvaluateView starEvaluate;
        @BindView(R.id.viewGroup)
        View viewGroup;
        @BindView(R.id.tvBusinessName)
        TextView tvBusinessName;
        @BindView(R.id.tvSalesVolume)
        TextView tvSalesVolume;
        @BindView(R.id.tvScore)
        TextView tvScore;
        @BindView(R.id.tvDescribe)
        TextView tvDescribe;
        @BindView(R.id.ivFirst)
        ImageView ivFirst;
        @BindView(R.id.ivSecond)
        ImageView ivSecond;
        @BindView(R.id.ivThird)
        ImageView ivThird;


        private Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        @OnFocusChange(R.id.viewGroup)
        void onFocusChange(boolean focused) {
            if (focused) {
                UiUtil.viewScaleZoomIn(viewGroup, false, 1.08F, 1.08F);
            } else {
                UiUtil.viewScaleZoomIn(viewGroup, true, 1.05F, 1.05F);
            }
        }


    }

    public interface RecyclerViewClickListener<T> {
        void onRecycleViewClick(T t);
    }
}
