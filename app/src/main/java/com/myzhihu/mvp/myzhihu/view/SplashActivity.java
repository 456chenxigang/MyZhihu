package com.myzhihu.mvp.myzhihu.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.myzhihu.mvp.myzhihu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.splashBackground)
    ImageView splashBackground;
    @BindView(R.id.bottomTitle)
    TextView bottomTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }
}
