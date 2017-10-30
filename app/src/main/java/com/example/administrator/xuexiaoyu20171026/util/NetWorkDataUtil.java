package com.example.administrator.xuexiaoyu20171026.util;

import android.content.Context;
import android.os.AsyncTask;


import com.example.administrator.xuexiaoyu20171026.inter.JSONCallBack;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by maohuawei on 2017/10/26.
 */

public class NetWorkDataUtil {

    /**
     * 请求网络书库
     * @param context
     * @param path
     * @param callBack
     */

    public static void getData(Context context, final String path, final JSONCallBack callBack) {

        //判断空
        if (context != null) {

                //异步加载
            new AsyncTask<Void, Void, String>() {

                @Override
                protected String doInBackground(Void... params) {


                    try {
                        //请求网络
                        HttpURLConnection connection = (HttpURLConnection) new URL(path).openConnection();
                        //设置请求方法
                        connection.setRequestMethod("GET");
                        //设置连接超时
                        connection.setConnectTimeout(1024 * 8);
                        //设置读取超时
                        connection.setReadTimeout(1024 * 8);
                        //获取状态吗
                        int responseCode = connection.getResponseCode();
                        //判断状态吗
                        if (responseCode == 200) {

                            //返回字符串
                            return StringUtil.streanToString(connection.getInputStream(), "utf-8");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    return null;
                }

                @Override
                protected void onPostExecute(String s) {
                    callBack.callJSON(s);
                }
            }.execute();


        }


    }


}
