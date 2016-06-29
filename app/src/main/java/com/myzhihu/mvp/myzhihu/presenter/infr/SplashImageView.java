package com.myzhihu.mvp.myzhihu.presenter.infr;

import com.myzhihu.mvp.myzhihu.model.entity.StartImage;

/**
 * Created by CXG on 2016/6/27.
 */
public interface SplashImageView {

    void showStartImage(StartImage startImage);

    void onNextStep();

    void onErrorShowStartImage();
}
