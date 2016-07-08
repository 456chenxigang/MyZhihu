package com.myzhihu.mvp.myzhihu.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.myzhihu.mvp.myzhihu.R;
import com.myzhihu.mvp.myzhihu.model.entity.WelfareImages;
import com.myzhihu.mvp.myzhihu.presenter.infr.RecyclerOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CXG on 2016/7/6.
 */
public class StaggeredAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Integer> mHeights = new ArrayList<>();
    private WelfareImages welfareImages;
    public static List<WelfareImages.ResultsBean> resultsBean;
    public static final int TYPE_LIST = 0x02;
    private int latestId;

    public StaggeredAdapter(Context context, WelfareImages welfareImages){
        this.context = context;
        init(welfareImages);
    }

    private void init(WelfareImages welfareImages){
        if (resultsBean != null){
            resultsBean.clear();
        }
        this.welfareImages = welfareImages;
        if (this.welfareImages != null){
            this.resultsBean = welfareImages.getResults();
        }
        notifyDataSetChanged();
    }

    public void loadingNewImages(WelfareImages welfareImages){
        init(welfareImages);
    }

    public void loadingPastImages(WelfareImages welfareImages){
        List<WelfareImages.ResultsBean> tmpResultsBean = welfareImages.getResults();
        if (tmpResultsBean !=null && tmpResultsBean.size() > 0){
            this.resultsBean.addAll(tmpResultsBean);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_LIST;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LIST){
            View v = LayoutInflater.from(context).inflate(R.layout.staggered,parent,false);
            StaggeredViewHolder viewHolder = new StaggeredViewHolder(v);
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StaggeredViewHolder){
            bindStaggeredView((StaggeredViewHolder)holder,position);
        }
    }

    private void bindStaggeredView(final StaggeredViewHolder holder, final int position) {
        if (mHeights.size() <= position){
            mHeights.add((int) (180+Math.random()*300));
        }
        ViewGroup.LayoutParams layoutParams = holder.getView().getLayoutParams();
        layoutParams.height = mHeights.get(position);
        holder.getView().setLayoutParams(layoutParams);

        Glide.with(context)
                .load(resultsBean.get(position).getUrl())
                .crossFade()
                .error(R.drawable.toolbar_place)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
        //holder.getView().setTag(position);
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClickListener(holder.getView(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return welfareImages == null || welfareImages.getResults() == null || welfareImages.getResults().size() == 0 ? 0 : welfareImages.getResults().size();
    }

    @Override
    public void setOnItemClickListener(RecyclerOnItemClickListener listener) {
        this.listener = listener;
    }

    public String getLatestId() {
        return latestId+(resultsBean.size()/10+1)+"";
    }

    public static class StaggeredViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.imageView)
        ImageView imageView;

        public StaggeredViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public View getView(){
            return imageView;
        }
    }
}
