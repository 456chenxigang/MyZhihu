package com.myzhihu.mvp.myzhihu.presenter.impl;

/**
 * Created by CXG on 2016/7/5.
 */
public interface TopicPresenter<T> extends Presenter<T>{

    void loadingNew(String topicId);

    void loadingPast(String topic,String lastId);
}
