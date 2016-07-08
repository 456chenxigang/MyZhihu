package com.myzhihu.mvp.myzhihu.presenter.usercase;

import com.myzhihu.mvp.myzhihu.model.entity.TopicDetail;
import com.myzhihu.mvp.myzhihu.model.service.ServiceRest;

import rx.Observable;

/**
 * Created by CXG on 2016/7/5.
 */
public class NewTopicUserCase extends UserCase<TopicDetail,String>{
    @Override
    protected Observable<TopicDetail> interactor(String params) {
        return ServiceRest.getInstance().getTopicDetail(params);
    }
}
