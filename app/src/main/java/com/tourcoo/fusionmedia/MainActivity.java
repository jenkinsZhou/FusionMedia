package com.tourcoo.fusionmedia;


import android.os.Bundle;
import android.view.View;

import com.tourcoo.core.module.activity.BaseMainActivity;
import com.tourcoo.core.module.activity.BaseTitleActivity;
import com.tourcoo.core.utils.FrameUtil;
import com.tourcoo.core.utils.ToastUtil;
import com.tourcoo.core.widget.view.title.TitleBarView;


public class MainActivity extends BaseTitleActivity {
private static final String TAG = "MainActivity";


    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        findViewById(R.id.btnTestSuccess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showSuccess(R.string.app_name);
            }
        });
        findViewById(R.id.btnTestFailed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showFailed("操作失败");
            }
        });
        findViewById(R.id.btnTestNormal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.showNormal("普通吐司");
                FrameUtil.startActivity(MainActivity.this, BaseMainActivity.class);
            }
        });
    }

    @Override
    public void setTitleBar(TitleBarView titleBar) {

    }
}
