package com.tourcoo.core.manager;

import android.app.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.tourcoo.core.delegate.RefreshDelegate;
import com.tourcoo.core.delegate.TitleDelegate;
import com.tourcoo.core.helper.BasisHelper;
import com.tourcoo.core.log.TourCooLogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * @Function: 保存Delegate对象以便统一销毁
 * @Description:
 */
public class DelegateManager {

    private static volatile DelegateManager sInstance;

    private DelegateManager() {
    }

    public static DelegateManager getInstance() {
        if (sInstance == null) {
            synchronized (DelegateManager.class) {
                if (sInstance == null) {
                    sInstance = new DelegateManager();
                }
            }
        }
        return sInstance;
    }


    /**
     * 装载FastRefreshDelegate Map对象
     */
    private WeakHashMap<Class, RefreshDelegate> mFastRefreshDelegateMap = new WeakHashMap<>();
    private WeakHashMap<Class, TitleDelegate> mFastTitleDelegateMap = new WeakHashMap<>();
    private WeakHashMap<Activity, List<BasisHelper>> mBasisHelperMap = new WeakHashMap<>();

    public RefreshDelegate getFastRefreshDelegate(Class cls) {
        RefreshDelegate delegate = null;
        if (cls != null && mFastRefreshDelegateMap.containsKey(cls)) {
            delegate = mFastRefreshDelegateMap.get(cls);
        }
        return delegate;
    }

    public void putFastRefreshDelegate(Class cls, RefreshDelegate delegate) {
        if (cls != null && !mFastRefreshDelegateMap.containsKey(cls)) {
            mFastRefreshDelegateMap.put(cls, delegate);
        }
    }

    /**
     * {@link FastLifecycleCallbacks#onFragmentViewDestroyed(FragmentManager, Fragment)}
     * {@link FastLifecycleCallbacks#onActivityDestroyed(Activity)}
     *
     * @param cls
     */
    public void removeFastRefreshDelegate(Class cls) {
        RefreshDelegate delegate = getFastRefreshDelegate(cls);
        TourCooLogUtil.i("removeFastRefreshDelegate_class:" + cls + ";delegate:" + delegate);
        if (delegate != null) {
            delegate.onDestroy();
            mFastRefreshDelegateMap.remove(cls);
        }
    }

    public TitleDelegate getFastTitleDelegate(Class cls) {
        TitleDelegate delegate = null;
        if (cls != null && mFastTitleDelegateMap.containsKey(cls)) {
            delegate = mFastTitleDelegateMap.get(cls);
        }
        return delegate;
    }

    public void putFastTitleDelegate(Class cls, TitleDelegate delegate) {
        if (cls != null && !mFastTitleDelegateMap.containsKey(cls)) {
            mFastTitleDelegateMap.put(cls, delegate);
        }
    }

    /**
     * {@link FastLifecycleCallbacks#onFragmentViewDestroyed(FragmentManager, Fragment)}
     * {@link FastLifecycleCallbacks#onActivityDestroyed(Activity)}
     *
     * @param cls
     */
    public void removeFastTitleDelegate(Class cls) {
        TitleDelegate delegate = getFastTitleDelegate(cls);
        if (delegate != null) {
            delegate.onDestroy();
            mFastTitleDelegateMap.remove(cls);
        }
    }


    public void putBasisHelper(Activity activity, BasisHelper helper) {
        if (activity == null) {
            return;
        }
        if (mBasisHelperMap.containsKey(activity)) {
            mBasisHelperMap.get(activity).add(helper);
        } else {
            List<BasisHelper> list = new ArrayList<>();
            list.add(helper);
            mBasisHelperMap.put(activity, list);
        }
    }

    /**
     * {@link FastLifecycleCallbacks#onActivityDestroyed(Activity)}
     *
     * @param activity
     */
    public void removeBasisHelper(Activity activity) {
        if (mBasisHelperMap.containsKey(activity)) {
            List<BasisHelper> list = mBasisHelperMap.get(activity);
            if (list != null) {
                TourCooLogUtil.i("list:"+list.size());
                for (BasisHelper item : list) {
                    item.onDestroy();
                }
                list.clear();
                mBasisHelperMap.remove(activity);
            }
        }
    }
}
