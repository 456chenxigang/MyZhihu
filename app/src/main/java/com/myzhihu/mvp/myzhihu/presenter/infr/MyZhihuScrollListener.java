package com.myzhihu.mvp.myzhihu.presenter.infr;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.myzhihu.mvp.myzhihu.presenter.adapter.MyZhihuAdapter;

/**
 * Created by CXG on 2016/6/30.
 */
public abstract class MyZhihuScrollListener extends RecyclerView.OnScrollListener{

    private int previousTotal = 0;
    private boolean loading = true;
    private int firstVisibleItem,totalItemCount,visibleItemCount;
    private int currentPage = 0;

    private LinearLayoutManager layoutManager;
    private MyZhihuAdapter adapter;

    public MyZhihuScrollListener(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager){
        this.adapter = (MyZhihuAdapter) recyclerView.getAdapter();
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
            onLoadMore(currentPage++);
            loading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);
}
