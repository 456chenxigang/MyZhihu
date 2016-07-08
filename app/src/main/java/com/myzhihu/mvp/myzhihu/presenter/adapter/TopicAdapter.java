package com.myzhihu.mvp.myzhihu.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.myzhihu.mvp.myzhihu.R;
import com.myzhihu.mvp.myzhihu.model.entity.Story;
import com.myzhihu.mvp.myzhihu.model.entity.TopicDetail;
import com.myzhihu.mvp.myzhihu.presenter.infr.RecyclerOnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CXG on 2016/7/5.
 */
public class TopicAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder>{

    public static final int TYPE_HEAD = 0x01;
    public static final int TYPE_LIST = 0x02;

    private Context context;
    private TopicDetail topicDetail;
    private List<Story> stories;
    private String latestId;



    public  TopicAdapter(Context context,TopicDetail topicDetail){
        this.context = context;
        init(topicDetail);
    }

    private void init(TopicDetail topicDetail) {
        if (stories != null)
            stories.clear();
        this.topicDetail = topicDetail;
        if (topicDetail!=null){
            this.stories = topicDetail.getStories();
            if (stories.size()>0){
                int len = stories.size();
                latestId = stories.get(len-1).getId()+"";
            }
        }
        this.notifyDataSetChanged();
    }

    public void loadingNewTopic(TopicDetail topicDetail){
        init(topicDetail);
    }

    public void loadingPastTopic(TopicDetail oldTopicDetail){
        List<Story> tmpStory = oldTopicDetail.getStories();
        if (tmpStory!=null && tmpStory.size() > 0){
            this.stories.addAll(tmpStory);
            int len = this.stories.size();
            if (stories.size() > 0){
                this.latestId = this.stories.get(len - 1).getId()+"";
            }
            this.notifyDataSetChanged();
        }
    }

    public String getLatestId(){
        return this.latestId;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_LIST;
    }

    @Override
    public void setOnItemClickListener(RecyclerOnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LIST){
            View v = LayoutInflater.from(context).inflate(R.layout.fead_item,parent,false);
            TopicListHolder topicListHolder = new TopicListHolder(v);
            return topicListHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TopicListHolder){
            bindListViewHolder((TopicListHolder)holder,position);
        }

    }

    private void bindListViewHolder(TopicListHolder holder, int position) {
        final int tPos = position;
        holder.tv_feed_desc.setText(stories.get(tPos).getTitle());
        List<String> images = stories.get(tPos).getImages();
        if (images == null || images.size() == 0){
            holder.img_feed_flag.setVisibility(View.GONE);
        }else {
            Glide.with(context)
                    .load(stories.get(tPos).getImages().get(0))
                    .placeholder(R.drawable.place)
                    .error(R.drawable.place)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(holder.img_feed_pic);
            holder.img_feed_flag.setVisibility(View.VISIBLE);
        }
        holder.getView().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.OnItemClickListener(v,stories.get(tPos).getId()+"");
                }
            }
        });
    }

    public static class TopicListHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.img_feed_flag)
        ImageView img_feed_flag;
        @Bind(R.id.img_feed_pic)
        ImageView img_feed_pic;
        @Bind(R.id.tv_feed_desc)
        TextView tv_feed_desc;

        public TopicListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public View getView(){
            return this.itemView;
        }
    }
    @Override
    public int getItemCount() {
        return stories.size();
    }
}
