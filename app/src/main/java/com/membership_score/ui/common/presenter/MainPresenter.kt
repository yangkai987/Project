package com.membership_score.ui.common.presenter

import com.membership_score.base.BasePresenter
import com.membership_score.ui.common.contract.MainContract
import com.membership_score.ui.common.model.MainModel

class MainPresenter : BasePresenter<MainModel, MainContract.IView>(), MainContract.IPresenter {

    override fun initModel() {
        mModel = MainModel()
    }

}















