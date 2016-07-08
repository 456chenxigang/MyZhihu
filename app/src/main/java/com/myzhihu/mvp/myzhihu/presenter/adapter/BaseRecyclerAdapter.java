package com.myzhihu.mvp.myzhihu.presenter.adapter;

import android.support.v7.widget.RecyclerView;

import com.myzhihu.mvp.myzhihu.presenter.infr.RecyclerOnItemClickListener;

/**
 * Created by CXG on 2016/6/29.
 */
public abstract  class BaseRecyclerAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T>{
    protected RecyclerOnItemClickListener listener;

    public abstract void   setOnItemClickListener(RecyclerOnItemClickListener listener);
}
