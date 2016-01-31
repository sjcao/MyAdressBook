package com.yzbz.myadressbook.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yzbz.myadressbook.R;

/**
 * Created by senjucao on 2016/1/31.
 */
public class AdapterHome extends BaseAdapter{
    private LayoutInflater mInflater;
    public AdapterHome(Context context)
    {
    mInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        //返回20测试
        return 20;
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
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_lv_home_layout,null, true);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        return convertView;
    }
    private final class ViewHolder{

    }

}
