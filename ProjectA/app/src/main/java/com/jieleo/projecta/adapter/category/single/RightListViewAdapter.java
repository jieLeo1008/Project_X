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
    private final int TYPE_FIRST = 0;

    private final int TYPE_NORMAL = 1;

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
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position==0){
            convertView=LayoutInflater.from(context).inflate(R.layout.item_head_right_single_page,parent,false);
            MyGridView gridView= (MyGridView) convertView.findViewById(R.id.grid_view_head);

            HeadGridViewAdapter headGridViewAdapter=new HeadGridViewAdapter(context);
            gridView.setAdapter(headGridViewAdapter);
            headGridViewAdapter.setCategoriesBean(singleBean.getData().getCategories().get(position));
        }else {
            MyHolder holder=null;
            if (convertView==null){
                convertView=LayoutInflater.from(context).inflate(R.layout.item_body_right_single_page,parent,false);
                holder=new MyHolder(convertView);
                convertView.setTag(holder);
            }else {
                holder= (MyHolder) convertView.getTag();
            }
            holder.textView.setText(singleBean.getData().getCategories().get(position).getName());
            BodyGridViewAdapter bodyGridViewAdapter=new BodyGridViewAdapter(context);
            holder.gridView.setAdapter(bodyGridViewAdapter);
            bodyGridViewAdapter.setCategoriesBean(singleBean.getData().getCategories().get(position));
        }
        return convertView;
    }

    //返回对应的行布局类型
    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_FIRST;
        }
        return TYPE_NORMAL;
    }

    //返回当前的行布局总数
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    class MyHolder {
        MyGridView gridView;
        TextView textView;

        public MyHolder(View itemView) {
            gridView = (MyGridView) itemView.findViewById(R.id.grid_view_right_single_page);
            textView = (TextView) itemView.findViewById(R.id.tv_name_right_single_page);
        }



    }

}
