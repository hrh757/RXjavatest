package hrh.commonlib.commonlib.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 功能模块信息实体
 */
public class FunctionEntity implements Serializable {
    public int id;
    /**
     * 商户id
     */
    @SerializedName("store_id")
    public int storeId;
    /**
     * 商户别名
     */
    @SerializedName("alias_name")
    public String aliasName;
    /**
     * 功能截止日期
     */
    @SerializedName("deadline_time")
    public long deadlineTime;
    /**
     * 功能模块状态
     */
    public int status;
    @SerializedName("is_forever")
    /**
     * 是否是永久开启
     */
    public int isForever;
    @SerializedName("function_name")
    /**
     * 功能名字
     */
    public String functionName;
    /**
     * 功能模块的id
     */
    public String code;
    /**
     * 是否按点位计价
     */
    @SerializedName("is_position")
    public int isPosition;

    @Override
    public String toString() {
        return "FunctionEntity{" +
                "id=" + id +
                ", storeId=" + storeId +
                ", aliasName='" + aliasName + '\'' +
                ", deadlineTime=" + deadlineTime +
                ", status=" + status +
                ", isForever=" + isForever +
                ", functionName='" + functionName + '\'' +
                ", code=" + code +
                ", isPosition=" + isPosition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FunctionEntity that = (FunctionEntity) o;
        return code == that.code;
    }
}
