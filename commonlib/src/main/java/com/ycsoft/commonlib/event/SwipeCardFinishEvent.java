package com.ycsoft.commonlib.event;

/**
 * Created by lingchen on 2019/8/17. 17:52
 * mail:lingchen52@foxmail.com
 */
public class SwipeCardFinishEvent {
    private boolean success;
    private String handNumber;

    public SwipeCardFinishEvent(boolean success, String handNumber) {
        this.success = success;
        this.handNumber = handNumber;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getHandNumber() {
        return handNumber;
    }

    public void setHandNumber(String handNumber) {
        this.handNumber = handNumber;
    }
}
