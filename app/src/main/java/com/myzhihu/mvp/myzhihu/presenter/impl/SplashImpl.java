package com.myzhihu.mvp.myzhihu.presenter.impl;

import com.myzhihu.mvp.myzhihu.model.entity.StartImage;
import com.myzhihu.mvp.myzhihu.presenter.infr.SplashImageView;
import com.myzhihu.mvp.myzhihu.presenter.usercase.SplashCase;

import rx.Subscriber;

/**
 * Created by CXG on 2016/6/27.
 */
public class SplashImpl implements SplashPresenter<SplashImageView>{

    Subscriber<StartImage> startImageSubscribe;
    SplashImageView splashImageView;
    SplashCase splashCase;
    @Override
    public void getSplashImage(String density) {

        startImageSubscribe = new Subscriber<StartImage>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                splashImageView.onErrorShowStartImage();
            }

            @Override
            public void onNext(StartImage startImage) {
                splashImageView.showStartImage(startImage);
            }
        };

        splashCase.subscribe(startImageSubscribe,density);
    }

    @Override
    public void attachView(SplashImageView view) {
        splashImageView = view;
        splashCase = new SplashCase();
    }

    @Override
    public void detachView(SplashImageView view) {
        splashCase.unSubscribe();
    }
}
