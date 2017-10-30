package com.example.administrator.xuexiaoyu20171026.application;

import android.app.Application;
import com.example.administrator.xuexiaoyu20171026.util.ImageLoaderUtil;

/**
 * Created by maohuawei on 2017/10/26.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化ImageLoader
        ImageLoaderUtil.init(this);
    }
}
