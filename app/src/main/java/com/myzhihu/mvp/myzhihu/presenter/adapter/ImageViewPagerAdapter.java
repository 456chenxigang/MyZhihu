package com.myzhihu.mvp.myzhihu.presenter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.myzhihu.mvp.myzhihu.view.fragment.ImageFragment;

import java.util.List;

/**
 * Created by CXG on 2016/7/7.
 */
public class ImageViewPagerAdapter extends FragmentStatePagerAdapter{

    List<String> mDatas;
    private static final String IMAGE_URL = "image";

    public ImageViewPagerAdapter(FragmentManager fm,List mDatas) {
        super(fm);
        this.mDatas = mDatas;
    }

    @Override
    public Fragment getItem(int position) {
        String url = mDatas.get(position);
        Fragment fragment = ImageFragment.newInstance(url);
        return fragment;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }
}
