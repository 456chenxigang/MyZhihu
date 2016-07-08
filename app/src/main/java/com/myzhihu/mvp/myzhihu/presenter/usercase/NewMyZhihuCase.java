package com.myzhihu.mvp.myzhihu.presenter.usercase;

import com.myzhihu.mvp.myzhihu.model.entity.Feed;
import com.myzhihu.mvp.myzhihu.model.service.ServiceRest;

import rx.Observable;

/**
 * Created by CXG on 2016/6/30.
 */
public class NewMyZhihuCase extends UserCase<Feed,String>{
    @Override
    protected Observable<Feed> interactor(String params) {
        return ServiceRest.getInstance().featchLatestMyZhihu();
    }
}
