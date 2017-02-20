package com.jieleo.projecta.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.jieleo.projecta.adapter.category.strategy.StrategyDownRecyclerViewAdapter;
import com.jieleo.projecta.adapter.category.strategy.StrategyUpRecyclerViewAdapter;
import com.jieleo.projecta.bean.category.StrategyDownBean;
import com.jieleo.projecta.bean.category.StrategyUpBean;
import com.jieleo.projecta.bean.homepage.BannerBean;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyongjie on 17/2/14.
 */


public class BaseViewHolder extends RecyclerView.ViewHolder {
    private View mview;

    private SparseArray<View> sparseArray;

    private Context mContext;

    public BaseViewHolder(View itemView, Context context) {
        super(itemView);
        mview = itemView;
        sparseArray = new SparseArray<>();
        mContext = context;
    }

    public static BaseViewHolder createViewHolder(Context context, ViewGroup group, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, group, false);
        BaseViewHolder holder = new BaseViewHolder(itemView, context);
        return holder;
    }

    public static BaseViewHolder createGridViewholder(View view, ViewGroup group, int layoutId) {
        BaseViewHolder baseViewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(group.getContext()).inflate(layoutId, group, false);
            baseViewHolder = new BaseViewHolder(view, group.getContext());
            view.setTag(baseViewHolder);
        } else {
            baseViewHolder = (BaseViewHolder) view.getTag();
        }
        return baseViewHolder;
    }

    public <T extends View> T getView(int resId) {
        View view = sparseArray.get(resId);
        if (view == null) {
            view = itemView.findViewById(resId);
            sparseArray.put(resId, view);
        }
        return (T) view;
    }

    public BaseViewHolder setText(int resId, String string) {
        TextView textView = getView(resId);
        if (string != null) {
            textView.setText(string);
        }
        return this;
    }

    public BaseViewHolder setCustromImage(int resId, String url) {
        ImageView imageView = getView(resId);
        if (url != null) {
            Glide.with(mContext).load(url).into(imageView);
        }
        return this;
    }

    public BaseViewHolder setLocalImage(int id,int resId){
        ImageView imageView=getView(id);
        if (resId!=0){
            imageView.setImageResource(resId);
        }
        return this;
    }


    public BaseViewHolder setCircleImage(int resId, String url) {
        ImageView imageView = getView(resId);
        if (url != null) {
            Glide.with(mContext).load(url).transform(new CircleTransform(mContext)).into(imageView);
        }
        return this;
    }


    public BaseViewHolder setHorizonalRecyclerView(int resId, String url) {
        RecyclerView recyclerView = getView(resId);
        if (url != null) {
            final StrategyUpRecyclerViewAdapter strategyUpRecyclerViewAdapter = new StrategyUpRecyclerViewAdapter(mContext);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(strategyUpRecyclerViewAdapter);
            NetTool.getInstance().startRequest(url, StrategyUpBean.class, new CallBack<StrategyUpBean>() {
                @Override
                public void onSuccess(StrategyUpBean response) {
                    StrategyUpBean strategyUpBean = response;
                    strategyUpRecyclerViewAdapter.setStrategyUpBean(strategyUpBean);
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        }
        return this;
    }

    public BaseViewHolder setVerticalRecyclerView(int resId, StrategyDownBean.DataBean.ChannelGroupsBean channelGroupsBean) {
        RecyclerView recyclerView = getView(resId);
        if (channelGroupsBean != null) {
            StrategyDownRecyclerViewAdapter strategyDownRecyclerViewAdapter = new StrategyDownRecyclerViewAdapter(mContext);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(strategyDownRecyclerViewAdapter);
            strategyDownRecyclerViewAdapter.setChannelGroupsBean(channelGroupsBean);
        }
        return this;
    }


    public BaseViewHolder setBanner(int resId, String url) {
        final Banner banner = getView(resId);
        if (url != null) {
            NetTool.getInstance().startRequest(url, BannerBean.class, new CallBack<BannerBean>() {
                @Override
                public void onSuccess(BannerBean response) {
                    BannerBean bannerBean = response;
                    List<String> bannerRes = new ArrayList<String>();
                    for (int i = 0; i < bannerBean.getData().getBanners().size(); i++) {
                        bannerRes.add(bannerBean.getData().getBanners().get(i).getImage_url());
                    }
                    banner.setImageLoader(new ImageLoader());
                    banner.setImages(bannerRes);
                    banner.isAutoPlay(true);
                    banner.setDelayTime(3000);
                    banner.setIndicatorGravity(BannerConfig.CENTER);
                    banner.start();
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        }

        return this;
    }


    class ImageLoader extends com.youth.banner.loader.ImageLoader {


        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }

    }


    //实现圆形头像的内部类
    public static class CircleTransform extends BitmapTransformation {

        public CircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleBitmap(pool, toTransform);
        }

        @Override
        public String getId() {
            return getClass().getName();
        }

        private static Bitmap circleBitmap(BitmapPool pool, Bitmap sorece) {
            if (sorece == null) {
                return sorece;
            }
            int size = Math.min(sorece.getWidth(), sorece.getHeight());
            int x = (sorece.getWidth() - size) / 2;
            int y = (sorece.getHeight() - size) / 2;

            Bitmap squared = Bitmap.createBitmap(sorece, x, y, size, size);
            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }
    }

}
