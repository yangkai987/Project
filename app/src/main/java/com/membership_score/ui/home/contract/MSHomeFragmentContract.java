package com.membership_score.ui.home.contract;

import android.database.sqlite.SQLiteDatabase;
import com.membership_score.base.BaseView;
import com.membership_score.database.bean.MemberShipInfoDBResult;

import java.util.List;

/**
 * @author YK
 * @createtime 2019/10/29 11:31
 */
public interface MSHomeFragmentContract {

    interface IView extends BaseView {
        void selectMSListSuccess(List<MemberShipInfoDBResult> listData);
        void selectMSListFaild(String msg);

        void selectMSListPhoneAndNameSuccess(List<MemberShipInfoDBResult> listData);
        void selectMSListPhoneAndNameFaild(String msg);
    }

    interface IPresenter {
        void selectMSList(SQLiteDatabase db);
        void selectMSListPhoneAndName(SQLiteDatabase db,String name,String phone);
    }

}


