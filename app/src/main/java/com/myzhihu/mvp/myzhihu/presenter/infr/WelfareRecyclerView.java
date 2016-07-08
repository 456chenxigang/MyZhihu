package com.myzhihu.mvp.myzhihu.presenter.infr;

import com.myzhihu.mvp.myzhihu.model.entity.WelfareImages;

/**
 * Created by CXG on 2016/7/6.
 */
public interface WelfareRecyclerView {

    void loadingNew(WelfareImages welfareImages);

    void loadingPast(WelfareImages welfareImages);

    void showError(int layoutId);

    void onLoadingNewComplete();

    void onLoadingPastComplete();
}
