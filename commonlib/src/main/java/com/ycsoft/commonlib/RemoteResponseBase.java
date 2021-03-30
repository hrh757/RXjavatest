package com.ycsoft.commonlib;

import java.io.Serializable;

/**
 * 请求返回结果公共字段类
 */
public class RemoteResponseBase<T> implements Serializable {
    /**
     * 请求状态
     */
    public boolean status;
    /**
     * 状态码
     */
    public int code;
    /**
     * 状态信息
     */
    public String message;
    /**
     * 结果数据
     */
    public T data;

    @Override
    public String toString() {
        return "RemoteResponseBase{" +
                "status=" + status +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
