package com.myzhihu.mvp.myzhihu.presenter.usercase;

import com.myzhihu.mvp.myzhihu.model.entity.StoryDetail;
import com.myzhihu.mvp.myzhihu.model.service.ServiceRest;

import rx.Observable;

/**
 * Created by CXG on 2016/7/1.
 */
public class StoryDetailCase extends UserCase<StoryDetail,String> {
    @Override
    protected Observable<StoryDetail> interactor(String params) {
        return ServiceRest.getInstance().getStoryDetail(params);
    }
}
