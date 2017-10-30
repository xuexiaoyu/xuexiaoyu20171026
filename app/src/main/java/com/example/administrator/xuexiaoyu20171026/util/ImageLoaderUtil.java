package com.example.administrator.xuexiaoyu20171026.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.administrator.xuexiaoyu20171026.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;


/**
 * Created by maohuawei on 2017/10/26.
 */

public class ImageLoaderUtil {

    private static final String TAG = "ImageLoaderUtil";

    public static void init(Context context) {


        //缓冲文件路径
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "cache");//StorageUtils.getCacheDirectory(context);

        Log.d(TAG, "init: " + cacheDir.getPath());

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)

                .threadPoolSize(3)// default  线程池内加载的数量

                .threadPriority(Thread.NORM_PRIORITY - 2) // default 设置当前线程的优先级

                .tasksProcessingOrder(QueueProcessingType.FIFO) // default

                .denyCacheImageMultipleSizesInMemory()

                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) //可以通过自己的内存缓存实现

                .memoryCacheSize(2 * 1024 * 1024)  // 内存缓存的最大值

                .memoryCacheSizePercentage(13) // default

                .diskCache(new UnlimitedDiskCache(cacheDir)) // default 可以自定义缓存路径

                .diskCacheSize(100 * 1024 * 1024) // 100Mb sd卡(本地)缓存的最大值

                .diskCacheFileCount(100)  // 可以缓存的文件数量

                // default为使用HASHCODE对UIL进行加密命名， 还可以用MD5(new Md5FileNameGenerator())加密
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())

                .imageDownloader(new BaseImageDownloader(context))

                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())

                .writeDebugLogs() // 打印debug log

                .build(); //开始构建

        //初始化
        ImageLoader.getInstance().init(config);


    }

    public static DisplayImageOptions getDefaultOption() {

        DisplayImageOptions options = new DisplayImageOptions.Builder()

                .showImageOnLoading(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片

                .showImageForEmptyUri(R.mipmap.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片

                .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片

                .resetViewBeforeLoading(true)  // default 设置图片在加载前是否重置、复位

                .cacheInMemory(true) // default  设置下载的图片是否缓存在内存中

                .cacheOnDisk(true) // default  设置下载的图片是否缓存在SD卡中

                .considerExifParams(true) // default

                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default 设置图片以如何的编码方式显示

                .bitmapConfig(Bitmap.Config.ARGB_8888) // default 设置图片的解码类型

                .displayer(new SimpleBitmapDisplayer()) // default  还可以设置圆角图片new RoundedBitmapDisplayer(20)

                .build();

        return options;

    }
}