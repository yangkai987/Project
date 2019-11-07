package com.membership_score.ui.account.fragment

import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.membership_score.R
import com.membership_score.app.AppApplication
import com.membership_score.base.BaseFragment
import com.membership_score.baselib.utils.SPUtils
import com.membership_score.baselib.utils.ToastUtil
import com.membership_score.database.bean.RegisterDBBean
import com.membership_score.ui.account.activity.AddMemberActivity
import com.membership_score.ui.account.activity.LoginActivity
import com.membership_score.ui.account.activity.MemberModifyPWActivity
import com.membership_score.ui.account.bean.ForGetPWResultBean
import com.membership_score.ui.account.contract.AccountFragmentContract
import com.membership_score.ui.account.presenter.AccountFragmentPresenter
import com.membership_score.widgetlib.dialog.CommonDialog
import kotlinx.android.synthetic.main.fragment_account_new.*

/**
 * @author yk
 * @createtime 2019/2/27 13:59
 */
class AccountFragment : BaseFragment<AccountFragmentPresenter>(), AccountFragmentContract.IView {
    lateinit var userInfobBean:RegisterDBBean

    override fun getLayoutId(): Int {
        return R.layout.fragment_account_new
    }

    override fun initView(rootView: View) {

    }

    override fun initPresenter() {
        mPresenter = AccountFragmentPresenter()
    }

    override fun initListener() {
        tv_add_member.setOnClickListener {
            startActivity(Intent(context,AddMemberActivity::class.java))
        }
        tv_modify_member_pw.setOnClickListener {
            startActivity(Intent(context, MemberModifyPWActivity::class.java))
        }
        tv_exit_login.setOnClickListener {
            CommonDialog.build(context, "请确认退出登陆")
                .setListener(object : CommonDialog.Listener() {
                    override fun onRightClick() {
                        SPUtils.clearSharePreference()
//                        EventBus.getDefault().postSticky(EventBusBean(1000))
                        val intent = Intent(AppApplication.context, LoginActivity::class.java)
                        startActivity(intent)
                        activity?.finish()
                    }
                }).show()
        }
    }

    override fun initData() {
        userInfobBean = SPUtils.getObject("userInfoBean", RegisterDBBean::class.java)
    }

    override fun getData() {
        if(!TextUtils.isEmpty(userInfobBean.adminNickname)){
            tv_user_name.text =userInfobBean.adminNickname.toString()
        }else{
            tv_user_name.text =userInfobBean.adminLoginName.toString()
        }
        tv_user_phone.text =userInfobBean.adminPhoneNum.toString()
    }

    override fun onClick(view: View) {

    }


    override fun addOneMemberShipFaild(msg: String?) {
        ToastUtil.showFaild("添加积分失败$msg")
    }

    override fun addOneMemberShipSuccess() {
        ToastUtil.showSuccess("添加积分成功")
    }

    override fun addOneMemberFaild(msg: String?) {
        ToastUtil.showFaild("添加失败$msg")
    }

    override fun addOneMemberSuccess() {
        ToastUtil.showSuccess("添加成功")
    }

    override fun upDataUserPWSuccess() {
    }

    override fun upDataUserPWFaild(msg: String?) {
    }
    override fun forgetUserPWSuccess(resultBean: ForGetPWResultBean?) {
    }

    override fun forgetUserPWFaild(msg: String?) {
    }

}
