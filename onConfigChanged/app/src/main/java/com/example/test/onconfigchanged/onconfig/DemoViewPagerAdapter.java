package com.example.test.onconfigchanged.onconfig;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.test.onconfigchanged.base.ui.BaseFragment;

import java.util.List;

/**
 * Created by xn052805 on 2017/4/17.
 */

public class DemoViewPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragments;
    public DemoViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
