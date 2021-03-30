

package hrh.commonlib.commonlib.ApiLog;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import hrh.commonlib.commonlib.BuildConfig;
import hrh.commonlib.commonlib.RemoteResponseBase;
import hrh.commonlib.commonlib.api.CommonRequest;
import hrh.commonlib.commonlib.common.CommonConstants;
import hrh.commonlib.commonlib.common.RetrofitClient;
import hrh.commonlib.commonlib.observer.BaseObserver;
import hrh.commonlib.commonlib.util.CharacterHandler;
import hrh.commonlib.commonlib.util.CommonToolUtil;
import hrh.commonlib.commonlib.util.UrlEncoderUtils;
import hrh.commonlib.commonlib.util.ZipHelper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


/**
 * 此拦截器可以在请求出错和异常时上报日志给平台
 * */

public class RequestErrorReportInterceptor implements Interceptor {


    public RequestErrorReportInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String params = "";
        if (request.body() != null && isParseable(request.body().contentType())) {
          params = parseParams(request);
        }
        Response originalResponse;
        try {
            originalResponse = chain.proceed(request);
        } catch (Exception e) {
            Log.w("intercept", "Http Error: " + e);
            loadErrorLog("Http Error: " + e,request.url().toString(),params);
            throw e;
        }

        ResponseBody responseBody = originalResponse.body();
        String bodyString = "";
        if (responseBody != null && isParseable(responseBody.contentType())) {
            bodyString = parseContent(originalResponse);
        }

