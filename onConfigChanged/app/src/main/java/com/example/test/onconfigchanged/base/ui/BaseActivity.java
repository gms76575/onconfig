package com.example.test.onconfigchanged.base.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.test.onconfigchanged.R;
import com.example.test.onconfigchanged.base.swipeback.app.SwipeBackActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/28.
 */

public class BaseActivity extends SwipeBackActivity {
    protected BaseActivity mActivity;
    protected LinearLayout layoutRoot;
//    @BindView(R.id.layout_title)
    FrameLayout layoutTitle;
//    @BindView(R.id.layout_pull_down)
    FrameLayout layoutPullDown;
//    @BindView(R.id.layout_content)
    LinearLayout layoutContent;
//    @BindView(R.id.layout_pull_up)
    FrameLayout layoutPullUp;
//    @BindView(R.id.layout_footer)
    FrameLayout layoutFooter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base);
        mActivity = this;
//        ButterKnife.bind(this);
        layoutRoot = (LinearLayout) findViewById(R.id.layout_root);
        layoutTitle = (FrameLayout) findViewById(R.id.layout_title);
        layoutPullDown = (FrameLayout) findViewById(R.id.layout_pull_down);
        layoutContent = (LinearLayout) findViewById(R.id.layout_content);
        layoutPullUp = (FrameLayout) findViewById(R.id.layout_pull_up);
        layoutFooter = (FrameLayout) findViewById(R.id.layout_footer);
        setTitleView(new DefaultTitleView(this));//TODO

    }

    protected void setTitleView(View title) {
        layoutTitle.setVisibility(View.VISIBLE);
        layoutTitle.removeAllViews();
        layoutTitle.addView(title);
    }

    protected void removeTitle(){
        layoutTitle.removeAllViews();
        layoutTitle.setVisibility(View.GONE);
    }

    protected void setContent(View content){
        layoutContent.removeAllViews();
        layoutContent.addView(content, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ButterKnife.bind(this, content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
