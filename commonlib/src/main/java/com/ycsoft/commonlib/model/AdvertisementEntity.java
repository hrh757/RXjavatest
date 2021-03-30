package com.ycsoft.commonlib.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 广告实体类
 */
public class AdvertisementEntity implements Serializable {
    /**
     * 主键
     */
    public int id;
    /**
     * 广告名字
     */
    @SerializedName("adv_name")
    public String advName;
    /**
     * 广告位置：1表示开机广告，2表示首页广告(商户设置),3表示首页广告(音创设置),4表示电影广告，5表示电视广告，6表示歌曲广告
     */
    @SerializedName("adv_position")
    public String advPosition;
    /**
     * 广告内容：1表示视频，2表示图片
     */
    @SerializedName("adv_content")
    public String advContent;
    /**
     * 资源列表
     */
    public ArrayList<Resource> resource = new ArrayList<>();
    /**
     * 开机广告视频地址
     */
    public String video;
    /**
     * 广告关联：0技师，1套餐，2商品
     */
    public String adv_rel;
    /**
     * 关联id
     */
    public int rel_id;

    @Override
    public String toString() {
        return "AdvertisementEntity{" +
                "id=" + id +
                ", advName='" + advName + '\'' +
                ", advPosition='" + advPosition + '\'' +
                ", advContent='" + advContent + '\'' +
                ", resource=" + resource +
                ", video='" + video + '\'' +
                '}';
    }

    public class Resource implements Serializable {
        /**
         * 类型：video表示视频，image表示图片
         */
        public String type;
        /**
         * 地址
         */
        public String url;

        @Override
        public String toString() {
            return "Resource{" +
                    "type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
