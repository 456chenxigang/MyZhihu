package com.myzhihu.mvp.myzhihu.model.service;

import android.text.TextUtils;
import android.util.Log;

import com.myzhihu.mvp.myzhihu.common.AppConfig;
import com.myzhihu.mvp.myzhihu.common.BaseApplication;
import com.myzhihu.mvp.myzhihu.model.entity.StartImage;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
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
            return null;
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

    public Observable<StartImage> getStartImage(String density){
        return cachedService().getStartImage(density);
    }

}
