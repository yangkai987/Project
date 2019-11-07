package com.membership_score.ui.account.contract;

import android.database.sqlite.SQLiteDatabase;
import com.membership_score.base.BaseView;
import com.membership_score.database.bean.RegisterDBBean;

public interface LoginContract {

    interface IView extends BaseView {
        void registerSuccess();
        void registerFaild(String msg);
        void loginSuccess();
        void loginFaild(String msg);
    }

    interface IPresenter {
       void  register(SQLiteDatabase db, RegisterDBBean registerDBBean);
       void  login(SQLiteDatabase db, String loginName,String loginPW);
    }

}


