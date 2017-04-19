package com.example.test.onconfigchanged.onconfig;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.example.test.onconfigchanged.R;
import com.example.test.onconfigchanged.base.ui.BaseActivity;
import com.example.test.onconfigchanged.base.ui.BaseFragment;
import com.example.test.onconfigchanged.onconfig.fragment.HomeFragment;
import com.example.test.onconfigchanged.onconfig.fragment.MessageFragment;
import com.example.test.onconfigchanged.onconfig.fragment.MineFragment;
import com.example.test.onconfigchanged.onconfig.fragment.ShopcartFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;

public class MainActivity2 extends BaseActivity implements AHBottomNavigation.OnTabSelectedListener{

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;
    @BindView(R.id.view_pager)
    AHBottomNavigationViewPager viewPager;
    @BindView(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;
//    private AHBottomNavigationAdapter navigationAdapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private DemoViewPagerAdapter adapter;
    private BaseFragment currentFragment;
    private List<BaseFragment> fragments;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_TranslucentNavigation);
        setSwipeBackEnable(false);
        removeTitle();
        setContent(LayoutInflater.from(this).inflate(R.layout.activity_main2, null));
        initNavigation();
//        bottomNavigation.setTranslucentNavigationEnabled(true);
        bottomNavigation.setOnTabSelectedListener(this);
        initViewpager();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Setting custom colors for notification
                AHNotification notification = new AHNotification.Builder()
                        .setText("2")
                        .setBackgroundColor(ContextCompat.getColor(mActivity, R.color.color_notification_back))
                        .setTextColor(ContextCompat.getColor(mActivity, R.color.color_notification_text))
                        .build();
                bottomNavigation.setNotification(notification, 1);
                Snackbar.make(bottomNavigation, "Snackbar with bottom navigation",
                        Snackbar.LENGTH_SHORT).show();

            }
        }, 3000);
    }

    private void initViewpager() {
        viewPager.setOffscreenPageLimit(4);
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new MessageFragment());
        fragments.add(new ShopcartFragment());
        fragments.add(new MineFragment());
        adapter = new DemoViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }

    private void initNavigation() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("车辆信息", R.mipmap.ic_launcher);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("借贷方案", R.mipmap.ic_launcher);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("客户身份", R.mipmap.ic_launcher);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("客户信息", R.mipmap.ic_launcher);
//        AHBottomNavigationItem item5 = new AHBottomNavigationItem("客户工作", R.mipmap.ic_launcher);
//        AHBottomNavigationItem item6 = new AHBottomNavigationItem("联系人", R.mipmap.ic_launcher);
//        AHBottomNavigationItem item7 = new AHBottomNavigationItem("二手车", R.mipmap.ic_launcher);
//        AHBottomNavigationItem item8 = new AHBottomNavigationItem("资料", R.mipmap.ic_launcher);
//        AHBottomNavigationItem item9 = new AHBottomNavigationItem("授权书", R.mipmap.ic_launcher);
//        AHBottomNavigationItem item10 = new AHBottomNavigationItem("申请表", R.mipmap.ic_launcher);

        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigationItems.add(item4);
//        bottomNavigationItems.add(item5);
//        bottomNavigationItems.add(item6);
//        bottomNavigationItems.add(item7);
//        bottomNavigationItems.add(item8);
//        bottomNavigationItems.add(item9);
//        bottomNavigationItems.add(item10);

        bottomNavigation.addItems(bottomNavigationItems);
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        viewPager.setCurrentItem(position);
        currentFragment = (BaseFragment) adapter.getItem(position);
        return true;
    }
}
