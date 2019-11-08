package com.membership_score.ui.home.contract;

import android.database.sqlite.SQLiteDatabase;
import com.membership_score.base.BaseView;
import com.membership_score.database.bean.MemberShipOperationBean;
import com.membership_score.database.bean.MemberShipScoreNum;
import com.membership_score.database.bean.MemberShipScoreNumResult;
import java.util.List;

/**
 * @author YK
 * @createtime 2019/10/29 11:31
 */
public interface MemberShipManageContract {

    interface IView extends BaseView {
        void selectOneMemberAllShipSuccess(List<MemberShipScoreNumResult> ms_list);
        void selectOneMemberAllShipFaild(String msg);
        void addOneMemberShipSuccess();
        void addOneMemberShipFaild(String msg);
        void insertOneMemberShipOperationSuccess();
        void insertOneMemberShipOperationFaild(String msg);
    }

    interface IPresenter {
        void  selectOneMemberAllShip(SQLiteDatabase db, String member_id);
        void  addOneMemberShip(SQLiteDatabase db, MemberShipScoreNum info);
        void  insertOneMemberShipOperation(SQLiteDatabase db, MemberShipOperationBean info);
    }

}


