package com.example.rxjava.Interface;



import com.example.rxjava.model.YCMvEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {




    /**
     * 获取随机歌曲列表
     */
//    @GET("api/Mv/GetRandomSong")
    @GET("Mv/GetRandomSong")
    Observable<YCMvEntity> getCategoryList(@Query("key_boss") String key_boss, @Query("limit") int limit);

    /**
     * 根据不同类别获取歌曲列表
     *
     * @param action    类别名称
     * @param pageIndex 页码索引
     * @param limit     每页条数
     * @return 歌曲列表集合
     */
//    @GET("api/Mv")
    @GET("Mv")
    Observable<YCMvEntity> getMv(@Query("action") String action, @Query("offset") int pageIndex, @Query("limit") int limit, @Query("key_boss") String key_boss);

    /**
     * 搜索歌曲
     *
     * @param action    搜索类型
     * @param keyword   关键字
     * @param pageIndex 页码索引
     * @param limit     每页数量
     * @return 歌曲列表集合
     */
//    @GET("api/Mv")
    @GET("Mv")
    Observable<YCMvEntity> searchMv(@Query("action") String action, @Query("keyword") String keyword, @Query("offset") int pageIndex, @Query("limit") int limit, @Query("key_boss") String key_boss);

    /**
     * 根据歌星搜索歌曲
     *
     * @param singerName
     * @param pageIndex
     * @param limit
     * @return
     */
    @GET("api/Mv")
    Observable<YCMvEntity> searchSongBySinger(@Query("action") String action, @Query("keyword") String singerName, @Query("offset") int pageIndex, @Query("limit") int limit);

    /**
     * 根据歌曲ID查找歌曲
     *
     * @param songId
     * @return
     */
    @GET("api/Mv/{songId}")
    Observable<YCMvEntity> fetchMvById(@Path("songId") String songId);

    /**
     * @param songName
     * @param singerName
     * @param pageIndex
     * @param limit
     * @return
     */
    @GET("api/Mv")
    Observable<YCMvEntity> searchVoiceMv(@Query("songName") String songName, @Query("singerName") String singerName, @Query("offset") int pageIndex, @Query("limit") int limit);

    /**
     * 下载歌曲
     *
     * @param songId
     * @return
     */
    @GET("/api/Mv/DownloadMv")
    Observable<YCMvEntity> downloadSong(@Query("songId") String songId);

    /**
     * 下载歌曲 返回参数 status 0:未下载 1下载中 2下载完成
     *
     * @param songIdlist
     * @return
     */
    @GET("/api/Mv/CheckMvDownload")
    Observable<YCMvEntity> checkMvDownload(@Query("list") List<String> songIdlist);
}
