package com.myzhihu.mvp.myzhihu.view;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.myzhihu.mvp.myzhihu.R;
import com.myzhihu.mvp.myzhihu.presenter.adapter.MainTabPagerAdapter;
import com.myzhihu.mvp.myzhihu.view.fragment.MyColumnFragment;
import com.myzhihu.mvp.myzhihu.view.fragment.MyZhiHuFragment;
import com.myzhihu.mvp.myzhihu.view.fragment.SelectedFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.frame_content)
    LinearLayout frameContent;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    private ActionBarDrawerToggle drawerToggle;
    private String[] mTitles;
    private MainTabPagerAdapter tabPagerAdapter;
    private ArrayList<Fragment> fragments;
    private Fragment myZhiHuFragment,myColumnFragment,selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("首页");
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        setNavigationViewItemClickListener();

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);

        initTabs();
    }

    private void initTabs() {
        mTitles = new String[]{"我的知乎","我的栏目","精选"};
        fragments = new ArrayList<>();

        myZhiHuFragment = new MyZhiHuFragment();
        myColumnFragment = new MyColumnFragment();
        selectedFragment = new SelectedFragment();
        fragments.add(myZhiHuFragment);
        fragments.add(myColumnFragment);
        fragments.add(selectedFragment);

        for (int i = 0; i < mTitles.length; i ++){
            tabLayout.addTab(tabLayout.newTab().setText(mTitles[i]));
        }

        tabPagerAdapter = new MainTabPagerAdapter(getSupportFragmentManager(),mTitles,fragments);
        viewPager.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setNavigationViewItemClickListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_item_home:
                        showSnackBar("首页");
                        break;
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    private void showSnackBar(String str) {
        Snackbar.make(mainContent, str, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

   private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.about_action:
                    showSnackBar("关于");
                    break;
                case R.id.settings_action:
                    showSnackBar("设置");
                    break;
                case R.id.search_icon:
                    showSnackBar("查找");
                    break;
            }
            return false;
        }
    };

    private long lastBackKeyDownTick = 0;
    public static final long MAX_DOUBLE_BACK_DURATION = 2000;

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(Gravity.LEFT)){//当前抽屉是打开的，则关闭
            drawerLayout.closeDrawers();
            return;
        }

        long currentTick = System.currentTimeMillis();
        if(currentTick - lastBackKeyDownTick > MAX_DOUBLE_BACK_DURATION){
            showSnackBar("再按一次退出");
            lastBackKeyDownTick = currentTick;
        }else {
            finish();
            System.exit(0);
        }
    }
}
