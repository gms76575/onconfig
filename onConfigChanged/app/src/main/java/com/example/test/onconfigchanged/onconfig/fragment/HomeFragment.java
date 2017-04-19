package com.example.test.onconfigchanged.onconfig.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.example.test.onconfigchanged.R;
import com.example.test.onconfigchanged.base.ui.BaseFragment;
import com.example.test.onconfigchanged.base.ui.DefaultTitleView;

/**
 * Created by Administrator on 2017/3/28.
 */

public class HomeFragment extends BaseFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContent(LayoutInflater.from(mContext).inflate(R.layout.fragment_home,null));
        initTitle();
    }

    private void initTitle() {
        DefaultTitleView title = (DefaultTitleView) getTitleView();
        title.setTitle("Home");
        title.setBackText("back");
        title.setRightBtnText("setting");
    }

}
