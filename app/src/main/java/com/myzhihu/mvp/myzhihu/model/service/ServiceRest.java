package com.myzhihu.mvp.myzhihu.model.service;

import android.text.TextUtils;
import android.util.Log;

import com.myzhihu.mvp.myzhihu.common.AppConfig;
import com.myzhihu.mvp.myzhihu.common.BaseApplication;
import com.myzhihu.mvp.myzhihu.common.util.NetworkUtil;
import com.myzhihu.mvp.myzhihu.model.entity.Feed;
import com.myzhihu.mvp.myzhihu.model.entity.StartImage;
import com.myzhihu.mvp.myzhihu.model.entity.StoryDetail;
import com.myzhihu.mvp.myzhihu.model.entity.StoryExtraInfo;
import com.myzhihu.mvp.myzhihu.model.entity.TopicDetail;
import com.myzhihu.mvp.myzhihu.model.entity.WelfareImages;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;

/**
 * Created by CXG on 2016/6/27.
 */
public class ServiceRest {

    private ServiceApi cachedServiceApi;
    private ServiceApi RtcServiceApi;
    private ServiceApi GanHuoServiceApi;

    private static String TAG = "ServiceRest";

    public ServiceRest(){

    }
    public static ServiceRest getInstance(){
        return SingletonHolder.instance;
    }
    private static class SingletonHolder{
        private static ServiceRest instance = new ServiceRest();
    }

    public static class Interceptor2 implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtil.isNetAvailable(BaseApplication.get())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Log.i(TAG, "no network");
            }

            Response response = chain.proceed(request);

            if (NetworkUtil.isNetAvailable(BaseApplication.get())) {
                int maxAge = AppConfig.CACHE_TIME_NETWORK_AVAIABLE; // 有网络时 设置缓存超时时间0个小时
                Log.i(TAG, "has network maxAge="+maxAge);
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                Log.i(TAG, "network error");
                int maxStale = AppConfig.CACHE_TIME_NETWORK_UNAVAIABLE; // 无网络时，设置超时为4周
                Log.i(TAG, "has maxStale="+maxStale);
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
                Log.i(TAG, "response build maxStale="+maxStale);
            }
            return response;
        }
    }

    public static class Interceptor1 implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            Log.i(TAG,"Request = "+request);
            Response response = chain.proceed(request);
            Log.i(TAG,"Response = "+response);

            String cacheControl = request.cacheControl().toString();
            if(TextUtils.isEmpty(cacheControl)){
                cacheControl = "public, max-age="+AppConfig.CACHE_TIME_DEFAULT;
            }
            return response.newBuilder()
                    .header("Cache-Control",cacheControl)
                    .removeHeader("Pragma")
                    .build();

        }
    }

    private OkHttpClient createCacheClient(){
        File httpCacheDirectory = new File(BaseApplication.get().getCacheDir(),AppConfig.CACHE_DIR_NAME);
        Cache cache = new Cache(httpCacheDirectory,AppConfig.CACHE_MAX_SIZE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor1()) //添加一个拦截器
                .cache(cache)
                .build();
        return okHttpClient;

    }

    private OkHttpClient createRtcClient(){
        File httpCacheDirectory = new File(BaseApplication.get().getCacheDir(),AppConfig.CACHE_DIR_NAME);
        Cache cache = new Cache(httpCacheDirectory,AppConfig.CACHE_MAX_SIZE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor2()) //添加一个拦截器
                .cache(cache)
                .build();
        return okHttpClient;

    }

    private ServiceApi cachedService(){
        if(cachedServiceApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(createCacheClient())
                    .baseUrl(AppConfig.BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //使接口以RXjava方式工作
                    .build();
            cachedServiceApi = retrofit.create(ServiceApi.class);
        }
        return cachedServiceApi;
    }

    private ServiceApi RtcService(){
        if(cachedServiceApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(createCacheClient())
                    .baseUrl(AppConfig.BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //使接口以RXjava方式工作
                    .build();
            cachedServiceApi = retrofit.create(ServiceApi.class);
        }
        return cachedServiceApi;
    }

    private ServiceApi GanHuoService(){
        if(GanHuoServiceApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(createCacheClient())
                    .baseUrl(AppConfig.GanHuo_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //使接口以RXjava方式工作
                    .build();
            GanHuoServiceApi = retrofit.create(ServiceApi.class);
        }
        return GanHuoServiceApi;
    }

    public Observable<StartImage> getStartImage(String density){
        return cachedService().getStartImage(density);
    }

    public Observable<Feed> featchLatestMyZhihu(){
        return cachedService().fetchLatestMyZhihu();
    }

    public Observable<Feed> featchPastMyZhihu(String date){
        return cachedService().fetchPastMyZhihu(date);
    }

    public Observable<StoryDetail> getStoryDetail(String id){
        return cachedService().getDetailStory(id);
    }

    public Observable<StoryExtraInfo> getStoryExtroInfo(String id){
        return RtcService().getDetailExtraInfo(id);
    }

    public Observable<TopicDetail> getTopicDetail(String topicId){
        return  cachedService().getTopicDetail(topicId);
    }

    public Observable<TopicDetail> getOldTopicDetail(String topicId,String latestId){
        return  cachedService().getPastTopic(topicId,latestId);
    }

    public Observable<WelfareImages> getNewWelfareImages(String id){
        return GanHuoService().getNewWelfareImages(id);
    }

    public Observable<WelfareImages> getPastWelfareImages(String id){
        return GanHuoService().getPastWelfareImages(id);
    }
}
