package com.jieleo.projecta.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.homepage.DetialsBean;

/**
 * Created by yuyongjie on 17/2/13.
 */


public class DetailsRecyclerViewAdapter extends RecyclerView.Adapter<DetailsRecyclerViewAdapter.MainHolder> {
    private DetialsBean detialsBean;
    private Context context;

    public DetailsRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setDetialsBean(DetialsBean detialsBean) {
        this.detialsBean = detialsBean;
        notifyDataSetChanged();
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_details_home_page, parent, false);
        MainHolder mainHolder = new MainHolder(itemView);
        return mainHolder;
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position) {
        DetialsBean.DataBean.ItemsBean itemsBean = detialsBean.getData().getItems().get(position);
        holder.nickNameTv.setText(itemsBean.getAuthor().getNickname());
        holder.autherIntroductionTv.setText(itemsBean.getAuthor().getIntroduction());
        if (itemsBean.getColumn() != null) {
            holder.columnTitleTv.setText(itemsBean.getColumn().getTitle());
            holder.columnTv.setText("栏目");
        } else {
            holder.columnTitleTv.setText("");
            holder.columnTv.setText("");
        }
        holder.titleTv.setText(itemsBean.getTitle());
        holder.introductionTv.setText(itemsBean.getIntroduction());
        holder.likesCountTv.setText(itemsBean.getLikes_count() + "");
//        Glide.with(context).load(itemsBean.getAuthor().getAvatar_url()).into(holder.autherHeadIv);
        Glide.with(context).load(itemsBean.getCover_image_url()).into(holder.coverImageIv);
        Glide.with(context).load(itemsBean.getAuthor().getAvatar_url()).transform(new CircleTransform(context)).into(holder.autherHeadIv);

    }

    //实现圆形头像的内部类
        public static class CircleTransform extends BitmapTransformation{

            public CircleTransform(Context context) {
                super(context);
            }

            @Override
            protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
                return circleBitmap(pool,toTransform);
            }

            @Override
            public String getId() {
                return getClass().getName();
            }

            private static Bitmap circleBitmap(BitmapPool pool,Bitmap sorece){
                if (sorece==null){
                    return sorece;
                }
                int size=Math.min(sorece.getWidth(),sorece.getHeight());
                int x=(sorece.getWidth()-size)/2;
                int y=(sorece.getHeight()-size)/2;

                Bitmap squared=Bitmap.createBitmap(sorece,x,y,size,size);
                Bitmap result=pool.get(size,size, Bitmap.Config.ARGB_8888);
                if (result==null){
                    result=Bitmap.createBitmap(size,size, Bitmap.Config.ARGB_8888);
                }

                Canvas canvas=new Canvas(result);
                Paint paint=new Paint();
                paint.setShader(new BitmapShader(squared,BitmapShader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                paint.setAntiAlias(true);
                float r=size/2f;
                canvas.drawCircle(r,r,r,paint);
                return  result;
            }
        }


    @Override
    public int getItemCount() {

        return detialsBean != null ? detialsBean.getData().getItems().size() : 0;
    }

    class MainHolder extends RecyclerView.ViewHolder {
        ImageView autherHeadIv, coverImageIv;
        TextView nickNameTv, autherIntroductionTv, columnTitleTv, likesCountTv, columnTv, titleTv, introductionTv;

        public MainHolder(View itemView) {
            super(itemView);
            autherHeadIv = (ImageView) itemView.findViewById(R.id.iv_author_head_item_details_home_page);
            coverImageIv = (ImageView) itemView.findViewById(R.id.iv_cover_image_item_details_home_page);
            nickNameTv = (TextView) itemView.findViewById(R.id.tv_author_nickname_item_details_home_page);
            autherIntroductionTv = (TextView) itemView.findViewById(R.id.tv_author_introduction_item_details_home_page);
            columnTitleTv = (TextView) itemView.findViewById(R.id.tv_column_title_details_home_page);
            likesCountTv = (TextView) itemView.findViewById(R.id.tv_likes_count_details_home_page);
            columnTv = (TextView) itemView.findViewById(R.id.tv_column_details_home_page);
            titleTv = (TextView) itemView.findViewById(R.id.tv_title_item_details_home_page);
            introductionTv = (TextView) itemView.findViewById(R.id.tv_introduction_item_details_home_page);
        }
    }

}
