package com.example.administrator.xuexiaoyu20171026.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xuexiaoyu20171026.R;
import com.example.administrator.xuexiaoyu20171026.bean.PullToRefreshListViewDataBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by maohuawei on 2017/10/26.
 */

public class MainPullToRefreshListViewAdapter extends BaseAdapter {

    //成员
    private Context context;
    //集合
    private List<PullToRefreshListViewDataBean.DataBean> list;

    public MainPullToRefreshListViewAdapter(Context context, List<PullToRefreshListViewDataBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        //返回大小
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //ViewHolder对象
        ViewHolder holder = null;
        //判断空
        if (convertView == null) {
            //实例化
            holder = new ViewHolder();
            //填充视图
            convertView = View.inflate(context, R.layout.item_pulltorefreshlistview_adapter, null);
            //查找控件
            holder.image_itme = convertView.findViewById(R.id.image_item);
            //查找控件
            holder.tv_item = convertView.findViewById(R.id.tv_item);
            //设置tag
            convertView.setTag(holder);

        } else {
            //获取tag
            holder = (ViewHolder) convertView.getTag();
        }
        //设置信息
        holder.tv_item.setText(list.get(position).getTITLE());
        //判断空
        if (list.get(position).getIMAGEURL() != null) {
            //加载图片
            ImageLoader.getInstance().displayImage((String) list.get(position).getIMAGEURL(), holder.image_itme);
        }

        return convertView;
    }

    /**
     * ViewHolder优化类
     */
    private static class ViewHolder {
        //成员
        private ImageView image_itme;
        private TextView tv_item;

    }
}
