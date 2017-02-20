package com.jieleo.projecta.adapter.category.single;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.category.SingleBean;

/**
 * Created by yuyongjie on 17/2/17.
 */


public class LeftListViewAdapter extends BaseAdapter {
    private SingleBean singleBean;
    private Context context;
    private int  selectedId;

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
        notifyDataSetChanged();
    }

    public void setSingleBean(SingleBean singleBean) {
        this.singleBean = singleBean;
        notifyDataSetChanged();
    }

    public LeftListViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return singleBean!=null?singleBean.getData().getCategories().size():0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder holder=null;
        convertView=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_left_single_page,parent,false);
            holder=new MyHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (MyHolder) convertView.getTag();
        }
        holder.textView.setText(singleBean.getData().getCategories().get(position).getName());

        if (selectedId==position){
            holder.textView.setTextColor(Color.RED);
        }
        return convertView;
    }
    class  MyHolder {
        TextView textView;

        public MyHolder(View itemView) {
            textView = (TextView) itemView.findViewById(R.id.tv_name_left_single_page);
        }
    }
}
