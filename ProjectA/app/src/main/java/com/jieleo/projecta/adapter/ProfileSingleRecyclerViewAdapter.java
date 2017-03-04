package com.jieleo.projecta.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.jieleo.projecta.R;
import com.jieleo.projecta.bean.greendao.Enshrine;
import com.jieleo.projecta.inter.OnClickListenerInter;

import java.util.List;

/**
 * Created by yuyongjie on 17/3/4.
 */


public class ProfileSingleRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    private List<Enshrine>  enshrines;
    private OnClickListenerInter onClickListenerInter;
    private static final int NORMALTYPE=1;
    private static final int ENDTYPE=2;

    public void setOnClickListenerInter(OnClickListenerInter onClickListenerInter) {
        this.onClickListenerInter = onClickListenerInter;
    }

    public ProfileSingleRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setEnshrines(List<Enshrine> enshrines) {
        this.enshrines = enshrines;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case NORMALTYPE:
                return BaseViewHolder.createViewHolder(context,parent, R.layout.item_profile_single_recycler_view);
            case ENDTYPE:
                return BaseViewHolder.createViewHolder(context,parent,R.layout.item_show_more_profile);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        if (position<7){
        holder.setCustromImage(R.id.iv_cover_image_profile_single,enshrines.get(position).getEnshrineCoverImage());
            holder.getView(R.id.iv_cover_image_profile_single).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListenerInter.onItemClickListener(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (enshrines!=null){
            if (enshrines.size()<8){
                return enshrines.size();
            }else {
                return 8;
            }

        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position<7){
        return NORMALTYPE;
        }else {
            return ENDTYPE;
        }

    }
}
