package com.tourcoo.fusionmedia.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.tourcoo.core.log.TourCooLogUtil;
import com.tourcoo.core.manager.RxJavaManager;
import com.tourcoo.core.module.activity.BaseTitleActivity;
import com.tourcoo.core.retrofit.BaseObserver;
import com.tourcoo.core.utils.DrawableUtil;
import com.tourcoo.core.utils.FrameUtil;
import com.tourcoo.core.utils.SizeUtil;
import com.tourcoo.core.utils.StatusBarUtil;
import com.tourcoo.core.widget.navigation.NavigationBarUtil;
import com.tourcoo.core.widget.view.title.TitleBarView;
import com.tourcoo.fusionmedia.MainActivity;
import com.tourcoo.fusionmedia.R;
import com.trello.rxlifecycle3.android.ActivityEvent;


/**
 * @Function: 欢迎页
 * @Description:
 */
public class SplashActivity extends BaseTitleActivity {

private TextView tvApp;

    private TextView tvVersion;

    private TextView tvCopyRight;
    /**
     * 闪屏
     */
    private long mDelayTime = 1800;

    @Override
    public void beforeSetContentView() {
        //防止应用后台后点击桌面图标造成重启的假象---MIUI及Flyme上发现过(原生未发现)
        if (!isTaskRoot()) {
            finish();
            return;
        }
        super.beforeSetContentView();
    }


    @Override
    public void setTitleBar(TitleBarView titleBar) {
        titleBar.setStatusBarLightMode(false)
                .setVisibility(View.GONE);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        if (!isTaskRoot()) {
            return;
        }
        if (!StatusBarUtil.isSupportStatusBarFontChange()) {
            //隐藏状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        tvApp =     findViewById(R.id.tv_appSplash);
         TourCooLogUtil.i(TAG,"tvApp="+tvApp);
        tvVersion =  findViewById(R.id.tv_versionSplash);
        tvCopyRight =  findViewById(R.id.tv_copyRightSplash);
        tvApp.setCompoundDrawablesWithIntrinsicBounds(null,
                DrawableUtil.setTintDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher).mutate(), Color.WHITE)
                , null, null);
        mContentView.setBackgroundResource(R.drawable.ic_bg_splash);
        tvVersion.setText("V" + FrameUtil.getVersionName(mContext));
        tvVersion.setTextColor(Color.WHITE);
        tvCopyRight.setTextColor(Color.WHITE);
        startAnimator();
    }

    /**
     * 动画
     */
    private void startAnimator() {
        float transY = (SizeUtil.getScreenHeight() + NavigationBarUtil.getNavigationBarHeight(this)) * 0.25f;
        //以控件自身所在的位置为原点，从下方距离原点200像素的位置移动到原点
        ObjectAnimator tranCenter = ObjectAnimator.ofFloat(tvVersion, "translationY", transY, 0);
        ObjectAnimator tranBottom = ObjectAnimator.ofFloat(tvCopyRight, "translationY", transY, 0);
        //将控件alpha属性从0变到1
        ObjectAnimator alphaCenter = ObjectAnimator.ofFloat(tvVersion, "alpha", 0, 1);
        ObjectAnimator alphaBottom = ObjectAnimator.ofFloat(tvCopyRight, "alpha", 0, 1);
        //初始化logo的移动和缩放动画

        //logo由屏幕中间移动回原始位置
        ObjectAnimator tranLogo = ObjectAnimator.ofFloat(tvApp, "translationY", transY, 0);
        //ivLogo在X轴和Y轴上都由0.5倍缩放到原本尺寸
        ObjectAnimator scaleXLogo = ObjectAnimator.ofFloat(tvApp, "scaleX", 0.5f, 1f);
        ObjectAnimator scaleYLogo = ObjectAnimator.ofFloat(tvApp, "scaleY", 0.5f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(mDelayTime*2/3);
        animatorSet.play(tranLogo)
                .with(scaleXLogo)
                .with(scaleYLogo)
                .with(tranCenter)
                .with(tranBottom)
                .with(alphaCenter)
                .with(alphaBottom);
        animatorSet.start();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startGo();
            }
        });
    }

    private void startGo() {
        RxJavaManager.getInstance().setTimer(mDelayTime/3)
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<Long>() {
                    @Override
                    public void onSuccessNext(Long entity) {
                        if (isFinishing() || isDestroyed()) {
                            return;
                        }
                        FrameUtil.startActivity(mContext, MainActivity.class);
                        finish();
                    }
                });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        StatusBarUtil.hideStatusBar(this,true);
        NavigationBarUtil.hideNavigationBar(this,true);
    }
}
