package hrh.commonlib.commonlib.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 设备相关配置信息实体
 */
public class DeviceEntity implements Serializable {
    /**
     * 本地服务器IP地址
     */
    @SerializedName("server_ip")
    public String localServerIp;

    /**
     * 营业模式： 0浴足 1水疗
     */
    public int model;

    /**
     * 该设备是否被移除：0表示设备正常，1表示已经移除
     */
    @SerializedName("is_remove")
    public int isRemove;

    @Override
    public String toString() {
        return "DeviceEntity{" +
                "localServerIp='" + localServerIp + '\'' +
                ", model=" + model +
                ", isRemove=" + isRemove +
                '}';
    }
}
