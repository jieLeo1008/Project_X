package com.jieleo.projecta.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.jieleo.projecta.R;
import com.jieleo.projecta.fragment.BaseFragment;
import com.jieleo.projecta.fragment.CategoryPageFragment;
import com.jieleo.projecta.fragment.HomePageFragment;
import com.jieleo.projecta.fragment.GiftPageFragmnet;
import com.jieleo.projecta.fragment.ProfilePageFragment;
import com.jieleo.projecta.fragment.MallPageFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "MainActivity";

    private RadioGroup radioGroup;

    private FragmentManager fragmentManager;

    private HomePageFragment homePageFragment;
    private GiftPageFragmnet giftPageFragmnet;
    private MallPageFragment mallPageFragment;
    private CategoryPageFragment categoryPageFragment;
    private ProfilePageFragment profilePageFragment;
    private FragmentTransaction fragmentTransaction,fmTransaction;

    private BaseFragment currentFragment;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.radio_group_main_activity);
        homePageFragment = new HomePageFragment();
        giftPageFragmnet = new GiftPageFragmnet();
        mallPageFragment = new MallPageFragment();
        categoryPageFragment = new CategoryPageFragment();
        profilePageFragment = new ProfilePageFragment();
        fragmentManager=getSupportFragmentManager();
//        fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.content,homePageFragment);
//        fragmentTransaction.commit();

    }

    @Override
    protected void initData() {
        fragmentManager.beginTransaction().add(R.id.content,homePageFragment)
                .add(R.id.content,giftPageFragmnet)
                .add(R.id.content,mallPageFragment)
                .add(R.id.content,categoryPageFragment)
                .add(R.id.content,profilePageFragment)
                .commit();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideAll(fragmentTransaction);
        fragmentTransaction.show(homePageFragment).commit();
    }

    @Override
    protected void bindEvent() {
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        fmTransaction = fragmentManager.beginTransaction();
        hideAll(fmTransaction);
        switch (checkedId){
            case R.id.radio_btn_home_page:
                fmTransaction.show(homePageFragment);
                break;
            case R.id.radio_bt_gift_page:
                fmTransaction.show(giftPageFragmnet);
                break;
            case R.id.radio_btn_mall_page:
                fmTransaction.show( mallPageFragment);
                break;
            case R.id.radio_btn_category_page:
                fmTransaction.show( categoryPageFragment);
                break;
            case R.id.radio_btn_profile_page:
                fmTransaction.show(profilePageFragment);
                break;

        }
        fmTransaction.commit();

    }

    private void hideAll(FragmentTransaction fragmentTransaction){
        fragmentTransaction.hide(homePageFragment)
                .hide(giftPageFragmnet)
                .hide(mallPageFragment)
                .hide(categoryPageFragment)
                .hide(profilePageFragment);
    }


}
