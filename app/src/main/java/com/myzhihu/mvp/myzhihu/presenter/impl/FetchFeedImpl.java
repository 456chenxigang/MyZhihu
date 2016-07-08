package com.myzhihu.mvp.myzhihu.presenter.impl;

import com.myzhihu.mvp.myzhihu.common.util.TimeUtil;
import com.myzhihu.mvp.myzhihu.model.entity.Feed;
import com.myzhihu.mvp.myzhihu.presenter.infr.MyZhihuRecyclerView;
import com.myzhihu.mvp.myzhihu.presenter.usercase.NewMyZhihuCase;
import com.myzhihu.mvp.myzhihu.presenter.usercase.OldMyZhihuCase;

import rx.Subscriber;

/**
 * Created by CXG on 2016/6/30.
 */
public class FetchFeedImpl implements MyZhihuPresenter<MyZhihuRecyclerView>{

    Subscriber<Feed> loadNewSub;
    Subscriber<Feed> loadOldeSub;
    private MyZhihuRecyclerView recyclerView;
    private OldMyZhihuCase oldMyZhihuCase;
    private NewMyZhihuCase newMyZhihuCase;

    @Override
    public void loadingNew() {

        loadNewSub = new Subscriber<Feed>() {
            @Override
            public void onCompleted() {
                recyclerView.onLoadingNewComplete();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Feed feed) {
                recyclerView.loadingNew(feed);
            }
        };
        newMyZhihuCase.subscribe(loadNewSub,"");
    }

    @Override
    public void loadingPast(final int page) {
        loadOldeSub = new Subscriber<Feed>() {
            @Override
            public void onCompleted() {
                recyclerView.onLoadingPastComplete();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Feed feed) {
                FetchFeedImpl.this.recyclerView.loadingPast(feed, TimeUtil.getPastDateStringDisplay(page +1));
            }
        };
        oldMyZhihuCase.subscribe(loadOldeSub, TimeUtil.getPastDatetimeString(page));
    }

    @Override
    public void showLoading() {
        recyclerView.showLoading();
    }

    @Override
    public void showError(int layoutId) {
        recyclerView.showError(layoutId);
    }


    @Override
    public void attachView(MyZhihuRecyclerView view) {
        this.recyclerView = view;
        newMyZhihuCase = new NewMyZhihuCase();
        oldMyZhihuCase = new OldMyZhihuCase();
    }

    @Override
    public void detachView(MyZhihuRecyclerView view) {
        newMyZhihuCase.unSubscribe();
        oldMyZhihuCase.unSubscribe();
    }
}
