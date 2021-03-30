package com.example.rxjava.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsBean implements Serializable {


    /**
     * id : 1
     * goods_name : ly_goods
     * describe : ly_goodsly_goods
     * sell_num : 100
     * price : 10.00
     * stock : 100
     * spce : p
     * picture : 11
     */

    private int id;
    private String goods_name;
    @SerializedName(value = "describe", alternate = {"description"})
    private String describe;
    @SerializedName(value = "sell_num", alternate = {"sales_volume"})
    private int sell_num;
    private BigDecimal price;
    private int stock;
    @SerializedName(value = "spce", alternate = {"unit"})
    private String spce;
    @SerializedName(value = "picture", alternate = {"picture1"})
    private String picture;
    private int orderCount;

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getSell_num() {
        return sell_num;
    }

    public void setSell_num(int sell_num) {
        this.sell_num = sell_num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSpce() {
        return spce;
    }

    public void setSpce(String spce) {
        this.spce = spce;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BigDecimal getTotalPrice(){
        BigDecimal count = new BigDecimal(orderCount);
        return price.multiply(count);
    }
}
