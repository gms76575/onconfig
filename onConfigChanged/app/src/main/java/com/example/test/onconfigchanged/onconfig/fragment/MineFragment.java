package com.example.test.onconfigchanged.onconfig.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.example.test.onconfigchanged.R;
import com.example.test.onconfigchanged.base.ui.BaseFragment;

/**
 * Created by Administrator on 2017/3/28.
 */

public class MineFragment extends BaseFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContent(LayoutInflater.from(mContext).inflate(R.layout.fragment_mine,null));
    }
}
