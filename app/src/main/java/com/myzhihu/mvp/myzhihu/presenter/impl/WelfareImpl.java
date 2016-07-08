package com.myzhihu.mvp.myzhihu.presenter.impl;

import com.myzhihu.mvp.myzhihu.model.entity.WelfareImages;
import com.myzhihu.mvp.myzhihu.presenter.infr.WelfareRecyclerView;
import com.myzhihu.mvp.myzhihu.presenter.usercase.NewWelfareUserCase;
import com.myzhihu.mvp.myzhihu.presenter.usercase.OldWelfareUserCase;

import rx.Subscriber;

/**
 * Created by CXG on 2016/7/6.
 */
public class WelfareImpl implements WelfarePresenter<WelfareRecyclerView>{

    WelfareRecyclerView welfareRecyclerView;

    NewWelfareUserCase newWelfareUserCase;
    OldWelfareUserCase oldWelfareUserCase;

    Subscriber<WelfareImages> newSubscriber;
    Subscriber<WelfareImages> oldSubscriber;

    @Override
    public void loadingNew(String id) {
        newSubscriber = new Subscriber<WelfareImages>() {
            @Override
            public void onCompleted() {
                welfareRecyclerView.onLoadingNewComplete();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(WelfareImages welfareImages) {
                welfareRecyclerView.loadingNew(welfareImages);
            }
        };
        newWelfareUserCase.subscribe(newSubscriber,id);
    }

    @Override
    public void loadingPast(String id) {
        oldSubscriber = new Subscriber<WelfareImages>() {
            @Override
            public void onCompleted() {
                welfareRecyclerView.onLoadingPastComplete();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(WelfareImages welfareImages) {
                welfareRecyclerView.loadingPast(welfareImages);
            }
        };
        oldWelfareUserCase.subscribe(oldSubscriber,id);
    }

    @Override
    public void attachView(WelfareRecyclerView view) {
        this.welfareRecyclerView = view;
        newWelfareUserCase = new NewWelfareUserCase();
        oldWelfareUserCase = new OldWelfareUserCase();
    }

    @Override
    public void detachView(WelfareRecyclerView view) {
        newWelfareUserCase.unSubscribe();
        oldWelfareUserCase.unSubscribe();
    }
}
