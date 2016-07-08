package com.myzhihu.mvp.myzhihu.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myzhihu.mvp.myzhihu.R;
import com.myzhihu.mvp.myzhihu.model.entity.TopicItem;
import com.myzhihu.mvp.myzhihu.presenter.adapter.MyColumnAdapter;
import com.myzhihu.mvp.myzhihu.presenter.infr.RecyclerOnItemClickListener;
import com.myzhihu.mvp.myzhihu.view.TopicActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyColumnFragment extends BaseFragment implements RecyclerOnItemClickListener{

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    private MyColumnAdapter myColumnAdapter;
    private GridLayoutManager gridLayoutManager;

    private ArrayList<TopicItem> topicItemList = new ArrayList<>();
    private String[] topicType = new String[]{};
    private String[] imageUrl= new String[]{"http://pic2.zhimg.com//71c8bcd3d99958de45ed87b8fc213224.jpg",
            "http://pic3.zhimg.com//ca912fc8405360ada01a61f7c78e474a.jpg",
            "http://pic3.zhimg.com//42a7dd7cf12b3fb5903d78273dfc6c8e_t.jpg",
            "http://pic3.zhimg.com//fd7eb52781ea04ba6e6962eef86450ce_t.jpg",
            "http://pic2.zhimg.com//13974285495b8a66f52ce2f8c9efa3a9.jpg",
            "http://pic2.zhimg.com//51e7f78ddf0a00e900704169d8cf3299_t.jpg",
            "http://pic3.zhimg.com//57435cf3b8e7884e06aa664b593ec6b6_t.jpg",
            "http://pic1.zhimg.com//8f26a64b42cc81666fff052cfefd0c74.jpg",
            "http://pic3.zhimg.com//d13bd513bde7372d146f4ed6790aa6be_t.jpg",
            "http://pic2.zhimg.com//71f849922f7613132f78f84ba15ae001_t.jpg",
            "http://pic3.zhimg.com//3b77ab10d0ff0165d5e9bcae8f2ce07e.jpg",
            "http://pic1.zhimg.com//978b339049ef497d10dcb47ad230bc68_t.png"};
    private String[] topicNum = new String[]{"99+","19","99+","79","93","6",
                                            "32","99+","68","88","99+","21"};

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                if (swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        }
    };

    public MyColumnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_column, container, false);
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

        TopicItem topicItem;
        topicType = getResources().getStringArray(R.array.menu_topic_type);
        for (int i = 0;i < 12;i ++)
        {
            topicItem = new TopicItem();
            topicItem.setType(topicType[i].split(",")[0]);
            topicItem.setId(topicType[i].split(",")[1]);
            topicItem.setNum(topicNum[i]);
            topicItem.setImage(imageUrl[i]);
            topicItemList.add(topicItem);
        }

        myColumnAdapter = new MyColumnAdapter(getActivity(),topicItemList);
        myColumnAdapter.setOnItemClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(myColumnAdapter);
        gridLayoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(gridLayoutManager);


        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //fetchFeed.loadingNew();
                handler.sendEmptyMessageDelayed(1,1000);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void OnItemClickListener(View v, Object obj) {

        Intent intent = new Intent(getActivity(),TopicActivity.class);
        intent.putExtra("id",topicItemList.get((Integer) obj).getId());
        intent.putExtra("topic",topicItemList.get((Integer) obj).getType());
        startActivity(intent);
    }
}
