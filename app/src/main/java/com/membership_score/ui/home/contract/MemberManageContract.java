package com.membership_score.ui.home.contract;

import android.database.sqlite.SQLiteDatabase;
import com.membership_score.base.BaseView;
import com.membership_score.database.bean.MemberShipScoreNum;

/**
 * @author YK
 * @createtime 2019/10/29 11:31
 */
public interface MemberManageContract {

    interface IView extends BaseView {
        void deleteOneMemberSuccess(long deleteOneMemberInfo);
        void deleteOneMemberFaild(String msg);

        void upDataOneMemberSuccess();
        void upDataOneMemberFaild(String msg);

        void addOneMemberShipSuccess();
        void addOneMemberShipFaild(String msg);
    }

    interface IPresenter {
        void  addOneMemberShip(SQLiteDatabase db, MemberShipScoreNum info);
        void deleteOneMember(SQLiteDatabase db,String userId);
        void upDataOneMember(SQLiteDatabase db,String member_id, String name, String phone);
    }

}


