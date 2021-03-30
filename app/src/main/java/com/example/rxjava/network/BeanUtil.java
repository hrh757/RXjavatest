package com.example.rxjava.network;

import com.example.rxjava.common.ParameterizedTypeImpl;
import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.adapter.rxjava2.Result;

public class BeanUtil {

    public <T> Result<T>  LoadBean(Class<T> clazz, Type type, BaseResponse baseResponse){
//       type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            Type ty = new ParameterizedTypeImpl(clazz, new Type[]{types[0]});
            Result<T> data= new Gson().fromJson(baseResponse.toString(),ty);
            return data;
        }else {
            Type pe = new ParameterizedTypeImpl(Result.class, new Class[]{clazz});
//            bean1 = new Gson().fromJson(new Gson().toJson(baseResponse.getData()),  new TypeToken<T>() {}.getType());
            return new Gson().fromJson(new Gson().toJson(baseResponse.getData()), pe);
        }







    }
}
