package com.myzhihu.mvp.myzhihu.presenter.usercase;

import com.myzhihu.mvp.myzhihu.model.entity.StoryExtraInfo;
import com.myzhihu.mvp.myzhihu.model.service.ServiceRest;

import rx.Observable;

/**
 * Created by CXG on 2016/7/1.
 */
public class StoryExtraCase extends UserCase<StoryExtraInfo,String> {
    @Override
    protected Observable<StoryExtraInfo> interactor(String params) {
        return ServiceRest.getInstance().getStoryExtroInfo(params);
    }
}
