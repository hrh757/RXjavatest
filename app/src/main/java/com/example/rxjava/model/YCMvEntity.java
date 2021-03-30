package com.example.rxjava.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * 歌曲实体
 *
 *
 */

public class YCMvEntity implements Serializable {
    /**
     * 电影编号（我们库中的编号）
     */
    public String id="";

    /**
     * 歌星名字
     */
    public String singer;

    /**
     * 电影名字
     */
    public String name;

    /**
     * 首拼
     */
    public String shortName;

    /**
     * 字数
     */
    public String wordCount;

    /**
     * 歌曲种类（例如：喜庆、儿歌、对唱......）
     */
    public String classify;

    /**
     * 歌曲风格类型（比如：风景、动画、故事......）
     */
    public String style;

    /**
     * 点击量
     */
    public String hit;

    /**
     * 默认播放音量
     */
    public String volume;

    /**
     * 声道
     * <p>
     * 取值为2，3，4，5几个值
     * <p>
     * 多音轨：
     * <p>
     * 2代表是第一条音轨为伴唱，第二条音轨为原唱； 3和2相反。
     * <p>
     * 单音轨：
     * <p>
     * 4代表左声道伴唱，右声道原唱；5和4相反
     */
    public String track;
    /**
     * 歌曲文件本地路径
     */
    public String url = "";
    public String oldUrl = "";
    /**
     * 语种
     */
    public String language;

    /**
     * 当前歌曲的状态 status 0:未下载 1下载中 2下载完成
     */
    public String status;
    /**
     * 是否已点播
     */
    public boolean isOrdered;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        YCMvEntity that = (YCMvEntity) o;

        return Objects.equals(id, that.id);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "YCMvEntity{" +
                "id='" + id + '\'' +
                ", singer='" + singer + '\'' +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", wordCount=" + wordCount +
                ", classify=" + classify +
                ", style=" + style +
                ", hit=" + hit +
                ", volume=" + volume +
                ", track=" + track +
                ", url='" + url + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
