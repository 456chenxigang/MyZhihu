package com.myzhihu.mvp.myzhihu.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myzhihu.mvp.myzhihu.R;
import com.myzhihu.mvp.myzhihu.model.entity.Feed;
import com.myzhihu.mvp.myzhihu.presenter.adapter.MyZhihuAdapter;
import com.myzhihu.mvp.myzhihu.presenter.impl.FetchFeedImpl;
import com.myzhihu.mvp.myzhihu.presenter.infr.MyZhihuRecyclerView;
import com.myzhihu.mvp.myzhihu.presenter.infr.MyZhihuScrollListener;
import com.myzhihu.mvp.myzhihu.presenter.infr.RecyclerOnItemClickListener;
import com.myzhihu.mvp.myzhihu.view.DetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyZhiHuFragment extends BaseFragment implements RecyclerOnItemClickListener,MyZhihuRecyclerView{

    @Bind(R.id.my_zhihu_recycler)
    RecyclerView myZhihuRecycler;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private MyZhihuAdapter myZhihuAdapter;
    private FetchFeedImpl fetchFeed;
    private LinearLayoutManager linearLayoutManager;

    public MyZhiHuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_zhi, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    private void initData() {
        myZhihuAdapter = new MyZhihuAdapter(getActivity(),new Feed());
        myZhihuRecycler.setHasFixedSize(true);
        myZhihuRecycler.setAdapter(myZhihuAdapter);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        myZhihuRecycler.setLayoutManager(linearLayoutManager);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchFeed.loadingNew();
            }
        });
        //添加一个滑动监听
        myZhihuRecycler.addOnScrollListener(new MyZhihuScrollListener(myZhihuRecycler,linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                Log.e("onLoadMore",currentPage+"");
                fetchFeed.loadingPast(currentPage);
            }
        });

        myZhihuAdapter.setOnItemClickListener(this);
        fetchFeed = new FetchFeedImpl();
        fetchFeed.attachView(this);

        testRxjava();
    }

    private void testRxjava() {
        fetchFeed.loadingNew();
    }

    @Override
    protected int setLayoutResourceID() {
        return 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void loadingNew(Feed feed) {
        myZhihuAdapter.loadingNewFeed(feed);
    }

    @Override
    public void loadingPast(Feed feed, String date) {
        myZhihuAdapter.loadingPastFeed(feed,date);
    }

    @Override
    public void showLoading() {

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

    }

    @Override
    public void OnItemClickListener(View v, Object obj) {
        //Snackbar.make(swipeRefreshLayout,"OnItemClickListener",Snackbar.LENGTH_LONG).show();
        if (obj instanceof String){
            String newsId = (String) obj;
            if(!TextUtils.isEmpty(newsId)){
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("id",newsId);
                startActivity(intent);
            }
        }
    }

}
