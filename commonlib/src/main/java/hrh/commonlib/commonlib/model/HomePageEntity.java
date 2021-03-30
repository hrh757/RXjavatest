package hrh.commonlib.commonlib.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HomePageEntity implements Serializable {
    /**
     * 对应模式背景图片
     */
    @SerializedName("mode_background_img")
    public String modeBackgroundImg;
    /**
     * 模式ID
     */
    @SerializedName("mode_code")
    public String modeCode;
    /**
     * 滚动字幕内容
     */
    @SerializedName("scroll_message")
    public String scrollMessage;
    /**
     * 是否全局滚动字幕：1表示是，0表示否
     */
    @SerializedName("is_global")
    public int isGlobalScrollMessage;
    /**
     * 主页欢迎语
     */
    @SerializedName("mode_welcome_text")
    public String modeWelcomeText;
    /**
     * 主页宣传图片
     */
    @SerializedName("prop_img")
    public String propImg;

    @Override
    public String toString() {
        return "HomePageEntity{" +
                "modeBackgroundImg='" + modeBackgroundImg + '\'' +
                ", modeCode='" + modeCode + '\'' +
                ", scrollMessage='" + scrollMessage + '\'' +
                ", isGlobalScrollMessage='" + isGlobalScrollMessage + '\'' +
                ", modeWelcomeText='" + modeWelcomeText + '\'' +
                ", propImg='" + propImg + '\'' +
                '}';
    }
}
