package com.ycsoft.commonlib.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 基础配置实体
 */
public class GeneralEntity implements Serializable {
    /**
     * logo地址
     */
    @SerializedName("logo_img")
    public String logoImg;
    /**
     * 终端前缀
     */
    @SerializedName("terminal_prefix")
    public String terminalPrefix;
    /**
     * 终端设置密码
     */
    @SerializedName("terminal_pass")
    public String terminalPass;
    /**
     * 风霆迅服务器IP
     */
    @SerializedName("ftx_ip")
    public String ftxIp;
    /**
     * 秒开服务器IP
     */
    @SerializedName("mk_live_ip")
    public String mkLiveIp;

    /**
     * 收款设置：0表示只支持记账，1表示支持壹创代收和记账
     */
    @SerializedName("collect_set")
    public int collectSet;

    @Override
    public String toString() {
        return "GeneralEntity{" +
                "logoImg='" + logoImg + '\'' +
                ", terminalPrefix='" + terminalPrefix + '\'' +
                ", terminalPass='" + terminalPass + '\'' +
                ", ftxIp='" + ftxIp + '\'' +
                ", mkLiveIp='" + mkLiveIp + '\'' +
                ", collectSet=" + collectSet +
                '}';
    }
}
