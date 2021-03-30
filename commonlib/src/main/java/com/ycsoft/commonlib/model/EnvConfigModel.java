package com.ycsoft.commonlib.model;

import java.io.Serializable;

/**
 * 运行环境
 */
public class EnvConfigModel implements Serializable {

    public String crm;
    public String business;
    public String ws;
    //来源 hotel spa
    public String from = HOTEL;

    public static final String SPA = "spa", HOTEL = "hotel";

    public EnvConfigModel(String crm, String business, String ws) {
        this.crm = crm;
        this.business = business;
        this.ws = ws;
    }

    @Override
    public String toString() {
        return "EnvConfigModel{" +
                "crm='" + crm + '\'' +
                ", business='" + business + '\'' +
                ", ws='" + ws + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
