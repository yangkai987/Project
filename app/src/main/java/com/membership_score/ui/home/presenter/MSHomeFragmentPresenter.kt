package com.membership_score.ui.home.presenter

import android.database.sqlite.SQLiteDatabase
import com.membership_score.base.BasePresenter
import com.membership_score.database.other.SQLManger
import com.membership_score.ui.home.contract.MSHomeFragmentContract
import com.membership_score.ui.home.model.HomeFragmentModel


/**
 * @author YK
 * @createtime 2019/10/29 11:34
 */
class MSHomeFragmentPresenter : BasePresenter<HomeFragmentModel, MSHomeFragmentContract.IView>(),
    MSHomeFragmentContract.IPresenter {

    override fun selectMSListPhoneAndName(db: SQLiteDatabase?, name: String?, phone: String?) {
        mView.showLoading()
        try {
            mView.selectMSListPhoneAndNameSuccess(SQLManger.getIntance().selectOneMemberPhoneAndName(db,phone,name))
        } catch (ex: Exception) {
            mView.selectMSListPhoneAndNameFaild(ex.message)
        }
        mView.hideLoading()
    }

    override fun selectMSList(db: SQLiteDatabase?) {
        mView.showLoading()
        try {
            mView.selectMSListSuccess(SQLManger.getIntance().selectMemberInfoToList(db))
        } catch (ex: Exception) {
            mView.selectMSListFaild(ex.message)
        }
        mView.hideLoading()
    }

    override fun initModel() {
        mModel = HomeFragmentModel()
    }
}
