package com.ycsoft.commonlib.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zhang on 2017/9/13.
 * wifi热点信息
 */

public class WifiApInfoEntity implements Serializable {
    /**
     * 热点名字
     */
    @SerializedName("ssid_name")
    public String ssidPrefix;
    /**
     * 热点密码
     */
    public String password;
    /**
     * 自动重启
     */
    public int reboot;
    /**
     * 重启间隔时间 单位：小时
     */
    @SerializedName("rebootTime")
    public long rebootTime;
    /**
     * 是否启用终端热点
     */
    public int enable;
    /**
     * 房间号
     */
    @SerializedName("room_code")
    public String roomCode;

    @Override
    public String toString() {
        return "WifiApInfoEntity{" +
                "ssidPrefix='" + ssidPrefix + '\'' +
                ", password='" + password + '\'' +
                ", reboot=" + reboot +
                ", rebootTime=" + rebootTime +
                ", enable=" + enable +
                '}';
    }
}
