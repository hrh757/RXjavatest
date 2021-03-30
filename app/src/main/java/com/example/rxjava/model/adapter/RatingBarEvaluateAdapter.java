package com.example.rxjava.model.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ycsoft.smartbox.newstore.R;
import com.ycsoft.smartbox.newstore.Util.UiUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//import com.ycsoft.smartbox.newstore.utils.UiUtil;

public class RatingBarEvaluateAdapter extends RecyclerView.Adapter<RatingBarEvaluateAdapter.Holder> {

    private List<String> dataList;

    public RatingBarEvaluateAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rating_bar_view, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {

        String model = dataList.get(position);
        holder.tv_pj.setText(model);
        if (position == 0) {
            holder.tv_pj.requestFocus();
            holder.tv_pj.setBackground(holder.viewGroup.getContext().getResources().getDrawable(R.drawable.shape_check_pj_true));
            UiUtil.viewScaleZoomIn(holder.tv_pj, false, 1.1F, 1.1F);
        }else {
            holder.tv_pj.setBackground(holder.viewGroup.getContext().getResources().getDrawable(R.drawable.shape_check_pj_false));
        }
        holder.tv_pj.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                holder.tv_pj.setSelected(b);
                if (b) {
                    holder.tv_pj.setBackground(holder.viewGroup.getContext().getResources().getDrawable(R.drawable.shape_check_pj_true));
                    UiUtil.viewScaleZoomIn(view, false, 1.1F, 1.1F);
                } else {
                    holder.tv_pj.setBackground(holder.viewGroup.getContext().getResources().getDrawable(R.drawable.shape_check_pj_false));
                    UiUtil.viewScaleZoomIn(view, true, 1.1F, 1.1F);
                }
            }
        });
        holder.viewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.ig_view_group)
        View viewGroup;
        @BindView(R.id.tv_pj)
        TextView tv_pj;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface onItemClickListener {
        public void onItemClick(int postion);
    }

    public onItemClickListener onItemClickListener;

    public void setOnItemClickListener(RatingBarEvaluateAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
