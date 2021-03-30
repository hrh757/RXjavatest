package com.example.rxjava.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * 全局常量和静态变量类
 */

public class Constants {
/*//    public static String LOCAL_MOUNTED_DIR = "nanda/YCMovie/";
    public static final long DELAY_TIME_DURATION = 3000;
    *//**
     * 数据库版本
     *//*
    public static int DB_VERSION = 1;
    *//**
     * 打开一个Activity时延时毫秒值
     *//*
    public static final long START_ACTIVITY_DELAYED = 300L;
    *//**
     * View缩放持续时间毫秒值
     *//*
    public static final long VIEW_SCALE_DURATION = 150L;
    *//**
     * 歌星头像地址
     *//*
    public static final String SINGER_AVATAR = "singerImg/";
    *//**
     * 编码格式(点歌Socket消息编码)
     *//*
    public static final String CHARSET = "gb18030";
    //个推相关的
    public static String APP_KEY;
    public static String APP_ID;
    public static String CID;*/


    private static int TYPE;
    public static final Constants INSTANCE;

    public final int getTYPE() {
        return TYPE;
    }

    public final void setTYPE(int var1) {
        TYPE = var1;
    }


    public static final String getMac() {
        String mac = "";

        try {
            FileReader fileReader = (FileReader)null;

            try {
                fileReader = new FileReader("/sys/class/net/eth0/address");
            } catch (FileNotFoundException var20) {
                var20.printStackTrace();
            }

            BufferedReader br = (BufferedReader)null;

            try {
                if (fileReader != null) {
                    br = new BufferedReader((Reader)fileReader, 1024);
                    mac = mac + br.readLine();
                }
            } catch (IOException var18) {
                var18.printStackTrace();
            } finally {
                try {
                    if (fileReader != null) {
                        fileReader.close();
                    }
                } catch (IOException var17) {
                    var17.printStackTrace();
                }

                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException var16) {
                        var16.printStackTrace();
                    }
                }

            }
        } catch (Exception var21) {
            var21.printStackTrace();
            mac = "";
        }

        return mac;
    }

    private Constants() {
    }

    static {
        Constants var0 = new Constants();
        INSTANCE = var0;
        TYPE = 2;
    }




}
