package com.myzhihu.mvp.myzhihu.view;

import android.os.Bundle;

import com.myzhihu.mvp.myzhihu.R;
import com.myzhihu.mvp.myzhihu.common.util.HackyViewPager;
import com.myzhihu.mvp.myzhihu.presenter.adapter.ImageViewPagerAdapter;
import com.myzhihu.mvp.myzhihu.presenter.adapter.StaggeredAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LargeImageActivity extends BaseActivity {

    @Bind(R.id.viewPager)
    HackyViewPager viewPager;

    private int position;
    private ImageViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_image);
        ButterKnife.bind(this);

        position = getIntent().getIntExtra("id",6);

        List<String> imagesUrl = new ArrayList<>();
        for (int i = 0; i < StaggeredAdapter.resultsBean.size();i ++){
            imagesUrl.add(StaggeredAdapter.resultsBean.get(i).getUrl());
        }
        viewPagerAdapter = new ImageViewPagerAdapter(getSupportFragmentManager(),imagesUrl);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(position);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}
