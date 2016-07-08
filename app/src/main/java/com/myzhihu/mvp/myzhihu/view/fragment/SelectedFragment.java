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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myzhihu.mvp.myzhihu.R;
import com.myzhihu.mvp.myzhihu.model.entity.TopicDetail;
import com.myzhihu.mvp.myzhihu.presenter.adapter.TopicAdapter;
import com.myzhihu.mvp.myzhihu.presenter.impl.FetchTopicImpl;
import com.myzhihu.mvp.myzhihu.presenter.infr.RecyclerOnItemClickListener;
import com.myzhihu.mvp.myzhihu.presenter.infr.TopicRecyclerView;
import com.myzhihu.mvp.myzhihu.presenter.infr.TopicScrollListener;
import com.myzhihu.mvp.myzhihu.view.DetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectedFragment extends BaseFragment implements TopicRecyclerView, RecyclerOnItemClickListener {


    @Bind(R.id.my_selected_recycler)
    RecyclerView recyclerView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private TopicAdapter topicAdapter;
    private FetchTopicImpl fetchTopic;
    private LinearLayoutManager linearLayoutManager;
    private String topicId = "11";

    public SelectedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selected, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected int setLayoutResourceID() {
        return 0;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    private void initData() {
        topicAdapter = new TopicAdapter(getActivity(), new TopicDetail());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(topicAdapter);
        linearLayoutManager = new LinearLayoutManager(getActivity());
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void OnItemClickListener(View v, Object obj) {
        if (obj instanceof String){
            String id = (String) obj;
            if (!TextUtils.isEmpty(id)){
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        }
    }

    @Override
    public void loadingNew(TopicDetail detail) {
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
