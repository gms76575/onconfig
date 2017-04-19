package com.example.test.onconfigchanged.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.test.onconfigchanged.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/28.
 */

public class BaseFragment extends Fragment {
    protected LinearLayout layoutRoot;
    @BindView(R.id.layout_title)
    FrameLayout layoutTitle;
    @BindView(R.id.layout_pull_down)
    FrameLayout layoutPullDown;
    @BindView(R.id.layout_content)
    LinearLayout layoutContent;
    @BindView(R.id.layout_pull_up)
    FrameLayout layoutPullUp;
    @BindView(R.id.layout_footer)
    FrameLayout layoutFooter;

    protected View mView;

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_base, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutRoot = (LinearLayout) view.findViewById(R.id.layout_root);
        setTitleView(new DefaultTitleView(mContext));//TODO
    }

    protected void setTitleView(View title) {
        layoutTitle.setVisibility(View.VISIBLE);
        layoutTitle.removeAllViews();
        layoutTitle.addView(title);
    }

    protected View getTitleView(){
        return layoutTitle.getChildAt(0);
    }

    protected void removeTitle(){
        layoutTitle.removeAllViews();
        layoutTitle.setVisibility(View.GONE);
    }

    protected void setContent(View content){
        layoutContent.removeAllViews();
        layoutContent.addView(content, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

}
