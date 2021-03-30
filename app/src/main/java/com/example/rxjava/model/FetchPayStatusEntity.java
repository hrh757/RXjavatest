package com.example.rxjava.model;

public class FetchPayStatusEntity {

    public int getPay_method() {
        return pay_method;
    }

    public void setPay_method(int pay_method) {
        this.pay_method = pay_method;
    }

    public int getPay_status() {
        return pay_status;
    }

    public void setPay_status(int pay_status) {
        this.pay_status = pay_status;
    }

    private int  pay_method;
    /**
     * 支付状态：1待支付，2已支付，3待退款，4已退款
     */
    private int pay_status;

}
