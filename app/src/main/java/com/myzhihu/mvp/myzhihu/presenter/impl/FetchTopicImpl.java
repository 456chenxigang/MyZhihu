package com.myzhihu.mvp.myzhihu.presenter.impl;

import com.myzhihu.mvp.myzhihu.model.entity.TopicDetail;
import com.myzhihu.mvp.myzhihu.presenter.infr.TopicRecyclerView;
import com.myzhihu.mvp.myzhihu.presenter.usercase.NewTopicUserCase;
import com.myzhihu.mvp.myzhihu.presenter.usercase.OldTopicUserCase;

import rx.Subscriber;

/**
 * Created by CXG on 2016/7/5.
 */
public class FetchTopicImpl implements TopicPresenter<TopicRecyclerView> {

    OldTopicUserCase oldTopicUserCase;
    NewTopicUserCase newTopicUserCase;
    Subscriber<TopicDetail> oldSubscriber;
    Subscriber<TopicDetail> newSubscriber;

    TopicRecyclerView topicRecyclerView;

    @Override
    public void loadingNew(String topicId) {

        newSubscriber = new Subscriber<TopicDetail>() {
            @Override
            public void onCompleted() {
                topicRecyclerView.onLoadingNewComplete();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TopicDetail detail) {
                topicRecyclerView.loadingNew(detail);
            }
        };
        newTopicUserCase.subscribe(newSubscriber,topicId);
    }

    @Override
    public void loadingPast(String topic, String lastId) {

        oldSubscriber = new Subscriber<TopicDetail>() {
            @Override
            public void onCompleted() {
                topicRecyclerView.onLoadingPastComplete();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TopicDetail detail) {
                topicRecyclerView.loadingPast(detail);
            }
        };
        oldTopicUserCase.subscribe(oldSubscriber,new String[]{topic,lastId});
    }

    @Override
    public void attachView(TopicRecyclerView view) {
        this.topicRecyclerView = view;
        newTopicUserCase = new NewTopicUserCase();
        oldTopicUserCase = new OldTopicUserCase();
    }

    @Override
    public void detachView(TopicRecyclerView view) {
        newTopicUserCase.unSubscribe();
        oldTopicUserCase.unSubscribe();
    }
}
