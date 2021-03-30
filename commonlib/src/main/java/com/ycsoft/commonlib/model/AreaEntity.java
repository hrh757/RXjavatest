package com.ycsoft.commonlib.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AreaEntity implements Serializable {
    /**
     * 区域id
     */
    @SerializedName("id")
    public int id;
    /**
     * 区域名字
     */
    @SerializedName("name")
    public String name;

    @Override
    public String toString() {
        return "AreaEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AreaEntity that = (AreaEntity) o;
        return id == that.id;
    }
}
