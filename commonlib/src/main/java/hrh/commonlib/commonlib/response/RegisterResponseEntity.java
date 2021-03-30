package hrh.commonlib.commonlib.response;

import com.google.gson.annotations.SerializedName;
import hrh.commonlib.commonlib.RemoteResponseBase;
import hrh.commonlib.commonlib.model.AppInfoEntity;
import hrh.commonlib.commonlib.model.AreaEntity;
import hrh.commonlib.commonlib.model.BootAdBean;
import hrh.commonlib.commonlib.model.DeviceEntity;
import hrh.commonlib.commonlib.model.EnvConfigModel;
import hrh.commonlib.commonlib.model.FunctionEntity;
import hrh.commonlib.commonlib.model.GeneralEntity;
import hrh.commonlib.commonlib.model.HomePageEntity;
import hrh.commonlib.commonlib.model.RoomEntity;
import hrh.commonlib.commonlib.model.StoreInfoEntity;
import hrh.commonlib.commonlib.model.WelcomeEntity;
import hrh.commonlib.commonlib.model.WifiApInfoEntity;

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
