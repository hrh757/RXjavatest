package com.ycsoft.commonlib.response;

import com.google.gson.annotations.SerializedName;
import com.ycsoft.commonlib.RemoteResponseBase;
import com.ycsoft.commonlib.model.AppInfoEntity;
import com.ycsoft.commonlib.model.AreaEntity;
import com.ycsoft.commonlib.model.BootAdBean;
import com.ycsoft.commonlib.model.DeviceEntity;
import com.ycsoft.commonlib.model.EnvConfigModel;
import com.ycsoft.commonlib.model.FunctionEntity;
import com.ycsoft.commonlib.model.GeneralEntity;
import com.ycsoft.commonlib.model.HomePageEntity;
import com.ycsoft.commonlib.model.RoomEntity;
import com.ycsoft.commonlib.model.StoreInfoEntity;
import com.ycsoft.commonlib.model.WelcomeEntity;
import com.ycsoft.commonlib.model.WifiApInfoEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegisterResponseEntity extends RemoteResponseBase<RegisterResponseEntity.Data> implements Serializable {
    public class Data implements Serializable {
        /**
         * 设备信息
         */
        public DeviceEntity devices;
        /**
         * 房间信息
         */
        public RoomEntity room;
        /**
         * 区域信息
         */
        public AreaEntity area;
        /**
         * 商户信息
         */
        public StoreInfoEntity stores;
        /**
         * 一般设置信息
         */
        public GeneralEntity general;
        /**
         * 欢迎信息
         */
        public WelcomeEntity welcome;
        /**
         * 主页信息
         */
        public HomePageEntity homeModel;
        /**
         * 运行环境
         */
        public EnvConfigModel envConfigModel;
        /**
         * 开通的功能模块
         */
        @SerializedName("Functions")
        public ArrayList<FunctionEntity> functions = new ArrayList<>();
        /**
         * wifi热点信息
         */
        public WifiApInfoEntity wifi;
        /**
         * 开机广告
         */
        @SerializedName("boot_adv")
        public ArrayList<BootAdBean> bootAdv = new ArrayList<>();

        /**
         * 房间用户
         */
        public String room_user;

        public List<AppInfoEntity> apps;

        @Override
        public String toString() {
            return "Data{" +
                    ", devices=" + devices +
                    ", room=" + room +
                    ", area=" + area +
                    ", stores=" + stores +
                    ", general=" + general +
                    ", welcome=" + welcome +
                    ", homeModel=" + homeModel +
                    ", functions=" + functions +
                    ", wifi=" + wifi +
                    ",bootAdv=" + bootAdv +
                    ", room_user='" + room_user +
                    "apps" + apps + '\'' +
                    '}';
        }
    }
}
