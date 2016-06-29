package com.myzhihu.mvp.myzhihu.presenter.usercase;

import com.myzhihu.mvp.myzhihu.model.entity.StartImage;
import com.myzhihu.mvp.myzhihu.model.service.ServiceRest;

import rx.Observable;

/**
 * Created by CXG on 2016/6/27.
 */
public class SplashCase extends UserCase<StartImage,String> {
    @Override
    protected Observable<StartImage> interactor(String params) {

        return ServiceRest.getInstance().getStartImage(params);
    }
}
