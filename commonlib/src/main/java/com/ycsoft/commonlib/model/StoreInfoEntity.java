package com.ycsoft.commonlib.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 商户信息
 */
public class StoreInfoEntity implements Serializable {
    /**
     * 商户ID
     */
    @SerializedName("stores_id")
    public int storesId;
    /**
     * 商户名字
     */
    public String name;
    /**
     * 商户门店名字
     */
    @SerializedName("store_name")
    public String storeName;
    /**
     * 商户分店名字
     */
    @SerializedName("sub_name")
    public String subName;
    /**
     * 省份
     */
    public String province;
    /**
     * 城市
     */
    public String city;
    /**
     * 区域
     */
    public String area;
    /**
     * 联系地址
     */
    public String address;
    /**
     * 营业状态
     */
    @SerializedName("operating_status")
    public String operatingStatus;
    /**
     * 营业时间
     */
    @SerializedName("opening_hours")
    public String openingHours;
    /**
     * 联系电话
     */
    public String telephone;
    /**
     * 地理位置
     */
    public String location;
    /**
     * 百度地图坐标
     */
    @SerializedName("bd09_location")
    public String bd09Location;
    /**
     * 商业描述
     */
    public String description;
    /**
     * 商户状态：0表示正常，1表示停止（被禁用）
     */
    @SerializedName("use_type")
    public int useType;
    /**
     * 绑定的老板id
     */
    @SerializedName("key_boss")
    public String keyBoss;

    /**
     * 营业开始时间
     */
    @SerializedName("start_time")
    public int start_time;
    /**
     * 营业结束时间
     */
    @SerializedName("end_time")
    public int end_time;

    @Override
    public String toString() {
        return "StoreInfoEntity{" +
                "storesId=" + storesId +
                ", name='" + name + '\'' +
                ", storeName='" + storeName + '\'' +
                ", subName='" + subName + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", operatingStatus='" + operatingStatus + '\'' +
                ", openingHours='" + openingHours + '\'' +
                ", telephone='" + telephone + '\'' +
                ", location='" + location + '\'' +
                ", bd09Location='" + bd09Location + '\'' +
                ", description='" + description + '\'' +
                ", useType=" + useType +
                ", keyBoss='" + keyBoss + '\'' +
                '}';
    }
}
