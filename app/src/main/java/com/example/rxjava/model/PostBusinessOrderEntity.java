package com.example.rxjava.model;

import java.util.ArrayList;

public class PostBusinessOrderEntity {

    private ArrayList<BusinessOrderGoodsData> goods;

    public ArrayList<BusinessOrderGoodsData> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<BusinessOrderGoodsData> goods) {
        this.goods = goods;
    }

    public String getDevice_mac() {
        return device_mac;
    }

    public void setDevice_mac(String device_mac) {
        this.device_mac = device_mac;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getKey_boss() {
        return key_boss;
    }

    public void setKey_boss(String key_boss) {
        this.key_boss = key_boss;
    }

    public int getPay_method() {
        return pay_method;
    }

    public void setPay_method(int pay_method) {
        this.pay_method = pay_method;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    private String device_mac;
    private String mac;
    private String random;
    private String key_boss;
    private int pay_method;
    private int room_id;
    private int shop_id;










    public static class BusinessOrderGoodsData {
        private  int goods_id;
        private  int count;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

    }


}
