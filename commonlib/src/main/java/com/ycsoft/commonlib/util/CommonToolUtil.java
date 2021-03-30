package com.ycsoft.commonlib.util;

import com.softwinner.Enc_Jni;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class CommonToolUtil {
    public static int check() {
        return Enc_Jni.getEncPass();
    }

    /**
     * 0表示keyboss，1表示random
     *
     * @param boss
     * @return
     */
    public static String[] getKeyBoss(String boss) {
        long l = System.currentTimeMillis();
        return new String[]{MD5.md5(boss).substring(8, 16) + boss
                + MD5.md5(String.valueOf(l)).substring(2, 10), String.valueOf(l)};
    }

    /**
     * 获取MAC地址
     *
     * @return 获取成功返回MAC地址，失败返回null
     */
    public static String getMac() {
        try {
            String mac = null;
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
                    mac = in.readLine();
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
            return mac;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getLocalIPAddress(){
        try {
            for(Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();){
                NetworkInterface intf = en.nextElement();
                for(Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();){
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if(!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)){
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "null";
    }
}
