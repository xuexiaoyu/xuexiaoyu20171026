package com.example.administrator.xuexiaoyu20171026.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xuexiaoyu20171026.R;

import java.util.List;

/**
 * Created by maohuawei on 2017/10/26.
 */

public class MainActivityListViewAdapter extends BaseAdapter {

    //成员
    private Context context;
    private List<String> list;

    //构造方法
    public MainActivityListViewAdapter(Context context, List<String> list) {
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
        //判断孔
        if (convertView == null) {
            //实例化
            holder = new ViewHolder();
            //填充视图
            convertView = View.inflate(context, R.layout.item_listview_adapter, null);
            //查找控件
            holder.image_iteml = convertView.findViewById(R.id.image_item);
            //查找控件
            holder.tv_item = convertView.findViewById(R.id.tv_item);
            //设置tag
            convertView.setTag(holder);

        } else {
            //获取tag
            holder = (ViewHolder) convertView.getTag();

        }

        //设置文本

        holder.tv_item.setText(list.get(position));
        //设置图片
        holder.image_iteml.setImageResource(R.mipmap.ic_launcher);
        //返回
        return convertView;
    }

    /**
     * ViewHolder优化类
     */
    private static class ViewHolder {
        //成员
        private ImageView image_iteml;

        private TextView tv_item;

    }


}
