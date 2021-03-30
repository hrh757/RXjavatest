package hrh.commonlib.commonlib.api;

import hrh.commonlib.commonlib.RemoteResponseBase;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CommonRequest {
    @POST("h3_error_report")
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    Observable<RemoteResponseBase> errorReport(@Body Map body);
}
