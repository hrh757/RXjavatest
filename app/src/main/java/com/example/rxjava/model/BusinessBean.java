package com.example.rxjava.model;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.List;

public class BusinessBean implements Serializable {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getBusiness_describe() {
        return business_describe;
    }

    public void setBusiness_describe(String business_describe) {
        this.business_describe = business_describe;
    }

    public int getBusiness_state() {
        return business_state;
    }

    public void setBusiness_state(int business_state) {
        this.business_state = business_state;
    }

    public int getBusiness_model() {
        return business_model;
    }

    public void setBusiness_model(int business_model) {
        this.business_model = business_model;
    }

    public String getBusiness_start_time() {
        return business_start_time;
    }

    public void setBusiness_start_time(String business_start_time) {
        this.business_start_time = business_start_time;
    }

    public String getBusiness_end_time() {
        return business_end_time;
    }

    public void setBusiness_end_time(String business_end_time) {
        this.business_end_time = business_end_time;
    }

    public int getSell_num() {
        return sell_num;
    }

    public void setSell_num(int sell_num) {
        this.sell_num = sell_num;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public boolean isIs_online() {
        return isIs_online;
    }

    public void setIs_online(boolean is_online) {
        isIs_online = is_online;
    }

    public List<TopBean> getTop() {
        return top;
    }

    public void setTop(List<TopBean> top) {
        this.top = top;
    }



    @Override
    public String toString() {
        return "BusinessBean{" +
                "id=" + id +
                ", shop_name='" + shop_name + '\'' +
                ", business_describe='" + business_describe + '\'' +
                ", business_state=" + business_state +
                ", business_model=" + business_model +
                ", business_start_time='" + business_start_time + '\'' +
                ", business_end_time='" + business_end_time + '\'' +
                ", sell_num=" + sell_num +
                ", rate=" + rate +
                ", isIs_online=" + isIs_online +
                ", top=" + top +
                '}';
    }
    private int id;
    private String shop_name;
    private String business_describe;
    private int business_state;
    private int business_model;
    private String business_start_time;
    private String business_end_time;
    private int sell_num;
    private double rate;
    private boolean isIs_online;
    private List<TopBean> top;




    public static final class TopBean implements Serializable {
        private int id;
        private String goods_name;
        @Nullable
        private String picture;
        private int category;

        public final int getId() {
            return this.id;
        }

        public final void setId(int var1) {
            this.id = var1;
        }

        @Nullable
        public final String getGoods_name() {
            return this.goods_name;
        }

        public final void setGoods_name(@Nullable String var1) {
            this.goods_name = var1;
        }

        @Nullable
        public final String getPicture() {
            return this.picture;
        }

        public final void setPicture(@Nullable String var1) {
            this.picture = var1;
        }

        public final int getCategory() {
            return this.category;
        }

        public final void setCategory(int var1) {
            this.category = var1;
        }
    }

}
