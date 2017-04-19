package com.example.test.onconfigchanged.onconfig;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.test.onconfigchanged.R;
import com.example.test.onconfigchanged.base.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登陆页
 */
public class LoginActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

}

