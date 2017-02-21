package com.jieleo.projecta.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.homepage.TitleBean;

import java.util.List;

/**
 * Created by jie on 2017/2/20.
 */

public class TestAdapter extends BaseAdapter {
    private Context context;
    private List<TitleBean.DataBean.ChannelsBean> channelsBeen;

    public void setChannelsBeen(List<TitleBean.DataBean.ChannelsBean> channelsBeen) {
        this.channelsBeen = channelsBeen;
        notifyDataSetChanged();
    }

    public TestAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return channelsBeen!=null?channelsBeen.size():0;
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
        MyHolder myHolder=null;
        if (convertView==null){
        convertView= LayoutInflater.from(context).inflate(R.layout.item_popupwindow,parent,false);
            myHolder=new MyHolder(convertView);
            convertView.setTag(myHolder);
        }else {
            myHolder= (MyHolder) convertView.getTag();
        }
        myHolder.textView.setText(channelsBeen.get(position).getName());

        return convertView;
    }


    class MyHolder {
        TextView textView;

        public MyHolder(View itemView) {
            textView= (TextView) itemView.findViewById(R.id.tv_popup_window);
        }
    }

}
