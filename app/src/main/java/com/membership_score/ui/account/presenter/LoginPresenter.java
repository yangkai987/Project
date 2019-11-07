package com.membership_score.ui.account.presenter;

import android.database.sqlite.SQLiteDatabase;
import com.membership_score.base.BasePresenter;
import com.membership_score.baselib.utils.SPUtils;
import com.membership_score.database.bean.RegisterDBBean;
import com.membership_score.database.other.SQLManger;
import com.membership_score.ui.account.contract.LoginContract;
import com.membership_score.ui.account.model.AccountFragmentModel;

/**
 * @author yk
 * @createtime 2019/2/27 11:34
 */
public class LoginPresenter extends BasePresenter<AccountFragmentModel, LoginContract.IView> implements LoginContract.IPresenter {

    private static final String TAG = "LoginPresenter";

    @Override
    public void initModel() {
        mModel = new AccountFragmentModel();
    }

    @Override
    public void register(SQLiteDatabase db, RegisterDBBean registerDBBean) {
        mView.showLoading();
        try {
            long result = SQLManger.getIntance().insertRegister(db,registerDBBean);
            if(result>0){
                if(result==2){
                    mView.registerFaild("登陆用户名或手机号已存在");
                }else{
                    SPUtils.putObject("UserInfoBean",registerDBBean);
                    mView.registerSuccess();
                }
            }else{
                mView.registerFaild("注册失败");
            }
            mView.hideLoading();
        }catch (Exception ex){
            mView.hideLoading();
            mView.registerFaild("注册失败："+ex.getMessage());
        }
    }

    @Override
    public void login(SQLiteDatabase db, String loginName, String loginPW) {
        mView.showLoading();
        try {
            long result = SQLManger.getIntance().loginAdmin(db,loginName,loginPW);
            if(result==1){
                mView.loginSuccess();
            }else  if(result==2){
                mView.loginFaild("用户等级权限不够，请联系管理员");
            }else{
                mView.loginFaild("用户不存在");
            }
            mView.hideLoading();
        }catch (Exception ex){
            mView.loginFaild("登录失败："+ex.getMessage());
            mView.hideLoading();
        }
    }
}