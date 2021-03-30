package com.example.rxjava.Util;


import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DeviceManager {
    /**
     * 获取MAC地址
     *
     * @return 获取成功返回MAC地址，失败返回空字符串
     */
    @NonNull
    public static String getMac() {
        String mac = "";
        try {
            FileReader fileReader = null;
            try {
                fileReader = new FileReader("/sys/class/net/eth0/address");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BufferedReader in = null;
            try {
                if (fileReader != null) {
                    in = new BufferedReader(fileReader, 1024);
                    mac += in.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileReader != null)
                        fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            mac = "";
        }
        return mac;
    }
}
