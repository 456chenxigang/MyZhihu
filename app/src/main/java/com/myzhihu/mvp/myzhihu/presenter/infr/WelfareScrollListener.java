package com.myzhihu.mvp.myzhihu.presenter.infr;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.myzhihu.mvp.myzhihu.presenter.adapter.StaggeredAdapter;

/**
 * Created by CXG on 2016/7/6.
 */
public abstract class WelfareScrollListener extends RecyclerView.OnScrollListener{

        private int previousTotal = 0;
        private boolean loading = true;
        private int totalItemCount;
        private int visibleItemCount;

        private String TAG = "WelfareScrollListener";
        private StaggeredGridLayoutManager layoutManager;
        private StaggeredAdapter adapter;

public WelfareScrollListener(RecyclerView recyclerView, StaggeredGridLayoutManager linearLayoutManager){
        this.adapter = (StaggeredAdapter) recyclerView.getAdapter();
        this.layoutManager = linearLayoutManager;
        }
@Override
public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = layoutManager.getItemCount();

        int[] visibleItems = layoutManager.findLastVisibleItemPositions(null);
        int lastitem = Math.max(visibleItems[0],visibleItems[1]);
        Log.d(TAG,"visibleItems =" + visibleItems);
        Log.d(TAG,"lastitem =" + lastitem);
        Log.d(TAG,"adapter.getItemCount() =" + adapter.getItemCount());
        if (loading){
                if (totalItemCount > previousTotal){
                        Log.d(TAG,"loading == false");
                        loading = false;
                        previousTotal = totalItemCount;
                }
        }
        //dy>0表示向下滑动
        if (dy > 0 && lastitem > adapter.getItemCount() - 4 && !loading) {
                Log.d(TAG,"will loadNewFeeds");
                onLoadMore(adapter.getLatestId());
        }
}

public abstract void onLoadMore(String latestId);
}
