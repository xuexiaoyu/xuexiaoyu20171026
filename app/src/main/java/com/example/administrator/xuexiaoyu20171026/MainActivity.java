package com.example.administrator.xuexiaoyu20171026;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.example.administrator.xuexiaoyu20171026.adapter.MainActivityListViewAdapter;
import com.example.administrator.xuexiaoyu20171026.adapter.MainPullToRefreshListViewAdapter;
import com.example.administrator.xuexiaoyu20171026.bean.PullToRefreshListViewDataBean;
import com.example.administrator.xuexiaoyu20171026.inter.JSONCallBack;
import com.example.administrator.xuexiaoyu20171026.util.NetWorkDataUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private DrawerLayout drawer_layout;
    private ListView list_view;
    private List<String> drawer_menudatalist;
    private MainActivityListViewAdapter adapter;
    private PullToRefreshListView pullToRefreshListView;
    private ILoadingLayout loadingLayoutProxy;
    private List<PullToRefreshListViewDataBean.DataBean> pullToRefreshListViewDataList = new ArrayList<>();
    private MainPullToRefreshListViewAdapter pullAdapter;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        initView();

        //设置侧滑
        setDrawerLayout();
        //设置PullToRefreshListView
        setPullToRefreshListView();
    }


    /**
     * 设置PullToRefreshListView
     */
    private void setPullToRefreshListView() {
        //设置支持上拉和下拉
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        //获取操作下拉的pullToRefreshListView
        loadingLayoutProxy = pullToRefreshListView.getLoadingLayoutProxy(true, false);

        //设置信息
        loadingLayoutProxy.setPullLabel("下拉刷新");
        loadingLayoutProxy.setRefreshingLabel("正在刷新...");
        loadingLayoutProxy.setReleaseLabel("放开刷新");

        loadingLayoutProxy = pullToRefreshListView.getLoadingLayoutProxy(false, true);
        //设置信息
        loadingLayoutProxy.setPullLabel("上拉加载");
        loadingLayoutProxy.setRefreshingLabel("正在刷新...");
        loadingLayoutProxy.setReleaseLabel("放开加载");

        getInitData();

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //回去数据
                getInitData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //标记增加
                page += 20;
                //加载数据
                getLoadData();

            }
        });
    }

    private void getLoadData() {
        //使用完了请求工具类请求书库
        NetWorkDataUtil.getData(MainActivity.this, "http://www.93.gov.cn/93app/data.do?channelId=0&startNum=" + page + "", new JSONCallBack() {
            @Override
            public void callJSON(String json) {

                //判断数据是否为空
                if (json != null) {

                    //创建Gson对象
                    Gson gson = new Gson();
                    //解析
                    PullToRefreshListViewDataBean pullToRefreshListViewDataBean = gson.fromJson(json, PullToRefreshListViewDataBean.class);
                    //获取对象
                    List<PullToRefreshListViewDataBean.DataBean> data = pullToRefreshListViewDataBean.getData();

                    //添加数据
                    pullToRefreshListViewDataList.addAll(data);
                    //停止刷新
                    pullToRefreshListView.onRefreshComplete();

                    //设置适配器
                    setPullToRefreshListViewAdapter();


                }
            }
        });
    }
    //获取初始化数据
    private void getInitData() {
        //使用网络数据请求工具类请求数据
        NetWorkDataUtil.getData(MainActivity.this, "http://www.93.gov.cn/93app/data.do?channelId=0&startNum=0", new JSONCallBack() {
            @Override
            public void callJSON(String json) {

                //判断请求的数据是否为空
                if (json != null) {
                    //创建Gson对象
                    Gson gson = new Gson();
                    //解析数据
                    PullToRefreshListViewDataBean pullToRefreshListViewDataBean = gson.fromJson(json, PullToRefreshListViewDataBean.class);
                    //回去对象
                    List<PullToRefreshListViewDataBean.DataBean> data = pullToRefreshListViewDataBean.getData();
                    //清空集合
                    pullToRefreshListViewDataList.clear();
                    //添加数据库
                    pullToRefreshListViewDataList.addAll(0, data);
                    //停止刷新
                    pullToRefreshListView.onRefreshComplete();
                    //设置适配器
                    setPullToRefreshListViewAdapter();
                }
            }
        });
    }

    private void setPullToRefreshListViewAdapter() {
        //判断适配器是否为空
        if (pullAdapter == null) {
            //实例化适配器
            pullAdapter = new MainPullToRefreshListViewAdapter(MainActivity.this, pullToRefreshListViewDataList);
            //设置适配器
            pullToRefreshListView.setAdapter(pullAdapter);

        } else {
            //刷新适配器
            pullAdapter.notifyDataSetChanged();
        }
    }

    private void setDrawerLayout() {
        //实例化集合
        drawer_menudatalist = new ArrayList<>();
        //添加数据
        drawer_menudatalist.add("我的消息");
        drawer_menudatalist.add("教学视频");
        drawer_menudatalist.add("我的成绩");
        drawer_menudatalist.add("学车日记");
        drawer_menudatalist.add("文章收藏");
        drawer_menudatalist.add("文章足迹");
        drawer_menudatalist.add("教员中心");
        drawer_menudatalist.add("设置");
        //实例化适配器
        adapter = new MainActivityListViewAdapter(this, drawer_menudatalist);

        //设置适配器
        list_view.setAdapter(adapter);

        //设置侧滑监听
        drawer_layout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                //打印日志
                Log.i(TAG, "onDrawerOpened: 抽屉打开");
            }
            @Override
            public void onDrawerClosed(View drawerView) {

                //打印日志
                Log.i(TAG, "onDrawerOpened: 抽屉关闭");
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }
    private void initView() {
        //初始控件
        drawer_layout = (DrawerLayout) findViewById(R.id.drawerlayout);
        list_view = (ListView) findViewById(R.id.list_view);
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pullToRefreshListView);
    }
}
