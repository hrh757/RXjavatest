package hrh.commonlib.commonlib.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RoomEntity implements Serializable {
    /**
     * 房间ID
     */
    @SerializedName("id")
    public int id;
    /**
     * 房间名字
     */
    @SerializedName("room_name")
    public String roomName;
    /**
     * 房间类型id
     */
    @SerializedName("room_type_id")
    public int roomTypeId;
    /**
     * 房间号
     */
    @SerializedName("room_code")
    public String roomCode;
    /**
     * 房间编号
     */
    @SerializedName("room_number")
    public String roomNumber;
    /**
     * 所在区域id
     */
    @SerializedName("area_id")
    public int areaId;
    /**
     * 房间里床位数量
     */
    @SerializedName("bed_number")
    public int bedNumber;
    /**
     * 已使用床位数量
     */
    @SerializedName("bed_used")
    public int bedUsed;
    /**
     * 楼层
     */
    @SerializedName("floor")
    public int floor;
    /**
     * 房间状态 0：空闲 1：使用中 2：待清理 3:已满
     */
    @SerializedName("state")
    public int state;

    @Override
    public String toString() {
        return "RoomEntity{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", roomTypeId=" + roomTypeId +
                ", roomNumber='" + roomNumber + '\'' +
                ", areaId=" + areaId +
                ", bedNumber=" + bedNumber +
                ", bedUsed=" + bedUsed +
                ", floor=" + floor +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomEntity that = (RoomEntity) o;
        return id == that.id;
    }
}
