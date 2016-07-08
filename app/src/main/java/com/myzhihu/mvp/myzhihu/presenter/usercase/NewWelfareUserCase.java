package com.myzhihu.mvp.myzhihu.presenter.usercase;

import com.myzhihu.mvp.myzhihu.model.entity.WelfareImages;
import com.myzhihu.mvp.myzhihu.model.service.ServiceRest;

import rx.Observable;

/**
 * Created by CXG on 2016/7/6.
 */
public class NewWelfareUserCase extends UserCase<WelfareImages,String>{
    @Override
    protected Observable<WelfareImages> interactor(String params) {
        return ServiceRest.getInstance().getNewWelfareImages(params);
    }
}
