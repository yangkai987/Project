package com.membership_score.ui.account.presenter;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import com.membership_score.base.BasePresenter;
import com.membership_score.database.bean.MemberShipInfo;
import com.membership_score.database.bean.MemberShipScoreNum;
import com.membership_score.database.other.SQLManger;
import com.membership_score.ui.account.bean.ForGetPWResultBean;
import com.membership_score.ui.account.contract.AccountFragmentContract;
import com.membership_score.ui.account.model.AccountFragmentModel;

/**
 * @author yk
 * @createtime 2019/2/27 11:34
 */
public class AccountFragmentPresenter extends BasePresenter<AccountFragmentModel, AccountFragmentContract.IView> implements AccountFragmentContract.IPresenter {

    private static final String TAG = "AccountFragmentPresenter";

    @Override
    public void initModel() {
        mModel = new AccountFragmentModel();
    }

    @SuppressLint("LongLogTag")
    @Override
    public void addOneMember(SQLiteDatabase db,MemberShipInfo info) {
        mView.showLoading();
        try {
            long rowid =SQLManger.getIntance().insertOneMember(db,info);
            mView.hideLoading();
            if (rowid == -1){
                mView.addOneMemberFaild("");
            }else  if (rowid == -2){
                mView.addOneMemberFaild("该会员已存在");
            }else{
                mView.addOneMemberSuccess();
            }
        }catch (Exception ex){
            mView.hideLoading();
            mView.addOneMemberFaild("");
        }

    }

    @Override
    public void addOneMemberShip(SQLiteDatabase db, MemberShipScoreNum info) {
        try {
            long rowid =SQLManger.getIntance().insertOneMemberShip(db,info);
            mView.hideLoading();
            if (rowid == -1){
                mView.addOneMemberFaild("");
            }else {
                mView.addOneMemberSuccess();
            }
        }catch (Exception ex){
            mView.addOneMemberFaild("");
        }
    }

    @Override
    public void upDataUserPW(SQLiteDatabase db, String userId, String newPW) {
        mView.showLoading();
        try {
            long result=SQLManger.getIntance().upDataUserPW(db,userId,newPW);
            if (result>0){
                mView.upDataUserPWSuccess();
            }else{
                mView.upDataUserPWFaild("修改失败");
            }
        } catch (Exception ex) {
            mView.upDataUserPWFaild(ex.getMessage());
        }
        mView.hideLoading();
    }

    @Override
    public void forgetUserPW(SQLiteDatabase db, String phone) {
        mView.showLoading();
        try {
            ForGetPWResultBean resultBean=SQLManger.getIntance().forgetUserPW(db,phone);
            if (resultBean!=null){
                mView.forgetUserPWSuccess(resultBean);
            }else{
                mView.forgetUserPWFaild("未找到对应结果");
            }
        } catch (Exception ex) {
            mView.forgetUserPWFaild(ex.getMessage());
        }
        mView.hideLoading();
    }
}