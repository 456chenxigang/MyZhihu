package com.myzhihu.mvp.myzhihu.presenter.infr;

import com.myzhihu.mvp.myzhihu.model.entity.StoryDetail;
import com.myzhihu.mvp.myzhihu.model.entity.StoryExtraInfo;

/**
 * Created by CXG on 2016/7/1.
 */
public interface StoryDetailView {

    void showStoryDetail(StoryDetail detail);

    void showStoryExtraInfo(StoryExtraInfo extraInfo);

}
