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
import com.myzhihu.mvp.myzhihu.model.entity.TopicItem;
import com.myzhihu.mvp.myzhihu.presenter.infr.RecyclerOnItemClickListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CXG on 2016/7/4.
 */
public class MyColumnAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder>{

    private Context context;
    private ArrayList<TopicItem> topicItemList = new ArrayList<>();

    public MyColumnAdapter(Context context,ArrayList<TopicItem> topicItemList){
        this.context = context;
        this.topicItemList = topicItemList;
    }

    @Override
    public void setOnItemClickListener(RecyclerOnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycolumn,parent,false);
        SquareViewHolder squareViewHolder = new SquareViewHolder(v);
        return squareViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SquareViewHolder){
            bindSquareView((SquareViewHolder)holder,position);
        }
    }

    private void bindSquareView(SquareViewHolder holder, final int position) {
        holder.tv_item_name.setText(topicItemList.get(position).getType());
        holder.tv_item_num.setText(topicItemList.get(position).getNum());
        Glide.with(context)
                .load(topicItemList.get(position).getImage())
                .placeholder(R.drawable.place)
                .error(R.drawable.place)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.imageView);
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClickListener(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topicItemList.size();
    }

    public static class SquareViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.img_item_back)
        ImageView imageView;
        @Bind(R.id.tv_item_name)
        TextView tv_item_name;
        @Bind(R.id.tv_item_num)
        TextView tv_item_num;

        public SquareViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public View getView(){
            return this.itemView;
        }
    }
}
