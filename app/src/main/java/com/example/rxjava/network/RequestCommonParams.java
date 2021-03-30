package com.example.rxjava.network;


import com.example.rxjava.common.Constants;
import com.ycsoft.commonlib.common.CommonConstants;
import com.ycsoft.commonlib.util.CommonToolUtil;

import java.io.Serializable;
import java.util.TreeMap;

public class RequestCommonParams implements Serializable {
    public static TreeMap<String, Object> staticParams;

    public static TreeMap<String, Object> getStaticParams() {
        return staticParams;
    }

    private static String[] array= CommonToolUtil.getKeyBoss(CommonConstants.REGISTER_DATA_ENTITY.stores.keyBoss);;

    public  void setStaticParams(TreeMap<String, Object> staticParams) {
        this.staticParams = staticParams;
    }


    /**
     * 移动端公共参数
     * @return TreeMap
     */




    public  RequestCommonParams(){
        TreeMap<String, Object> map = new TreeMap<>();
         String key_boss= array[0];
         String random=array[1];
         String mac= Constants.getMac();;
        map.put("key_boss", key_boss);
        map.put("random", random);
        map.put("mac", mac);
        setStaticParams(map);
    }
}
