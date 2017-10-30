package com.example.administrator.xuexiaoyu20171026.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by maohuawei on 2017/10/26.
 */

public class StringUtil {


    /**
     * 字节流转字符串
     * @param inputStream
     * @param charset
     * @return
     */
    public static String streanToString(InputStream inputStream, String charset) {


        try {
            //读取流
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset));

            //读取
            String str = null;
            //保存
            StringBuilder builder = new StringBuilder();

            try {
                //循环读取
                while ((str = br.readLine()) != null) {

                    builder.append(str);


                }
                //返回字符串
                return builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
