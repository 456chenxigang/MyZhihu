package com.myzhihu.mvp.myzhihu.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myzhihu.mvp.myzhihu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyZhiHuFragment extends BaseFragment {


    public MyZhiHuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_zhi, container, false);
    }

    @Override
    protected int setLayoutResourceID() {
        return 0;
    }

}
