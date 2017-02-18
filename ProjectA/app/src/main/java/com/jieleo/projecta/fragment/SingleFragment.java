package com.jieleo.projecta.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jieleo.projecta.R;
import com.jieleo.projecta.adapter.category.single.LeftListViewAdapter;
import com.jieleo.projecta.adapter.category.single.RightListViewAdapter;
import com.jieleo.projecta.bean.category.SingleBean;
import com.jieleo.projecta.inter.CallBack;
import com.jieleo.projecta.tool.NetTool;
import com.jieleo.projecta.website.WebsiteInter;

/**
 * Created by jie on 2017/2/16.
 */

public class SingleFragment extends BaseFragment implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    private ListView leftListView ,rightListView;
    private LeftListViewAdapter leftAdapter;
    private RightListViewAdapter rightAdapter;
    private static final String TAG = "SingleFragment";

    private int lastPosition;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_single_page;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        leftListView = (ListView) view.findViewById(R.id.list_view_left);
        rightListView = (ListView) view.findViewById(R.id.list_view_right);
        leftAdapter=new LeftListViewAdapter(getContext());
        rightAdapter=new RightListViewAdapter(getContext());
        leftListView.setAdapter(leftAdapter);
        rightListView.setAdapter(rightAdapter);
    }

    @Override
    protected void initData() {
        NetTool.getInstance().startRequest(WebsiteInter.SINGLE, SingleBean.class, new CallBack<SingleBean>() {
            @Override
            public void onSuccess(SingleBean response) {
                    SingleBean singleBean=response;
                rightAdapter.setSingleBean(singleBean);
                leftAdapter.setSingleBean(singleBean);
            }

            @Override
            public void onError(Throwable e) {

            }
        });


        leftListView.setOnItemClickListener(this);
        rightListView.setOnScrollListener(this);
        rightListView.getFirstVisiblePosition();

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        rightListView.setSelection(position);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
//            switch (scrollState){
//                //当滚动停止时
//                case SCROLL_STATE_IDLE:
//                    leftListView.setSelection(view.getFirstVisiblePosition());
//                    leftAdapter.setSelectedId(view.getFirstVisiblePosition());
//                    Log.e(TAG, "onScrollStateChanged: SCROLL_STATE_IDLE");
//                    break;
//                //触摸滚动  手指没离开
//                case SCROLL_STATE_TOUCH_SCROLL:
//                    Log.e(TAG, "onScrollStateChanged: SCROLL_STATE_TOUCH_SCROLL");
//                    leftListView.setSelection(view.getFirstVisiblePosition());
//                    leftAdapter.setSelectedId(view.getFirstVisiblePosition());
//                    break;
//                //用力滑动 Listview继续滚动的状态
//                case SCROLL_STATE_FLING:
//                    break;
//            }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        Log.d(TAG, "view.getFirstVisiblePosition():" + view.getFirstVisiblePosition());
//        leftListView.smoothScrollToPosition(firstVisibleItem);
        int nowPosition = view.getFirstVisiblePosition();
        if(nowPosition != lastPosition){
            leftListView.setSelection(nowPosition);
            leftAdapter.setSelectedId(nowPosition);
            leftListView.smoothScrollToPositionFromTop(nowPosition,100);
        }
        lastPosition = nowPosition;

    }
}
