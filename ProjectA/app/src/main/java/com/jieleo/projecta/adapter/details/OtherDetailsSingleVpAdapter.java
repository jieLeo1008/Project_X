package com.jieleo.projecta.adapter.details;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jieleo.projecta.MyApp;
import com.jieleo.projecta.R;

import java.util.List;

/**
 * Created by yuyongjie on 17/3/1.
 */


public class OtherDetailsSingleVpAdapter extends PagerAdapter {
    private List<String> imageUrls;

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return imageUrls!=null?imageUrls.size()+1:0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(position<imageUrls.size()){
        ImageView imageView=new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(MyApp.getmContext()).load(imageUrls.get(position)).into(imageView);
        container.addView(imageView);
        return imageView;
        }else {
            View hintView= LayoutInflater.from(container.getContext()).inflate(R.layout.single_top_vp,container,false);
            ImageView imageView= (ImageView) hintView.findViewById(R.id.iv_hint);
            TextView textView= (TextView) hintView.findViewById(R.id.tv_hint);
            container.addView(hintView);
            return hintView;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView imageView=new ImageView(container.getContext());
        container.removeView(imageView);
    }


}
