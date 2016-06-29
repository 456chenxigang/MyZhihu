package com.myzhihu.mvp.myzhihu.model.service;

import com.myzhihu.mvp.myzhihu.model.entity.StartImage;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by CXG on 2016/6/27.
 */
public interface ServiceApi {

    @GET("4/start-image/{density}")
    Observable<StartImage> getStartImage(@Path("density") String density); //返回的Observable用来执行请求的 Observable<StartImage>返回体中的数据
}
