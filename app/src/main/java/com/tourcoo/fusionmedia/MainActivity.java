package com.tourcoo.fusionmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.tourcoo.core.log.TourCooLogUtil;
import com.tourcoo.core.utils.ToastUtil;


public class MainActivity extends AppCompatActivity {
private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnTestSuccess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showSuccess("操作成功");
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
                ToastUtil.showNormal("普通吐司");
            }
        });
    }
}
