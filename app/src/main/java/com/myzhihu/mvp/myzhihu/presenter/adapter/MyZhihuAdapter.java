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
import com.myzhihu.mvp.myzhihu.model.entity.Feed;
import com.myzhihu.mvp.myzhihu.model.entity.Story;
import com.myzhihu.mvp.myzhihu.presenter.infr.RecyclerOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CXG on 2016/6/29.
 */
public class MyZhihuAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder>{

    private static final int TYPE_HEAD = 0x01;
    private static final int TYPE_LIST_ITEM = 0x02;
    private Context context;
    private Feed feed;
    private List<Story> stories;
    protected List<Integer> storyHeadPos = new ArrayList<>();

    public MyZhihuAdapter(Context context,Feed feed){
        this.context = context;
        init(feed);
    }

    private void init(Feed feed) {
        if(stories != null){
            stories.clear();
        }else if (storyHeadPos != null){
            storyHeadPos.clear();
        }
        this.feed = feed;
        if (this.feed != null){
            this.stories =  this.feed.getStories();
            this.storyHeadPos.add(1);
        }
    }

    public void loadingNewFeed(Feed feed){
        init(feed);
        notifyDataSetChanged();
    }

    public void loadingPastFeed(Feed feed,String date){
        List<Story> tmpStory = feed.getStories();
        if (tmpStory != null && tmpStory.size() > 0){
            for (Story story:tmpStory){
                story.setDate(date);
            }
            storyHeadPos.add(this.stories.size()+1);
            this.stories.addAll(tmpStory);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public void setOnItemClickListener(RecyclerOnItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEAD:TYPE_LIST_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEAD){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.head_item,parent,false);
            TopViewHolder vItem = new TopViewHolder(v);
            return vItem;
        }else if (viewType == TYPE_LIST_ITEM){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fead_item,parent,false);
            ItemViewHolder vItem = new ItemViewHolder(v);
            return vItem;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TopViewHolder){
            bindTopData((TopViewHolder) holder);
        }else if (holder instanceof  ItemViewHolder){
            bindItemData((ItemViewHolder) holder,position);
        }
    }

    private void bindTopData(TopViewHolder holder) {
        if (holder instanceof TopViewHolder){
            Glide.with(context)
                    .load(feed.getTopStories().get(0).getImage())
                    .dontAnimate()
                    .error(R.drawable.place)
                    .placeholder(R.drawable.place)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(holder.img_heand_main);
        }
        holder.tv_head_title.setText(feed.getTopStories().get(0).getTitle());
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClickListener(v,feed.getTopStories().get(0).getId()+"");
            }
        });
    }


    private void bindItemData(final ItemViewHolder holder, final int position) {
        final int tPos = position - 1;
        final List<Story> stories = feed.getStories();

        holder.tv_feed_desc.setText(stories.get(tPos).getTitle());
        Glide.with(context)
                .load(stories.get(tPos).getImages().get(0))
                .placeholder(R.drawable.place)
                .error(R.drawable.place)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.img_feed_pic);
        holder.img_feed_flag.setVisibility(stories.get(tPos).getMultipic()?View.VISIBLE:View.GONE);
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClickListener(v,stories.get(tPos).getId()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return feed == null || feed.getStories() == null || feed.getStories().size() == 0 ? 0:feed.getStories().size()+1;
    }

    public static class TopViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.img_head_main)
        ImageView img_heand_main;
        @Bind(R.id.tv_head_title)
        TextView tv_head_title;

        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public View getView(){
            return this.itemView;
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.img_feed_flag)
        ImageView img_feed_flag;
        @Bind(R.id.img_feed_pic)
        ImageView img_feed_pic;
        @Bind(R.id.tv_feed_desc)
        TextView tv_feed_desc;
        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public View getView(){
            return this.itemView;
        }
    }
}
