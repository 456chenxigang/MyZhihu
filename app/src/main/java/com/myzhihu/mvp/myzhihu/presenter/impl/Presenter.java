package com.myzhihu.mvp.myzhihu.presenter.impl;

/**
 * Created by CXG on 2016/6/27.
 */
public interface Presenter<T> {

    void attachView(T view);

    void detachView(T view);
}
