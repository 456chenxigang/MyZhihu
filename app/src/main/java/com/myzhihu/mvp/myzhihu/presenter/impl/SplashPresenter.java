package com.myzhihu.mvp.myzhihu.presenter.impl;

/**
 * Created by CXG on 2016/6/27.
 */
public interface SplashPresenter<T> extends Presenter<T> {

    void getSplashImage(String density);
}
