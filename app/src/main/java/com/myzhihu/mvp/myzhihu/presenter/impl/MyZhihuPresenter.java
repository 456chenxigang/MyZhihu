package com.myzhihu.mvp.myzhihu.presenter.impl;

/**
 * Created by CXG on 2016/6/30.
 */
public interface MyZhihuPresenter<T> extends Presenter<T>{

    void loadingNew();

    void loadingPast(int page);

    void showLoading();

    void showError(int layoutId);
}
