package com.yzbz.myadressbook.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.yzbz.myadressbook.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by senjucao on 2016/1/31.
 */
public class AdapterContacts extends BaseAdapter implements Filterable {
    private NameFilter mNameFilter;
    private List<String> originaList; //保存原始数据

    private LayoutInflater mInflater;
    private List<String> dataList;



    private StringBuffer buffer = new StringBuffer(); //用来保存第一次字母的索引
    private List<String> firdList = new ArrayList<String>(); //用来保存索引值对下的索引名称

    public AdapterContacts(Context context, List<String> dataList) {
        this.dataList = dataList;
        mInflater = LayoutInflater.from(context);
        originaList=new ArrayList<String>(dataList);
    }
    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_lv_contacts_layout, null, true);
            viewHolder.tv_sort = (TextView) convertView.findViewById(R.id.tv_sort);
            viewHolder.tv_username = (TextView) convertView.findViewById(R.id.tv_user_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String key = dataList.get(position).substring(0, 1);
        if (buffer.indexOf(key) == -1) {
            buffer.append(key);
            firdList.add(dataList.get(position));
        }
        if (firdList.contains(dataList.get(position))) {
            viewHolder.tv_sort.setText(key);
            viewHolder.tv_sort.setVisibility(View.VISIBLE);
            viewHolder.tv_username.setText(dataList.get(position));
        } else {
            viewHolder.tv_sort.setVisibility(View.GONE);
            viewHolder.tv_username.setText(dataList.get(position));
        }
        return convertView;
    }
    private final class ViewHolder {
        TextView tv_sort;
        TextView tv_username;
    }

    @Override
    public Filter getFilter() {
        if (mNameFilter == null) {
            mNameFilter = new NameFilter();
        }
        return mNameFilter;
    }

    //过滤数据
    class NameFilter extends Filter {
        //执行筛选
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            List<String> mFilteredArrayList=new ArrayList<String>();
            for (Iterator<String> iterator = originaList.iterator(); iterator.hasNext(); ) {
                String name = iterator.next();
                if (name.contains(charSequence)) {
                    mFilteredArrayList.add(name);
                }
            }
            filterResults.values = mFilteredArrayList;
            return filterResults;
        }

        //刷新操作
        @Override
        protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
             dataList= (List<String>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }

        }
    }

}
