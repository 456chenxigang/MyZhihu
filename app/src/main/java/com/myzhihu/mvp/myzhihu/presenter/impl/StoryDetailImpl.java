package com.myzhihu.mvp.myzhihu.presenter.impl;

import com.myzhihu.mvp.myzhihu.model.entity.StoryDetail;
import com.myzhihu.mvp.myzhihu.model.entity.StoryExtraInfo;
import com.myzhihu.mvp.myzhihu.presenter.infr.StoryDetailView;
import com.myzhihu.mvp.myzhihu.presenter.usercase.StoryDetailCase;
import com.myzhihu.mvp.myzhihu.presenter.usercase.StoryExtraCase;

import rx.Subscriber;

/**
 * Created by CXG on 2016/7/1.
 */
public class StoryDetailImpl implements StoryDetailPresenter<StoryDetailView>{

    private StoryDetailView storyDetailView;
    private StoryDetailCase storyDetailCase;
    private StoryExtraCase storyExtraCase;
    private Subscriber<StoryDetail> storyDetailSubscriber;
    private Subscriber<StoryExtraInfo> storyExtraInfoSubscriber;



    @Override
    public void getDetailStory(String id) {

        storyDetailSubscriber = new Subscriber<StoryDetail>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(StoryDetail detail) {
                storyDetailView.showStoryDetail(detail);
            }
        };
        storyDetailCase.subscribe(storyDetailSubscriber,id);
    }

    @Override
    public void getExtraInfo(String id) {

        storyExtraInfoSubscriber = new Subscriber<StoryExtraInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(StoryExtraInfo extraInfo) {
                storyDetailView.showStoryExtraInfo(extraInfo);
            }
        };
        storyExtraCase.subscribe(storyExtraInfoSubscriber,id);
    }

    @Override
    public void attachView(StoryDetailView view) {
        this.storyDetailView = view;
        storyDetailCase = new StoryDetailCase();
        storyExtraCase = new StoryExtraCase();
    }

    @Override
    public void detachView(StoryDetailView view) {
        storyExtraCase.unSubscribe();
        storyExtraCase.unSubscribe();
    }
}
