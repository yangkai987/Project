package com.membership_score.ui.common.model

import com.membership_score.baselib.http.HttpUtil
import com.membership_score.base.BaseModel
import com.membership_score.ui.common.service.MainService

class MainModel : BaseModel<MainService>() {

    override fun initService() {
        mService = HttpUtil.getService(MainService::class.java)
    }

}















