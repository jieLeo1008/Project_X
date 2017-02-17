package com.jieleo.projecta.adapter.category.single;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.category.SingleBean;
import com.jieleo.projecta.custromview.MyGridView;

/**
 * Created by yuyongjie on 17/2/17.
 */


public class RightListViewAdapter extends BaseAdapter{
    private static final String TAG = "RightListViewAdapter";
    private SingleBean singleBean;
    private Context context;

    public void setSingleBean(SingleBean singleBean) {
        this.singleBean = singleBean;
        notifyDataSetChanged();
    }

    public RightListViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return singleBean!=null?singleBean.getData().getCategories().size():0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position==0){
            FirstHolder firstHolder=null;
            if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.item_head_right_single_page,parent,false);
                firstHolder=new FirstHolder(convertView);
                convertView.setTag(firstHolder);
            }else {
                firstHolder= (FirstHolder) convertView.getTag();
            }
            HeadGridViewAdapter headGridViewAdapter=new HeadGridViewAdapter(context);
            firstHolder.gridView.setAdapter(headGridViewAdapter);
            headGridViewAdapter.setCategoriesBean(singleBean.getData().getCategories().get(position));
            convertView=null;
        }else {
            MyHolder holder=null;
            if (convertView==null){
                convertView=LayoutInflater.from(context).inflate(R.layout.item_body_right_single_page,parent,false);
                holder=new MyHolder(convertView);
                convertView.setTag(holder);
            }else {
                holder= (MyHolder) convertView.getTag();
            }
//            holder.textView.setText(singleBean.getData().getCategories().get(position).getName());
            BodyGridViewAdapter bodyGridViewAdapter=new BodyGridViewAdapter(context);
            holder.gridView.setAdapter(bodyGridViewAdapter);
            bodyGridViewAdapter.setCategoriesBean(singleBean.getData().getCategories().get(position));
        }
        return convertView;
    }

    class MyHolder {
        MyGridView gridView;
        TextView textView;

        public MyHolder(View itemView) {
            gridView = (MyGridView) itemView.findViewById(R.id.grid_view_right_single_page);
            textView = (TextView) itemView.findViewById(R.id.tv_name_right_single_page);
        }



    }

    class FirstHolder{
        GridView gridView;

        public FirstHolder(View itemView) {
            gridView= (GridView) itemView.findViewById(R.id.grid_view_head);
        }
    }
}
