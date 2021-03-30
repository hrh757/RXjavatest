package hrh.commonlib.commonlib.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * launcher 开机广告实体类
 * */
public class BootAdBean implements Serializable {

    /**
     * resource_img : http://ychm-test.oss-cn-beijing.aliyuncs.com/images/r3pOw2CMfnnes40gGQ6sVLYCBi6m9cBur79kgKrU.jpeg
     * resource_video : http://ychm-test.oss-cn-beijing.aliyuncs.com/images/fW5lAvKCZOvV5EyMIkRHdgSIr1BxjKZ5JhZtPtsM.m4v
     * time_out : 5
     * index : 1
     */

    @SerializedName("resource_img")
    private String resourceImg;
    @SerializedName("resource_video")
    private String resourceVideo;
    @SerializedName("time_out")
    private int timeOut;
    private int index;

    public String getResourceImg() {
        return resourceImg;
    }

    public void setResourceImg(String resourceImg) {
        this.resourceImg = resourceImg;
    }

    public String getResourceVideo() {
        return resourceVideo;
    }

    public void setResourceVideo(String resourceVideo) {
        this.resourceVideo = resourceVideo;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
