package com.myzhihu.mvp.myzhihu.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.myzhihu.mvp.myzhihu.R;
import com.myzhihu.mvp.myzhihu.common.util.WebUtils;
import com.myzhihu.mvp.myzhihu.model.entity.StoryDetail;
import com.myzhihu.mvp.myzhihu.model.entity.StoryExtraInfo;
import com.myzhihu.mvp.myzhihu.presenter.impl.StoryDetailImpl;
import com.myzhihu.mvp.myzhihu.presenter.infr.StoryDetailView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements StoryDetailView {

    @Bind(R.id.img_detail)
    ImageView imgDetail;
    @Bind(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @Bind(R.id.tv_source_detail)
    TextView tvSourceDetail;
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.scrollView)
    NestedScrollView scrollView;
    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.detail_item)
    View detail_item;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    private String newsId;
    private StoryDetailImpl storyDetailImpl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        toolbar.setNavigationOnClickListener(new NavigationView.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        newsId = getIntent().getStringExtra("id");

        initWebview();

        initData();
    }

    private void initWebview() {

        final WebSettings settings = webview.getSettings();
        if (Build.VERSION.SDK_INT >= 19) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
        settings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (!settings.getLoadsImagesAutomatically()) {
                    settings.setLoadsImagesAutomatically(true);
                }
            }
        });
    }

    private void initData() {

        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(android.R.color.transparent));

        storyDetailImpl = new StoryDetailImpl();
        storyDetailImpl.attachView(this);
        showProgressBar(true);
        storyDetailImpl.getDetailStory(newsId);
        storyDetailImpl.getExtraInfo(newsId);
    }

    private void showProgressBar(boolean isShow) {
        //detail_item.setVisibility(isShow ? View.GONE : View.VISIBLE);
        floatingActionButton.setVisibility(isShow ? View.GONE : View.VISIBLE);
        progressBar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.about_action:
                    startActivity(new Intent(getApplication(),AboutActivity.class));
                    break;
                case R.id.settings_action:
                    startActivity(new Intent(getApplication(),SettingActivity.class));
                    break;
                case R.id.search_icon:
                    showSnackBar("无需查找");
                    break;
            }
            return false;
        }
    };

    private void showSnackBar(String str) {
        Snackbar.make(coordinatorLayout, str, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showStoryDetail(StoryDetail detail) {
        tvDetailTitle.setText(detail.getTitle());
        tvSourceDetail.setText(detail.getImageSource());
        Glide.with(this)
                .load(detail.getImage())
                .placeholder(R.drawable.place)
                .error(R.drawable.toolbar_place)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imgDetail);
        bindWebview(detail);
        showProgressBar(false);
    }

    private void bindWebview(StoryDetail detail) {
        if (TextUtils.isEmpty(detail.getBody())) {
            webview.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
            webview.loadUrl(detail.getShareUrl());
        } else {
            String data = WebUtils.BuildHtmlWithCss(detail.getBody(), detail.getCss(), false);
            webview.loadDataWithBaseURL(null, data, WebUtils.MIME_TYPE, WebUtils.ENCODING, null);
            if (TextUtils.isEmpty(detail.getImage())) {

                //detail_item.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void showStoryExtraInfo(StoryExtraInfo extraInfo) {

    }
}
