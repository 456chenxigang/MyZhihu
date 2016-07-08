package com.myzhihu.mvp.myzhihu.presenter.infr;

import com.myzhihu.mvp.myzhihu.model.entity.TopicDetail;

/**
 * Created by CXG on 2016/7/5.
 */
public interface TopicRecyclerView {

    void loadingNew(TopicDetail detail);

    void loadingPast(TopicDetail detail);

    void showError(int layoutId);

    void onLoadingNewComplete();

    void onLoadingPastComplete();
}