        if (!isSuccess(bodyString)){
            String url = originalResponse.request().url().toString();
            loadErrorLog(bodyString,url,params);
        }
        return originalResponse;
    }

    private boolean isSuccess(String bodyString){
        try {
            JsonObject bodyJson = new JsonParser().parse(bodyString).getAsJsonObject();
            bodyJson.getAsJsonObject(bodyString);
            // 自身平台接口
            JsonElement code = bodyJson.get("code");
            if (code != null && code.getAsInt() == 200){
                return true;
            }
            // 风霆迅网络的第三方接口
            JsonElement res = bodyJson.get("res");
            if (res != null  && res.getAsInt() == 0){
                return true;
            }
            // 秒开网络的第三方接口
            JsonElement ret = bodyJson.get("Ret");
            if (ret != null  && ret.getAsInt() == 0){
                return true;
            }
            JsonElement ret2 = bodyJson.get("ret");
            if (ret2 != null  && ret2.getAsInt() == 1){
                return true;
            }
            JsonElement result = bodyJson.get("Result");
            if (result != null && result.getAsInt() == 1){
                return true;
            }
            JsonElement programs = bodyJson.get("Programs");
            if (programs != null && programs.getAsJsonArray().size()>0){
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    private void loadErrorLog(String data,String url,String  params){
        Map<String,Object> uploadData = new HashMap<>();
        uploadData.put("mac", CommonToolUtil.getMac());
        uploadData.put("version", BuildConfig.VERSION_NAME);
        if (CommonConstants.REGISTER_DATA_ENTITY != null && CommonConstants.REGISTER_DATA_ENTITY.stores != null ){
            uploadData.put("store_id", CommonConstants.REGISTER_DATA_ENTITY.stores.storesId);
        }
        uploadData.put("type",2);
        uploadData.put("ip",CommonToolUtil.getLocalIPAddress());
        uploadData.put("url",url);
        uploadData.put("parameter",params);
        uploadData.put("error",data);
        CommonRequest commonRequest = RetrofitClient.getBusinessInstance().create(CommonRequest.class);
        commonRequest.errorReport(uploadData)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new BaseObserver<RemoteResponseBase>() {
                    @Override
                    public void onNext(RemoteResponseBase remoteResponseBase) {
                        super.onNext(remoteResponseBase);
                        Log.d("loadErrorLog","onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        e.printStackTrace();
                        Log.d("loadErrorLog","onError");
                    }
                });
    }

    /**
     * 打印响应结果
     *
     * @param request     {@link Request}
     * @param response    {@link Response}
     * @param logResponse 是否打印响应结果
     * @return 解析后的响应结果
     * @throws IOException
     */
    @Nullable
    private String printResult(Request request, Response response, boolean logResponse) throws IOException {
        try {
            //读取服务器返回的结果
            ResponseBody responseBody = response.newBuilder().build().body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            //获取content的压缩类型
            String encoding = response
                    .headers()
                    .get("Content-Encoding");

            Buffer clone = buffer.clone();

            //解析response content
            return parseContent(responseBody, encoding, clone);
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }

    private String parseContent(Response response){
        try {
            //读取服务器返回的结果
            ResponseBody responseBody = response.newBuilder().build().body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            //获取content的压缩类型
            String encoding = response
                    .headers()
                    .get("Content-Encoding");

            Buffer clone = buffer.clone();

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            if (encoding != null && encoding.equalsIgnoreCase("gzip")) {//content 使用 gzip 压缩
                return ZipHelper.decompressForGzip(clone.readByteArray(), convertCharset(charset));//解压
            } else if (encoding != null && encoding.equalsIgnoreCase("zlib")) {//content 使用 zlib 压缩
                return ZipHelper.decompressToStringForZlib(clone.readByteArray(), convertCharset(charset));//解压
            } else {//content 没有被压缩, 或者使用其他未知压缩方式
                return clone.readString(charset);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }
    /**
     * 解析服务器响应的内容
     *
     * @param responseBody {@link ResponseBody}
     * @param encoding     编码类型
     * @param clone        克隆后的服务器响应内容
     * @return 解析后的响应结果
     */
    private String parseContent(ResponseBody responseBody, String encoding, Buffer clone) {
        Charset charset = Charset.forName("UTF-8");
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(charset);
        }
        if (encoding != null && encoding.equalsIgnoreCase("gzip")) {//content 使用 gzip 压缩
            return ZipHelper.decompressForGzip(clone.readByteArray(), convertCharset(charset));//解压
        } else if (encoding != null && encoding.equalsIgnoreCase("zlib")) {//content 使用 zlib 压缩
            return ZipHelper.decompressToStringForZlib(clone.readByteArray(), convertCharset(charset));//解压
        } else {//content 没有被压缩, 或者使用其他未知压缩方式
            return clone.readString(charset);
        }
    }

    /**
     * 解析请求服务器的请求参数
     *
     * @param request {@link Request}
     * @return 解析后的请求信息
     * @throws UnsupportedEncodingException
     */
    public static String parseParams(Request request) throws UnsupportedEncodingException {
        try {
            RequestBody body = request.newBuilder().build().body();
            if (body == null) return "";
            Buffer requestbuffer = new Buffer();
            body.writeTo(requestbuffer);
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            String json = requestbuffer.readString(charset);
            if (UrlEncoderUtils.hasUrlEncoded(json)) {
                json = URLDecoder.decode(json, convertCharset(charset));
            }
            return CharacterHandler.jsonFormat(json);
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }

    /**
     * 是否可以解析
     *
     * @param mediaType {@link MediaType}
     * @return {@code true} 为可以解析
     */
    public static boolean isParseable(MediaType mediaType) {
        if (mediaType == null || mediaType.type() == null) return false;
        return isText(mediaType) || isPlain(mediaType)
                || isJson(mediaType) || isForm(mediaType)
                || isHtml(mediaType) || isXml(mediaType);
    }

    public static boolean isText(MediaType mediaType) {
        if (mediaType == null || mediaType.type() == null) return false;
        return mediaType.type().equals("text");
    }

    public static boolean isPlain(MediaType mediaType) {
        if (mediaType == null || mediaType.subtype() == null) return false;
        return mediaType.subtype().toLowerCase().contains("plain");
    }

    public static boolean isJson(MediaType mediaType) {
        if (mediaType == null || mediaType.subtype() == null) return false;
        return mediaType.subtype().toLowerCase().contains("json");
    }

    public static boolean isXml(MediaType mediaType) {
        if (mediaType == null || mediaType.subtype() == null) return false;
        return mediaType.subtype().toLowerCase().contains("xml");
    }

    public static boolean isHtml(MediaType mediaType) {
        if (mediaType == null || mediaType.subtype() == null) return false;
        return mediaType.subtype().toLowerCase().contains("html");
    }

    public static boolean isForm(MediaType mediaType) {
        if (mediaType == null || mediaType.subtype() == null) return false;
        return mediaType.subtype().toLowerCase().contains("x-www-form-urlencoded");
    }

    public static String convertCharset(Charset charset) {
        String s = charset.toString();
        int i = s.indexOf("[");
        if (i == -1)
            return s;
        return s.substring(i + 1, s.length() - 1);
    }
}
