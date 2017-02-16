package com.jieleo.projecta.adapter.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.BaseViewHolder;
import com.jieleo.projecta.bean.category.StrategyUpBean;

/**
 * Created by jie on 2017/2/16.
 */

public class StrategyUpRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "StrategyUpRecyclerViewA";
    private Context context;
    private StrategyUpBean strategyUpBean;

    public void setStrategyUpBean(StrategyUpBean strategyUpBean) {
        this.strategyUpBean = strategyUpBean;
        notifyDataSetChanged();
    }

    public StrategyUpRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createViewHolder(context,parent, R.layout.item_strategy_details_up_page);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder: "+strategyUpBean.getData().getColumns().get(position).getCover_image_url() );
            holder.setCustromImage(R.id.iv_item_strategy_details_up_page,strategyUpBean.getData().getColumns().get(position).getCover_image_url());
    }

    @Override
    public int getItemCount() {
        return strategyUpBean!=null?strategyUpBean.getData().getColumns().size():0;
    }
}
