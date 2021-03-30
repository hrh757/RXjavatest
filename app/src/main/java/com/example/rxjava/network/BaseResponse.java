package com.example.rxjava.network;

public class BaseResponse  {

    private  boolean status;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private  int code;
    private String message;
    private Object data;


 /*   public T getGeneral() {
        return general;
    }

    public void setGeneral(T general) {
        this.general = general;
    }

    private T general;*/

}
