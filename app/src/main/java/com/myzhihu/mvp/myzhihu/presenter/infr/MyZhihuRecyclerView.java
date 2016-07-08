package com.myzhihu.mvp.myzhihu.presenter.infr;

import com.myzhihu.mvp.myzhihu.model.entity.Feed;

/**
 * Created by CXG on 2016/6/30.
 */
public interface MyZhihuRecyclerView {

    void loadingNew(Feed feed);

    void loadingPast(Feed feed,String data);

    void showLoading();

    void showError(int layoutId);

    void onLoadingNewComplete();

    void onLoadingPastComplete();
}
