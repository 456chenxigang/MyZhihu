package com.myzhihu.mvp.myzhihu.presenter.impl;

/**
 * Created by CXG on 2016/7/1.
 */
public interface StoryDetailPresenter<T> extends Presenter<T>{

    void getExtraInfo(String id);

    void getDetailStory(String id);
}
