package com.myzhihu.mvp.myzhihu.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.myzhihu.mvp.myzhihu.R;
import com.myzhihu.mvp.myzhihu.common.util.ScreenUtil;
import com.myzhihu.mvp.myzhihu.model.entity.StartImage;
import com.myzhihu.mvp.myzhihu.presenter.impl.SplashImpl;
import com.myzhihu.mvp.myzhihu.presenter.infr.SplashImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements SplashImageView {


    @Bind(R.id.splashBackground)
    ImageView splashBackground;
    @Bind(R.id.bottomTitle)
    TextView bottomTitle;

    private SplashImpl splash;
    private static final int GO_MAIN = 1000;
    private static final int SPLASH_TIME = 3000;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == GO_MAIN) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
                overridePendingTransition(0,0);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        splash = new SplashImpl();
        splash.attachView(this);
        splash.getSplashImage(ScreenUtil.getImageDensity());
    }


    @Override
    public void showStartImage(StartImage startImage) {
        bottomTitle.setText(startImage.getText());
        Glide.with(this)
                .load(startImage.getImg())
                .dontAnimate()//直接显示图片而没有任何淡入淡出效果
                .placeholder(R.drawable.splash)//流式占位符，用来事先显示一个图片
                .error(R.drawable.splash)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)//缓存变换后的资源
                .into(splashBackground);
        onNextStep();
    }

    @Override
    public void onNextStep() {
        handler.sendEmptyMessageDelayed(GO_MAIN, SPLASH_TIME);
    }

    @Override
    public void onErrorShowStartImage() {
        Log.e("onErrorShowStartImage", "---------------");
        bottomTitle.setText("my zhihu");
        Glide.with(this)
                .load(R.drawable.splash)
                .placeholder(R.drawable.splash)
                .error(R.drawable.splash)
                .into(splashBackground);
        onNextStep();
    }
}
