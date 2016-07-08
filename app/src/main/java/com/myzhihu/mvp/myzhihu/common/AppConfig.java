package com.myzhihu.mvp.myzhihu.common;

/**
 * Created by CXG on 2016/6/27.
 */
public class AppConfig {

    public static final Boolean DEBUG = true;

    public static final String BASE_URL = "http://news-at.zhihu.com/api/";

    //以下api来自http://gank.io/api

    //分类数据:  http://gank.io/api/data/数据类型/请求个数/第几页
    //数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
    //example:  http://gank.io/api/data/Android/10/1
    public static final String GanHuo_URL = "http://gank.io/api/data/";

    public static final String CACHE_DIR_NAME = "MyCache_file";

    public static final long CACHE_MAX_SIZE = 20 * 1024 * 1024;

    public static final int CACHE_TIME_DEFAULT = 60 * 60 * 24 * 28;

    public static final int CACHE_TIME_NETWORK_UNAVAIABLE = 60 * 60 * 24 * 28;

    public static final int CACHE_TIME_NETWORK_AVAIABLE = 60;
}
