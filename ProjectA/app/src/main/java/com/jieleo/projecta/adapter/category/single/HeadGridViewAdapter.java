package com.jieleo.projecta.adapter.category.single;

import android.content.Context;
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


public class HeadGridViewAdapter extends BaseAdapter {
    private SingleBean.DataBean.CategoriesBean categoriesBean;
    private Context context;

    public HeadGridViewAdapter(Context context) {
        this.context = context;
    }

    public void setCategoriesBean(SingleBean.DataBean.CategoriesBean categoriesBean) {
        this.categoriesBean = categoriesBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return categoriesBean!=null?categoriesBean.getSubcategories().size():0;
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
        MyHolder holder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_grid_head_right_single_page,parent,false);
            holder=new MyHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (MyHolder) convertView.getTag();
        }
        holder.textView.setText(categoriesBean.getSubcategories().get(position).getName());
        Glide.with(context).load(categoriesBean.getSubcategories().get(position).getIcon_url()).into(holder.imageView);
        return convertView;
    }
    class  MyHolder {
        TextView textView;
        ImageView imageView;
        public MyHolder(View itemView) {
            textView = (TextView) itemView.findViewById(R.id.tv_grid_head_right_single_page);
            imageView= (ImageView) itemView.findViewById(R.id.iv_grid_head_right_single_page);
        }
    }
}
