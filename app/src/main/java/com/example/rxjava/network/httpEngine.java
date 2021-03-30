package com.example.rxjava.network;

import com.example.rxjava.Interface.Api;
import com.example.rxjava.Util.DeviceManager;
import com.example.rxjava.common.Constants;
import com.example.rxjava.model.OrderGoodsData;
import com.example.rxjava.model.PostBusinessOrderEntity;
import com.google.gson.Gson;
import hrh.commonlib.commonlib.common.CommonConstants;
import hrh.commonlib.commonlib.util.CommonToolUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class httpEngine {

    private static Api mService = RetrofitServiceManager.getInstance().create(Api.class);
    private static BaseResponse baseResponse;
    private int type;

    private static <T> Observable<T> setSubscribe(Observable<T> observable, BaseObserver<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe((Observer<? super T>) observer);
        return observable;
    }

    public static Observable<BaseResponse> getCategoryList(int type, String kboss, String random, String mac, BaseObserver<BaseResponse> observer) {
//        RequestBody.create(MediaType.parse("application/json;charset=UTF-8")
        return setSubscribe(mService.categoryList(type,kboss,random,mac),observer);

    }
//    Observer<CategoryListEntity> observer
    public static void getCommodityList(int goodsType, int categoryId, BaseObserver<BaseResponse> observer) {
        RequestCommonParams params= new RequestCommonParams();
        TreeMap<String, Object> staticParams=params.getStaticParams();
        staticParams.put("type",goodsType);
        staticParams.put("category_id",categoryId);
        params.setStaticParams(staticParams);
        setSubscribe(mService.commodityList(params.getStaticParams()), observer);
    }



//    Observer<FetchPayStatusEntity> observer
    public static void  fetchPayStatus(String orderId, String type, BaseObserver<BaseResponse> observer) {
        RequestCommonParams params= new RequestCommonParams();
        TreeMap<String, Object> staticParams=params.getStaticParams();
        staticParams.put("id",orderId);
        staticParams.put("type",type);
        setSubscribe(mService.fetchPayStatus(staticParams), observer);
    }

    public static Observable<Object> confirmInBill(String orderId, BaseObserver observer) {
         return setSubscribe(mService.confirmInBill(), observer);
    }

//    Observer<CommitResultEntity> commitResultEntityObserver
    public static void commitOrder(ArrayList<OrderGoodsData> goodsList, BigDecimal totalPrice, int goodsType, BaseObserver observer) {
        RequestCommonParams params= new RequestCommonParams();
        TreeMap<String, Object> staticParams=params.getStaticParams();
        staticParams.put("goodsType",goodsType);
        staticParams.put("totalPrice",totalPrice);
        staticParams.put("goodsList","");
        setSubscribe(mService.commitOrder(staticParams), observer);
    }


    public static void GetBussinessCatagory( BaseObserver<BaseResponse> observer){
        RequestCommonParams params= new RequestCommonParams();
        TreeMap<String, Object> staticParams=params.getStaticParams();
        params.setStaticParams(staticParams);
        setSubscribe(mService.getBusinessCategory(staticParams), observer);
    }




    public static void getBussinessList(int category,BaseObserver<BaseResponse> observer){
        RequestCommonParams params= new RequestCommonParams();
        TreeMap<String, Object> staticParams=params.getStaticParams();
//        staticParams.put("id",category);
        staticParams.put("category",category);
        setSubscribe(mService.getBusinessList(staticParams),observer);
    }



    public static void getGoodsCategory(int shop_id,BaseObserver<BaseResponse> observer){

        RequestCommonParams params= new RequestCommonParams();
        TreeMap<String, Object> staticParams=params.getStaticParams();
        staticParams.put("shop_id",shop_id);
        setSubscribe(mService.getGoodsCategory(staticParams),observer);
    }



    public static void getGoodsList( int category , int shopId,BaseObserver<BaseResponse> observer){
        RequestCommonParams params= new RequestCommonParams();
        TreeMap<String, Object> staticParams=params.getStaticParams();
        staticParams.put("category",category);
        staticParams.put("shop_id",shopId);
        setSubscribe(mService.getGoodsList(staticParams),observer);
    }

        // map 转换 observable 对象
  /*  public static void getGoodsList( int category , int shopId,Observer<List<GoodsBean>> observer){
        BaseObserver<BaseResponse> obFirst = null;
        RequestCommonParams params= new RequestCommonParams();
        TreeMap<String, Object> staticParams=params.getStaticParams();
        staticParams.put("category",category);
        staticParams.put("shop_id",shopId);
        Observable<BaseResponse> ob=setSubscribe(mService.getGoodsList(staticParams),obFirst);
//        setSubscribe(mService.getGoodsList(staticParams),observer)
        ob.just(baseResponse).map(new Function<BaseResponse, List<GoodsBean>>() {
            @Override
            public List<GoodsBean> apply(BaseResponse baseResponse) {
                return  new Gson().fromJson(new Gson().toJson(baseResponse),  new TypeToken<BaseResponse>() {}.getType());
            }
        });
    }*/


    public static void getBusinessDetail(int shopId,BaseObserver<BaseResponse> observer){
        RequestCommonParams params= new RequestCommonParams();
        TreeMap<String, Object> staticParams=params.getStaticParams();
        staticParams.put("shop_id",shopId);
        setSubscribe(mService.getBusinessDetail(staticParams),observer);
    }

    public static void commitBusinessOrder(int shopId, int payMethod, ArrayList<PostBusinessOrderEntity.BusinessOrderGoodsData> list, BaseObserver<BaseResponse> observer){
        String[] s = CommonToolUtil.getKeyBoss(CommonConstants.REGISTER_DATA_ENTITY.stores.keyBoss);
        RequestCommonParams params= new RequestCommonParams();
        PostBusinessOrderEntity mEntity= new PostBusinessOrderEntity();
        mEntity.setDevice_mac(DeviceManager.getMac());
        mEntity.setGoods(list);
        mEntity.setKey_boss(s[0]);
        mEntity.setRandom(s[1]);
        mEntity.setMac(Constants.getMac());
        mEntity.setRoom_id(CommonConstants.REGISTER_DATA_ENTITY.room.id);
        mEntity.setShop_id(shopId);
        mEntity.setPay_method(payMethod);
//        setSubscribe(mService.commitBusinessOrder(RequestBody.create(MediaType.parse("Content-Type:application/json;charset=UTF-8"), paramObj.toString())),observer);
//        setSubscribe(mService.commitBusinessOrder(staticParams),observer);
//        Content-Type:
        setSubscribe(mService.commitBusinessOrder(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),  new Gson().toJson(mEntity))),observer);
    }
}
