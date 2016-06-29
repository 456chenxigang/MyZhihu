package com.myzhihu.mvp.myzhihu.presenter.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by CXG on 2016/6/28.
 */
public class MainTabPagerAdapter extends FragmentStatePagerAdapter{

    private String TAG = "MainTabPagerAdapter";
    private String[] mTitles;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    public MainTabPagerAdapter(FragmentManager fm,String[] titles,ArrayList fragments) {
        super(fm);
        this.mTitles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {

//        Fragment fragment = com.myzhihu.mvp.myzhihu.common.util.ViewUtils.createFragment(MyZhiHuFragment.class,false);
//        Bundle bundle = new Bundle();
//        bundle.putString("type", mTitles[position]);
//        fragment.setArguments(bundle);
        return fragments.get(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        //重写这个方法是为了防止在restoreState的时候导致应用崩溃，这样做虽然不太好，但是目前我也只能想到这种方法了
        Log.i(TAG,"restoreState");
    }
}
