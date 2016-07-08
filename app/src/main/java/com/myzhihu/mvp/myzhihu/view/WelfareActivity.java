package com.myzhihu.mvp.myzhihu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.myzhihu.mvp.myzhihu.R;
import com.myzhihu.mvp.myzhihu.model.entity.WelfareImages;
import com.myzhihu.mvp.myzhihu.presenter.adapter.StaggeredAdapter;
import com.myzhihu.mvp.myzhihu.presenter.impl.WelfareImpl;
import com.myzhihu.mvp.myzhihu.presenter.infr.RecyclerOnItemClickListener;
import com.myzhihu.mvp.myzhihu.presenter.infr.WelfareRecyclerView;
import com.myzhihu.mvp.myzhihu.presenter.infr.WelfareScrollListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelfareActivity extends BaseActivity implements RecyclerOnItemClickListener, WelfareRecyclerView {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private WelfareImpl welfareImpl;
    private StaggeredAdapter staggeredAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare);
        ButterKnife.bind(this);

        toolbar.setTitle("福利");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();

        initData();
    }

    private void initView() {

    }

    private void initData() {
        staggeredAdapter = new StaggeredAdapter(this,new WelfareImages());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(staggeredAdapter);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                welfareImpl.loadingNew("1");
            }
        });
        //添加一个滑动监听
        recyclerView.addOnScrollListener(new WelfareScrollListener(recyclerView,staggeredGridLayoutManager) {
            @Override
            public void onLoadMore(String latestId) {
                Log.e("Welfare onLoadMore",latestId);
                welfareImpl.loadingPast(latestId);
            }

        });

        staggeredAdapter.setOnItemClickListener(this);
        welfareImpl = new WelfareImpl();
        welfareImpl.attachView(this);

        welfareImpl.loadingNew("1");
    }

    @Override
    public void OnItemClickListener(View v, Object obj) {

            Intent intent = new Intent(this,LargeImageActivity.class);
            intent.putExtra("id",(Integer) obj);
            startActivity(intent);
    }

    @Override
    public void loadingNew(WelfareImages welfareImages) {
        staggeredAdapter.loadingNewImages(welfareImages);
    }

    @Override
    public void loadingPast(WelfareImages welfareImages) {
        staggeredAdapter.loadingPastImages(welfareImages);
    }

    @Override
    public void showError(int layoutId) {

    }

    @Override
    public void onLoadingNewComplete() {
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onLoadingPastComplete() {
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId())
            {
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
        Snackbar.make(toolbar, str, Snackbar.LENGTH_LONG).show();
    }
}
