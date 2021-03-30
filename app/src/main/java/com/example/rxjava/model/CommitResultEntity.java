package com.example.rxjava.model;

public class CommitResultEntity {


       private String ali_url;
       private String id;
       private  int is_pay;

    public String getAli_url() {
        return ali_url;
    }

    public void setAli_url(String ali_url) {
        this.ali_url = ali_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(int is_pay) {
        this.is_pay = is_pay;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getWex_url() {
        return wex_url;
    }

    public void setWex_url(String wex_url) {
        this.wex_url = wex_url;
    }

    private String total_price;
       private String wex_url;

}
