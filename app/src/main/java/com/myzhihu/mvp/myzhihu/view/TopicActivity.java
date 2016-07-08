package com.myzhihu.mvp.myzhihu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.myzhihu.mvp.myzhihu.R;
import com.myzhihu.mvp.myzhihu.model.entity.TopicDetail;
import com.myzhihu.mvp.myzhihu.presenter.adapter.TopicAdapter;
import com.myzhihu.mvp.myzhihu.presenter.impl.FetchTopicImpl;
import com.myzhihu.mvp.myzhihu.presenter.infr.RecyclerOnItemClickListener;
import com.myzhihu.mvp.myzhihu.presenter.infr.TopicRecyclerView;
import com.myzhihu.mvp.myzhihu.presenter.infr.TopicScrollListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public  class TopicActivity extends BaseActivity implements TopicRecyclerView, RecyclerOnItemClickListener {

    @Bind(R.id.img_detail)
    ImageView imgDetail;
    @Bind(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @Bind(R.id.tv_source_detail)
    TextView tvSourceDetail;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.topic_item)
    View topic_item;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    private TopicAdapter topicAdapter;
    private FetchTopicImpl fetchTopic;
    private LinearLayoutManager linearLayoutManager;

    private String topicId, topicType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        ButterKnife.bind(this);

        topicId = getIntent().getStringExtra("id");
        topicType = getIntent().getStringExtra("topic");

        toolbar.setTitle(topicType);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        toolbar.setNavigationOnClickListener(new NavigationView.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(android.R.color.transparent));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
            }
        });

        initData();
    }

    private void initData() {

        topicAdapter = new TopicAdapter(this, new TopicDetail());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(topicAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchTopic.loadingNew(topicId);
            }
        });
        //添加一个滑动监听
        recyclerView.addOnScrollListener(new TopicScrollListener(recyclerView, linearLayoutManager) {
            @Override
            public void onLoadMore(String latestId) {
                Log.e("onLoadMore", latestId);
                fetchTopic.loadingPast(topicId, latestId);
            }
        });

        topicAdapter.setOnItemClickListener(this);
        fetchTopic = new FetchTopicImpl();
        fetchTopic.attachView(this);

        fetchTopic.loadingNew(topicId);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void showSnackBar(String str) {
        Snackbar.make(coordinatorLayout, str, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void OnItemClickListener(View v, Object obj) {
        if (obj instanceof String){
            String id = (String) obj;
            if (!TextUtils.isEmpty(id)){
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        }
    }

    @Override
    public void loadingNew(TopicDetail detail) {
        Glide.with(this)
                .load(detail.getImage())
                .placeholder(R.drawable.place)
                .error(R.drawable.place)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imgDetail);
        tvDetailTitle.setText(detail.getDescription());
        tvSourceDetail.setText(detail.getImageSource());
        topicAdapter.loadingNewTopic(detail);
    }

    @Override
    public void loadingPast(TopicDetail detail) {
        topicAdapter.loadingPastTopic(detail);
    }

    @Override
    public void showError(int layoutId) {

    }

    @Override
    public void onLoadingNewComplete() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onLoadingPastComplete() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}