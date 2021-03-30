package hrh.commonlib.commonlib.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 欢迎页面相关内容实体
 */
public class WelcomeEntity implements Serializable {
    /**
     * 营业模式
     */
    @SerializedName("business_model")
    public String businessModel;
    /**
     * 欢迎页面欢迎语
     */
    @SerializedName("welcome_text")
    public String welcomeText;
    /**
     * 欢迎页面背景图片
     */
    @SerializedName("welcome_background_img")
    public String welcomeBackgroundImg;

    @Override
    public String toString() {
        return "WelcomeEntity{" +
                "businessModel='" + businessModel + '\'' +
                ", welcomeText='" + welcomeText + '\'' +
                ", welcomeBackgroundImg='" + welcomeBackgroundImg + '\'' +
                '}';
    }
}
