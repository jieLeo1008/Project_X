package com.jieleo.projecta.adapter.mall;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.BaseViewHolder;
import com.jieleo.projecta.bean.category.StrategyDownBean;
import com.jieleo.projecta.bean.category.StrategyUpBean;

/**
 * Created by yuyongjie on 17/3/4.
 */


public class ColumnRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private StrategyUpBean strategyUpBean;
    private Context context;

    public ColumnRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setStrategyUpBean(StrategyUpBean strategyUpBean) {
        this.strategyUpBean = strategyUpBean;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createViewHolder(context,parent, R.layout.item_recycler_view_column);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
            holder.setCustromImage(R.id.iv_cover_image_column,strategyUpBean.getData().getColumns().get(position).getCover_image_url());
            holder.setText(R.id.tv_title_column,strategyUpBean.getData().getColumns().get(position).getTitle());
            holder.setText(R.id.tv_author_column,strategyUpBean.getData().getColumns().get(position).getAuthor());
        ((CheckBox)holder.getView(R.id.checkbox_column)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    buttonView.setText("已关注");
                    buttonView.setBackgroundColor(Color.parseColor("#FFECEC"));
                }else {
                    buttonView.setText("未关注");
                    buttonView.setBackgroundColor(Color.WHITE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return strategyUpBean!=null?strategyUpBean.getData().getColumns().size():0;
    }
}
