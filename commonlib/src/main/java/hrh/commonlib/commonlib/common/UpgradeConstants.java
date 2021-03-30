package hrh.commonlib.commonlib.common;

public class UpgradeConstants {
    /**
     * 升级应用服务的名字
     */
    public static final String NAME_OF_UPGRADE_APP_SERVICE = "UpgradeAppService";
    /**
     * 升级应用升级自身的服务的名字
     */
    public static final String NAME_OF_UPGRADE_SELF_SERVICE = "UpgradeSelfService";
    //--------状态---------
    /**
     * 已经是最新版了
     */
    public static final String STATUS_ALREADY_LATEST_VERSION = "status_latest";

    //-----------广播动作----------
    /**
     * 回复状态广播动作
     */
    public static final String ACTION_BC_UPGRADE_STATUS = "action.RESPONSE_UPGRADE_STATUS";

    //---------其它--------
    /**
     * 开始检查更新启动服务动作
     */
    public static final String ACTION_START_CHECK_UPGRADE = "action.UPGRADE_APPS";
    /**
     * 开始检测升级apk更新
     */
    public static final String ACTION_START_CHECK_SELF_UPGRADE = "action.SELF_UPGRADE_APPS";
    /**
     * 开始检测Room升级
     */
    public static final String ACTION_START_CHECK_ROOM_UPGRADE = "action.UPGRADE_ROOM";

}
