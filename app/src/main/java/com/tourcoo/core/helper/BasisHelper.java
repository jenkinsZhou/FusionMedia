package com.tourcoo.core.helper;

import android.app.Activity;

import com.tourcoo.core.log.TourCooLogUtil;
import com.tourcoo.core.manager.DelegateManager;


/**
 * @Function: 绑定Activity Helper
 * @Description:
 */
public class BasisHelper {
    protected Activity mContext;
//    protected Unbinder mUnBinder;
    protected String mTag = getClass().getSimpleName();

    public BasisHelper(Activity context) {
        mContext = context;
        DelegateManager.getInstance().putBasisHelper(context, this);
    }

    /**
     * Activity 关闭onDestroy调用
     */
    public void onDestroy() {
        TourCooLogUtil.w(mTag, "onDestroy");
      /*  if (mUnBinder != null) {
            mUnBinder.unbind();
            mUnBinder = null;
        }*/
    }
}
