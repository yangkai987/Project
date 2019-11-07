package com.membership_score.baselib.base;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.membership_score.baselib.BuildConfig;
import com.membership_score.baselib.utils.ContextUtil;

/**
 * @author zhoujiulong
 * @createtime 2019/7/11 13:34
 */
public class SimpleApplication extends Application {

    private static Application mInstance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ContextUtil.init(this);
        initLogger();
    }

    /**
     * 初始化日志输入框架
     */
    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true).methodCount(1).methodOffset(1).tag("xingfugolog").build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG_MODE;
            }
        });
    }

    public static Application getInstance() {
        return mInstance;
    }

}

















