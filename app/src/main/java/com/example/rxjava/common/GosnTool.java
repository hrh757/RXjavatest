package com.example.rxjava.common;

import com.google.gson.Gson;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import retrofit2.adapter.rxjava2.Result;

public class GosnTool {

    public static <T> Result<T> fromJsonObject(Reader reader, Class<T> clazz, Gson gson
    ) {
        Type type = new ParameterizedTypeImpl(Result.class, new Class[]{clazz});
        return gson.fromJson(reader, type);
    }



    public static <T> Result<List<T>> fromJsonArray(Reader reader, Class<T> clazz, Gson gson) {
        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(Result.class, new Type[]{listType});
        return gson.fromJson(reader, type);
    }
}
