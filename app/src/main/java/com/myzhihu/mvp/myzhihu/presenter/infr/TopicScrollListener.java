package com.myzhihu.mvp.myzhihu.presenter.infr;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.myzhihu.mvp.myzhihu.presenter.adapter.TopicAdapter;

/**
 * Created by CXG on 2016/7/6.
 */
public abstract class TopicScrollListener extends RecyclerView.OnScrollListener{

    private int previousTotal = 0;
    private boolean loading = true;
    private int firstVisibleItem,totalItemCount,visibleItemCount;

    private LinearLayoutManager layoutManager;
    private TopicAdapter adapter;

    public TopicScrollListener(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager){
        this.adapter = (TopicAdapter) recyclerView.getAdapter();
        this.layoutManager = linearLayoutManager;
    }
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = layoutManager.getItemCount();
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

        if (loading == true){
            if (totalItemCount > previousTotal){
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= firstVisibleItem){
            onLoadMore(adapter.getLatestId());
            loading = true;
        }
    }

    public abstract void onLoadMore(String latestId);
}
