package com.membership_score.ui.account.contract;

import android.database.sqlite.SQLiteDatabase;
import com.membership_score.base.BaseView;
import com.membership_score.database.bean.MemberShipInfo;
import com.membership_score.database.bean.MemberShipScoreNum;
import com.membership_score.ui.account.bean.ForGetPWResultBean;

public interface AccountFragmentContract {

    interface IView extends BaseView {
        void addOneMemberSuccess();
        void addOneMemberFaild(String msg);
        void addOneMemberShipSuccess();
        void addOneMemberShipFaild(String msg);
        void upDataUserPWSuccess();
        void upDataUserPWFaild(String msg);
        void forgetUserPWSuccess(ForGetPWResultBean resultBean);
        void forgetUserPWFaild(String msg);
    }

    interface IPresenter {
       void  addOneMember(SQLiteDatabase db,MemberShipInfo info);
       void  addOneMemberShip(SQLiteDatabase db, MemberShipScoreNum info);
       void  upDataUserPW(SQLiteDatabase db, String userId,String newPW);
       void  forgetUserPW(SQLiteDatabase db, String phone);
    }

}


