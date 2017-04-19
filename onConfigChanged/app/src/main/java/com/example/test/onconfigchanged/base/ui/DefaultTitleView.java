package com.example.test.onconfigchanged.base.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.test.onconfigchanged.R;
import com.example.test.onconfigchanged.base.rxbinding.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/28.
 */

public class DefaultTitleView extends RelativeLayout {
    private Context context;

    public DefaultTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(attrs, 0);
    }

    public DefaultTitleView(Context context) {
        this(context, 0);
    }

    public DefaultTitleView(Context context, int inflateLayoutId) {
        super(context);
        this.context = context;
        initView(null, inflateLayoutId);
    }

    private void initView(AttributeSet attrs, int inflateLayoutId) {
        if (inflateLayoutId == 0) {
            inflateLayoutId = R.layout.global_titlebar;
        }
        inflate(this.getContext(), inflateLayoutId, this);
        ButterKnife.bind(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DefaultTitleView);
        CharSequence backText = typedArray.getText(R.styleable.DefaultTitleView_LBackText);
        CharSequence title = typedArray.getText(R.styleable.DefaultTitleView_CTitleText);
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
        if (!TextUtils.isEmpty(backText)) {
            setBackText(backText);
        }
    }

    /**
     * 设置左边点击事件
     *
     * @param l
     */
    public void setOnBackClickListener(OnClickListener l) {
        mBackTextView.setOnClickListener(l);
    }

    /**
     * 设置标题文本
     *
     * @param title
     */
    public void setTitle(CharSequence title) {
        if (title == null) {
            return;
        }
        String x = title.toString();
        title = x.length() <= 9 ? x : x.substring(0, 9) + "...";
        mTitleTextView.setText(title);
    }

    /**
     * 设置左边文本
     *
     * @param backText
     */
    public void setBackText(CharSequence backText) {
        if (backText == null) {
            return;
        }
        mBackTextView.setText(backText.toString());
        mBackTextView.setCompoundDrawables(null, null, null, null);
    }

    /**
     * 设置左边图标
     *
     * @param resId
     */
    public void setTitleIcon(CharSequence title, @DrawableRes int resId) {
        if (resId == 0) {
            return;
        }
        mTitleTextView.setText(title);
        mTitleTextView.setVisibility(View.VISIBLE);
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        mTitleTextView.setCompoundDrawables(null, null, drawable, null);
    }

    /**
     * 设置左边图标
     *
     * @param resId
     */
    public void setBackTextIcon(@DrawableRes int resId) {
        if (resId == 0) {
            return;
        }
        mBackTextView.setText("");
        mBackTextView.setVisibility(View.VISIBLE);
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        mBackTextView.setCompoundDrawables(drawable, null, null, null);
    }

    /**
     * 设置titlebar的背景颜色
     *
     * @param color
     */
    public void setBackground(int color) {
        llTitleBar.setBackgroundColor(color);
    }

    /**
     * 设置titlebar的背景
     *
     * @param drawable
     */
    public void setBackground(Drawable drawable) {
        llTitleBar.setBackgroundDrawable(drawable);
    }

    /**
     * 设置右边文本文字
     *
     * @param charSequence
     */
    public void setRightBtnText(CharSequence charSequence) {
        if (charSequence == null) {
            return;
        }
        mRightTextView.setVisibility(View.VISIBLE);
        mRightTextView.setText(charSequence.toString());
        mRightTextView.setCompoundDrawables(null, null, null, null);
    }

    public void setRightBtnOnClickListener(OnClickListener listener) {
        mRightTextView.setOnClickListener(listener);
    }

    public Observable<Void> onRightBtnClicks() {
        return RxView.clicks(mRightTextView);
    }


    public void setTitleBtnOnClickListener(OnClickListener listener) {
        mTitleTextView.setOnClickListener(listener);
    }

    public Observable<Void> onTitleBtnClicks() {
        return RxView.clicks(mTitleTextView);
    }


    /**
     * 设置右边图标
     *
     * @param resId
     */
    public void setRightBtnTextIcon(@DrawableRes int resId) {
        if (resId == 0) {
            return;
        }
        mRightTextView.setText("");
        mRightTextView.setVisibility(View.VISIBLE);
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        mRightTextView.setCompoundDrawables(drawable, null, null, null);
    }

    /**
     * 设置整个标题背景为透明
     */
    public void setTitleTransparentBackground() {
        titleDivider.setVisibility(INVISIBLE);
        llTitleBar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }

    @BindView(R.id.titlebar_tv_left)
    TextView mBackTextView;
    @BindView(R.id.titlebar_tv_center)
    TextView mTitleTextView;
    @BindView(R.id.titlebar_tv_right)
    TextView mRightTextView;
    @BindView(R.id.titlebar)
    @Nullable
    ViewGroup llTitleBar;
    @BindView(R.id.title_divider)
    View titleDivider;
}
