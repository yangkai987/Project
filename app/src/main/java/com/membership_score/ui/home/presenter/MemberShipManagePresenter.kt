package com.membership_score.ui.home.presenter

import android.database.sqlite.SQLiteDatabase
import com.membership_score.base.BasePresenter
import com.membership_score.database.bean.MemberShipScoreNum
import com.membership_score.database.other.SQLManger
import com.membership_score.ui.home.contract.MemberShipManageContract
import com.membership_score.ui.home.model.HomeFragmentModel


/**
 * @author YK
 * @createtime 2019/10/29 11:34
 */
class MemberShipManagePresenter : BasePresenter<HomeFragmentModel, MemberShipManageContract.IView>(),
    MemberShipManageContract.IPresenter {
    override fun selectOneMemberAllShip(db: SQLiteDatabase?, member_id: String?) {
        try {
            mView.selectOneMemberAllShipSuccess(SQLManger.getIntance().selectMemberShipToList(db,member_id))
        } catch (ex: Exception) {
            mView.selectOneMemberAllShipFaild(ex.message)
        }
        db?.close()
    }

    override fun addOneMemberShip(db: SQLiteDatabase, info: MemberShipScoreNum) {
        try {
            val rowid = SQLManger.getIntance().insertOneMemberShip(db, info)
            mView.hideLoading()
            if (rowid >-1) {
                mView.addOneMemberShipSuccess()
            } else {
                mView.addOneMemberShipFaild("")
            }
        } catch (ex: Exception) {
            mView.addOneMemberShipFaild(ex.message)
        }
        db?.close()
    }

    override fun initModel() {
        mModel = HomeFragmentModel()
    }
}
