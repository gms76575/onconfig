package com.example.test.onconfigchanged.onconfig;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.test.onconfigchanged.R;
import com.example.test.onconfigchanged.base.ui.BaseActivity;
import com.example.test.onconfigchanged.base.ui.BaseFragment;
import com.example.test.onconfigchanged.onconfig.fragment.HomeFragment;
import com.example.test.onconfigchanged.onconfig.fragment.MessageFragment;
import com.example.test.onconfigchanged.onconfig.fragment.MineFragment;
import com.example.test.onconfigchanged.onconfig.fragment.ShopcartFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

//    private Map<String, Fragment> mFragments = new HashMap<>();
//    private Fragment currentFragment;
    @BindView(R.id.rgTools)
    RadioGroup radioGroup;
    private int mIndex;
    private BaseFragment[] mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSwipeBackEnable(false);
        removeTitle();
        setContent(LayoutInflater.from(this).inflate(R.layout.activity_main, null));
        initFragment();
        /*Fragment fragment = new HomeFragment();
        changeFragment(fragment);*/
        radioGroup.setOnCheckedChangeListener(this);
    }

    private void initFragment() {
        HomeFragment homeFragment =new HomeFragment();
        ShopcartFragment shopcartFragment =new ShopcartFragment();
        MessageFragment messageFragment =new MessageFragment();
        MineFragment mineFragment =new MineFragment();
        mFragments = new BaseFragment[]{homeFragment,shopcartFragment,messageFragment,mineFragment};
        change(mFragments[0]);
    }


    private void setIndexSelected(int index) {
        if(mIndex==index){
            return;
        }
        change(mFragments[index]);
        mIndex=index;
    }

    private void change(BaseFragment mFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.hide(mFragments[mIndex]);
        if(!mFragment.isAdded()){
            ft.add(R.id.content, mFragment).show(mFragment);
        }else {
            ft.show(mFragment);
        }
        ft.commit();
    }

    @Optional
    @OnClick({R.id.rbHome, R.id.rbShop, R.id.rbMessage, R.id.rbMine})
    public void onClick(View view) {


    }

    /*public Fragment getFragment(Class<?> key){
        return mFragments.get(key.getName());
    }

    public Fragment getFragment(String key){
        return mFragments.get(key);
    }*/

    /*public void changeFragment(Fragment target){
        changeFragment(R.id.content, target);
    }*/

    /*public void changeFragment(int resView, Fragment targetFragment)
    {
        if(targetFragment == null){
            return;
        }
        if (targetFragment.equals(currentFragment)){
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()){
            String key = targetFragment.getClass().getName();
            transaction.add(resView, targetFragment, key);
            mFragments.put(key, targetFragment);
        }
        if (targetFragment.isHidden()) {
            transaction.show(targetFragment);
        }
        if (currentFragment != null && currentFragment.isVisible()){
            transaction.hide(currentFragment);
        }
        currentFragment = targetFragment;
        transaction.commit();
    }*/

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        Fragment fragment = null;
        switch (i) {
            case R.id.rbHome:
                Toast.makeText(mActivity, "home", Toast.LENGTH_SHORT).show();
                setIndexSelected(0);
                /*fragment = getFragment(HomeFragment.class);
                if(fragment == null){
                    fragment = new HomeFragment();
                    mFragments.put(HomeFragment.class.getName(), fragment);
                }
                changeFragment(fragment);*/
                break;
            case R.id.rbShop:
                Toast.makeText(mActivity, "shop", Toast.LENGTH_SHORT).show();
                setIndexSelected(1);
                /*fragment = getFragment(ShopcartFragment.class);
                if(fragment == null){
                    fragment = new ShopcartFragment();
                    mFragments.put(ShopcartFragment.class.getName(), fragment);
                }
                changeFragment(fragment);*/
                break;
            case R.id.rbMessage:
                Toast.makeText(mActivity, "message", Toast.LENGTH_SHORT).show();
                setIndexSelected(2);
                /*fragment = getFragment(MessageFragment.class);
                if(fragment == null){
                    fragment = new MessageFragment();
                    mFragments.put(MessageFragment.class.getName(), fragment);
                }
                changeFragment(fragment);*/
                break;
            case R.id.rbMine:
                Toast.makeText(mActivity, "mine", Toast.LENGTH_SHORT).show();
                setIndexSelected(3);
                /*fragment = getFragment(MineFragment.class);
                if(fragment == null){
                    fragment = new MineFragment();
                    mFragments.put(MineFragment.class.getName(), fragment);
                }
                changeFragment(fragment);*/
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mFragments.clear();
        mFragments = null;
    }
}
