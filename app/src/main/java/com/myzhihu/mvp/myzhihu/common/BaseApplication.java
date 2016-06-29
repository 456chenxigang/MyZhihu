package com.myzhihu.mvp.myzhihu.common;

import android.app.Application;

import com.myzhihu.mvp.myzhihu.common.util.ScreenUtil;
import com.myzhihu.mvp.myzhihu.common.util.StrictModeUtil;

/**
 * Created by CXG on 2016/6/27.
 * Application是一种单例模式，可以共享一些全局变量
 * 需要在配置文件中指定name属性
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    public static BaseApplication get(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        instance = (BaseApplication) getApplicationContext();

        if(AppConfig.DEBUG){
            StrictModeUtil.startStrictMode();
        }

        ScreenUtil.GetInfo(instance);

    }
}
