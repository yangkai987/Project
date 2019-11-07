package com.membership_score.ui.home.presenter

import android.database.sqlite.SQLiteDatabase
import com.membership_score.base.BasePresenter
import com.membership_score.database.bean.MemberShipScoreNum
import com.membership_score.database.other.SQLManger
import com.membership_score.ui.home.contract.MemberManageContract
import com.membership_score.ui.home.model.HomeFragmentModel


/**
 * @author YK
 * @createtime 2019/10/29 11:34
 */
class MemberManagePresenter : BasePresenter<HomeFragmentModel, MemberManageContract.IView>(),
    MemberManageContract.IPresenter {

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

    }

    override fun deleteOneMember(db: SQLiteDatabase?, userId: String?) {
        mView.showLoading()
        try {
            mView.deleteOneMemberSuccess(SQLManger.getIntance().deleteOneMemberInfo(db,userId))
        } catch (ex: Exception) {
            mView.deleteOneMemberFaild(ex.message)
        }
        mView.hideLoading()
    }

    override fun upDataOneMember(db: SQLiteDatabase?,member_id:String?, name: String?, phone: String?) {
        mView.showLoading()
        try {
            var upDataOneMemberInfo=SQLManger.getIntance().upDataMemberInfo(db,member_id,name,phone)
            if (upDataOneMemberInfo>0){
                mView.upDataOneMemberSuccess()
            }else{
                mView.upDataOneMemberFaild("更新失败")
            }
        } catch (ex: Exception) {
            mView.upDataOneMemberFaild(ex.message)
        }
        mView.hideLoading()
    }

    override fun initModel() {
        mModel = HomeFragmentModel()
    }
}
