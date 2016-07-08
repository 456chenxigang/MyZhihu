package com.myzhihu.mvp.myzhihu.presenter.impl;

/**
 * Created by CXG on 2016/7/6.
 */
public interface WelfarePresenter<T> extends Presenter<T>{

    public void loadingNew(String id);

    public void loadingPast(String id);
}
