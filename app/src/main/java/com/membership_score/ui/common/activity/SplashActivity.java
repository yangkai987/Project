package com.membership_score.ui.common.activity;

import android.content.Intent;
import android.view.View;
import com.membership_score.R;
import com.membership_score.base.BaseActivity;
import com.membership_score.baselib.utils.SPUtils;
import com.membership_score.constant.DBConstant;
import com.membership_score.ui.account.activity.LoginActivity;

public class SplashActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash_view;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getData() {
        init();
    }


    private void init(){
        int loginStatue= SPUtils.getInt(DBConstant.LOGINS_TATUE,0);
        if (loginStatue==1){
            goMain();
        }else{
            // TODO: 2018/11/15 去登陆
            //第一次
            goGuide();
        }
    }

    private void goGuide() {
        //跳转到引导页面
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        this.finish();
    }
    private void goMain() {
        //跳转到引导页面
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        this.finish();
    }


    @Override
    public void onClick(View view) {

    }
}
