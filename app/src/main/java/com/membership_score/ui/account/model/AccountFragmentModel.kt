package com.membership_score.ui.account.model

import com.membership_score.baselib.http.HttpUtil
import com.membership_score.base.BaseModel
import com.membership_score.ui.home.service.HomeFragmentService

/**
 * @author zhoujiulong
 * @createtime 2019/2/27 11:32
 */
class AccountFragmentModel : BaseModel<HomeFragmentService>() {

    override fun initService() {
        mService = HttpUtil.getService(HomeFragmentService::class.java)
    }

}
