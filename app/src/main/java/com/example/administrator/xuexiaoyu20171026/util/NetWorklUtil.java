package com.example.administrator.xuexiaoyu20171026.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by maohuawei on 2017/10/26.
 */

public class NetWorklUtil {


    public static boolean isConnect(Context context) {

        //判断空
        if (context == null) {
            return false;
        }

        //获取网络管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //获取网络信息
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        //返回网络状态信息
        return info != null && info.isAvailable();


    }

}
