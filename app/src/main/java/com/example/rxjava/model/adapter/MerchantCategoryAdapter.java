package com.example.rxjava.model.adapter;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ycsoft.smartbox.newstore.R;
import com.ycsoft.smartbox.newstore.model.BussinessCategoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//import com.ycsoft.smartbox.newstore.api.response.category.BussinessCategoryBean;

public class MerchantCategoryAdapter extends BaseAdapter {
    private List<BussinessCategoryBean> list = new ArrayList<>();
    private boolean hasFocus;
    private int selectedPosition = -1;
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    /**
     * 是否有焦点
     *
     * @param hasFocus true lv has focus
     */
    public void setHasFocus(boolean hasFocus) {
        this.hasFocus = hasFocus;
    }

    public MerchantCategoryAdapter() {
    }

    public List<BussinessCategoryBean> getList() {
        return list;
    }

    public void setList(List<BussinessCategoryBean> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public BussinessCategoryBean getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category_view, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        if (holder.fl_st != null && position == 0) {
            holder.fl_st.requestFocus();
        }
//        holder.name.setText(list.get(position).getType_name());
        holder.name.setText(list.get(position).getName());
        ColorStateList stateColor = viewGroup.getContext().getResources().getColorStateList(R.color.bg_home_menu_tv_text_color_selector);
        //失去焦点&选中状态
        if (!hasFocus && position == selectedPosition) {
            int textColor = stateColor.getColorForState(new int[]{android.R.attr.state_selected}, R.color.home_fragment_base_theme_yellow);
            holder.name.setTextColor(textColor);
        } else {
            holder.name.setTextColor(stateColor);
        }
        return view;
    }

   final class ViewHolder {
        @BindView(R.id.categoryName)
        TextView name;
        @BindView(R.id.fl_st)
        View fl_st;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
