package com.jieleo.projecta.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.category.single.SingleListDetailsRecyclerAdapter;
import com.jieleo.projecta.bean.category.SingleBean;
import com.jieleo.projecta.bean.category.SingleListDetailsBean;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.website.WebsiteInter;

import java.util.List;

public class SingleListDetailsActivity extends BaseActivity {
    private ImageView backIv,sortIv;
    private TextView titleTv;
    private LRecyclerView recyclerView;
    private SingleBean.DataBean.CategoriesBean.SubcategoriesBean subcategoriesBean;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private SingleListDetailsRecyclerAdapter singleListDetailsRecyclerAdapter;
    private String nextUrl;
    private List<SingleListDetailsBean.DataBean.ItemsBean> itemsBeen;

    @Override
    public int setLayout() {
        return R.layout.activity_single_list;
    }

    @Override
    protected void initView() {
        backIv = (ImageView) findViewById(R.id.iv_back_single_list_details);
        sortIv = (ImageView) findViewById(R.id.iv_sort_single_list_details);
        titleTv = (TextView) findViewById(R.id.tv_title_single_list_details);
        recyclerView = (LRecyclerView) findViewById(R.id.recycler_view_single_list);
        singleListDetailsRecyclerAdapter=new SingleListDetailsRecyclerAdapter(this);
        lRecyclerViewAdapter=new LRecyclerViewAdapter(singleListDetailsRecyclerAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(lRecyclerViewAdapter);

    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("bundle");
        subcategoriesBean=bundle.getParcelable("categoriesBean");
        titleTv.setText(subcategoriesBean.getName());
        String url= WebsiteInter.SINGE_SECOND_UP+subcategoriesBean.getId()+WebsiteInter.SINGE_SECOND_DOWN;
        NetTool.getInstance().startRequest(url, SingleListDetailsBean.class, new CallBack<SingleListDetailsBean>() {
            @Override
            public void onSuccess(SingleListDetailsBean response) {
                itemsBeen = response.getData().getItems();
                singleListDetailsRecyclerAdapter.setItemsBeen(itemsBeen);
                nextUrl=response.getData().getPaging().getNext_url();
            }

            @Override
            public void onError(Throwable e) {

            }
        });

        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (nextUrl!=null){
                    NetTool.getInstance().startRequest(nextUrl, SingleListDetailsBean.class, new CallBack<SingleListDetailsBean>() {
                        @Override
                        public void onSuccess(SingleListDetailsBean response) {
                            itemsBeen.addAll(response.getData().getItems());
                            singleListDetailsRecyclerAdapter.notifyDataSetChanged();
                            recyclerView.setNoMore(false);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
                }else {
                    recyclerView.setNoMore(true);
                }
            }
        });

        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void bindEvent() {

    }
}
