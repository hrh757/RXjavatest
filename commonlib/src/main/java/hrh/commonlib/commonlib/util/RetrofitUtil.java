package hrh.commonlib.commonlib.util;


import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Retrofit相关的工具类
 */
public class RetrofitUtil {
    /**
     * 转换为 form-data请求参数对象集合
     *
     * @param requestDataMap 请求数据集合
     * @return 参数对象集合
     */
    public static Map<String, RequestBody> generateRequestBody(@NonNull Map<String, String> requestDataMap) {
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        for (String key : requestDataMap.keySet()) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                    Objects.requireNonNull(requestDataMap.get(key) == null ? "" : requestDataMap.get(key)));
            requestBodyMap.put(key, requestBody);
        }
        return requestBodyMap;
    }

    /**
     * 转换为 form-data请求参数对象
     *
     * @param requestData 请求数据
     * @return 参数对象
     */
    public static RequestBody getRequestBody(@NonNull String requestData) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), requestData);
    }
}
