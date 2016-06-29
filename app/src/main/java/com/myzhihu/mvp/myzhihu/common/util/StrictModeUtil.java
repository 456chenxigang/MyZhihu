package com.myzhihu.mvp.myzhihu.common.util;

import android.os.Build;
import android.os.StrictMode;

/**
 * Created by CXG on 2016/6/27.
 * 1.开启严格模式（检测主线程中本地磁盘和网络读写等耗时的操作。严格模式会将应用的违例细节暴露给开发者方便优化与改善）
 * 2.查看结果，通过查看日志，终端下过滤StrictMode就能得到违例的具体stacktrace信息。
 * 3.详情：http://www.tuicool.com/articles/ueeM7b6
 */
public class StrictModeUtil {

    public static void startStrictMode(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD){

            startThreadStrictMode();

            startVmStrictMode();
        }
    }

    private static void startVmStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().detectDiskReads().permitDiskWrites().penaltyLog().build());
    }

    private static void startThreadStrictMode() {
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }
}
