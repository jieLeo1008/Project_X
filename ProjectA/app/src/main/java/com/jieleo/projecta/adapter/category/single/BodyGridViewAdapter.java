package com.jieleo.projecta.adapter.category.single;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.category.SingleBean;

/**
 * Created by yuyongjie on 17/2/17.
 */


public class BodyGridViewAdapter extends BaseAdapter {
    private static final String TAG = "BodyGridViewAdapter";
    private SingleBean.DataBean.CategoriesBean categoriesBean;
    private Context context;

    public void setCategoriesBean(SingleBean.DataBean.CategoriesBean categoriesBean) {
        this.categoriesBean = categoriesBean;
        notifyDataSetChanged();
    }

    public BodyGridViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return categoriesBean!=null?categoriesBean.getSubcategories().size():0;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.item_grid_right_single_page,parent,false);
            myHolder=new MyHolder(convertView);
            convertView.setTag(myHolder);
        }else {
            myHolder= (MyHolder) convertView.getTag();
        }
        myHolder.textView.setText(categoriesBean.getSubcategories().get(position).getName());
        Glide.with(context).load(categoriesBean.getSubcategories().get(position).getIcon_url()).into(myHolder.imageView);
        return convertView;
    }

    class MyHolder{
        TextView textView;
        ImageView imageView;

        public MyHolder(View itemView) {
            textView = (TextView) itemView.findViewById(R.id.tv_grid_body_single_page);
            imageView= (ImageView) itemView.findViewById(R.id.iv_grid_body_single_page);
        }
    }
}
