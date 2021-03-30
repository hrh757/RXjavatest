package hrh.commonlib.commonlib.common;

import android.util.Log;

import hrh.commonlib.commonlib.response.RegisterResponseEntity;

public class CommonConstants {

    /*
     * 测试环境模式标记
     * <p>
     * 正式环境，
     * 演示环境，
     * PUB环境，
     * 本地环境，
     * </p>
     */

    /**
     * CRM云服务地址
     */
    public static String BASE_CRM_URL_PREFIX;
    /**
     * 运营平台url地址
     */
    public static String BASE_BUSINESS_URL_PREFIX;
    /**
     * websocket地址
     */
    public static String BASE_REMOTE_WS_PREFIX;
    /**
     * 本地服务器地址
     */
    public static String BASE_LOCAL_URL_PREFIX;

    /**
     * 支付宝支付后缀，使用时填入订单ID
     */
    public static final String PAYMENT_ALIPAY_SUBFIX = "payment/alipay?id=%s";

    /**
     * 微信支付后缀，使用时填入mac 订单ID pay_type支付类型：hotel:酒店内部  external_shop:外部商家
     */
    public static final String PAYMENT_WECHAT_SUBFIX = "wechat/pay_qrcode?mac=%s&id=%s&pay_type=%s";

    public static RegisterResponseEntity.Data REGISTER_DATA_ENTITY;

    /**
     * 更新目标URL信息(跨进程App更新方案)
     */
    public static void updateTargetUrlInfo() {
        if (REGISTER_DATA_ENTITY != null && REGISTER_DATA_ENTITY.envConfigModel != null) {
            Log.i("env_config_init", "updateTargetUrlInfo:" + REGISTER_DATA_ENTITY.envConfigModel.toString());
            updateTargetUrlInfoByModeId(REGISTER_DATA_ENTITY.envConfigModel.crm, REGISTER_DATA_ENTITY.envConfigModel.business, REGISTER_DATA_ENTITY.envConfigModel.ws);
        }
    }

    /**
     * 测试环境通过modeId进行环境配置(for launcher)
     */
    public static void updateTargetUrlInfoByModeId(String crm, String business, String ws) {
        BASE_CRM_URL_PREFIX = crm;
        BASE_BUSINESS_URL_PREFIX = business;
        BASE_REMOTE_WS_PREFIX = ws;
        Log.i("env_config_init", "crm:" + crm);
        Log.i("env_config_init", "business:" + business);
        Log.i("env_config_init", "ws:" + ws);
    }

}
