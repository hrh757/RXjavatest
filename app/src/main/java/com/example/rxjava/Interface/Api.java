package com.example.rxjava.Interface;

//import android.database.Observable;


import com.example.rxjava.network.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    /**
     * 获取分类
     *
     *
     * @return
     */
    @POST("hotel/goods_type")
    @Headers({"Content-Type:application/json;charset=UTF-8"})
//    Observable<CategoryListEntity> categoryList(@Body RequestBody body);
    Observable<BaseResponse> categoryList(@Query("type") int type, @Query("key_boss") String key_boss, @Query("random") String random, @Query("mac") String mac);

    /**
     * 商品详情
     *
     *
     */
    @POST("hotel/goods_list")
//    Observable<CommodityListPost> commodityList( );
    Observable<BaseResponse> commodityList(@Body Map var);

    /**
     *
     * 提交订单
     */
    @POST("hotel/add_goods_orders")

//      PostCommitOrderEntity  CommitResultEntity
    Observable<BaseResponse> commitOrder(@Body Map v);

    /**
     * 获取支付状态
     *
     */
    @POST("hotel/get_orders_status")
//    FetchPayStatusEntity
    Observable<BaseResponse> fetchPayStatus(@Body Map var );

    /**
     * 确认挂账
     */
    @POST("hotel/bookkeeping")
//    PostConfirmInBillEntity
    Observable<Object> confirmInBill( );

    /**
     * 删除订单
     */
    @POST("hotel/delete_order")
//    Observable<PostDeleteOrderEntity> deleteOrder( );
    Observable<Object> deleteOrder( );


    /**
     * 获取外部商家的分类
     */
    @POST("shop/h3/get_h3shopcategray_list")
//    Observable<RequestBase> getBusinessCategory();
    Observable<BaseResponse> getBusinessCategory(@Body Map var);

    /**
     * 获取外部商家的分类下的店铺
     */
    @POST("shop/h3/get_h3shop_list")
//    Observable<PostGetBussinessEntity> getBusinessList();
    Observable<BaseResponse> getBusinessList(@Body Map var);

    /**
     * 获取外部商家的商品分类
     */
    @POST("shop/h3/get_h3goodstypecategray_list")
//    Observable<PostGetGoodsCategoryEntity> getGoodsCategory( );
    Observable<BaseResponse> getGoodsCategory( @Body Map var);

    /**
     * 获取外部商家的商品列表
     */
    @POST("shop/h3/get_h3goods_list")
//    Observable<PostGetGoodsEntity> getGoodsList( );
    Observable<BaseResponse> getGoodsList(@Body Map var);
    /**
     * 获取单个外部商家信息
     */
    @POST("shop/h3/get_h3shop_first")
//    Observable<PostGetBusinessDetailEntity> getBusinessDetail();
    Observable<BaseResponse> getBusinessDetail(@Body Map var);
    /**
     * 外部商家下单
     */
    @POST("shop/h3/place_an_order")
//    Observable<PostBusinessOrderEntity> commitBusinessOrder();
    Observable<BaseResponse> commitBusinessOrder(@Body RequestBody body);

//    Observable<BaseResponse> commitBusinessOrder(@Body  Map var);




}
