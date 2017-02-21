package com.jieleo.projecta.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.jieleo.projecta.R;

public class SearchActivity extends BaseActivity {
    private EditText etSearch;

    @Override
    public int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        etSearch = (EditText) findViewById(R.id.et_search);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {
            etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    switch (event.getAction()){
                    }
                    return false;
                }
            });
    }
}
