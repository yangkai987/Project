package com.membership_score.ui.account.activity

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.text.TextUtils
import android.view.View
import com.membership_score.R
import com.membership_score.app.AppApplication
import com.membership_score.app.AppApplication.Companion.context
import com.membership_score.base.BaseActivity
import com.membership_score.baselib.utils.SPUtils
import com.membership_score.baselib.utils.StringUtils
import com.membership_score.baselib.utils.ToastUtil
import com.membership_score.constant.DBConstant
import com.membership_score.database.other.MySqlLiteHelper
import com.membership_score.ui.account.bean.ForGetPWResultBean
import com.membership_score.ui.account.contract.AccountFragmentContract
import com.membership_score.ui.account.presenter.AccountFragmentPresenter
import com.membership_score.widgetlib.dialog.CommonDialog
import kotlinx.android.synthetic.main.activity_member_forget_pw.*

/**
 * 忘记密码
 * */
class MemberForGetPWActivity : BaseActivity<AccountFragmentPresenter>(), AccountFragmentContract.IView {
    var db: SQLiteDatabase? = null
    var mySqlLiteHelper: MySqlLiteHelper? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_member_forget_pw
    }

    override fun initView() {
    }

    override fun initPresenter() {
        mPresenter = AccountFragmentPresenter()
    }

    override fun initListener() {
        tv_find_member_forget_pw_sure.setOnClickListener{
            val mobileNums = et_member_old_forget_phone_input.text.toString()
            if (TextUtils.isEmpty(mobileNums)){
                ToastUtil.toast("请输入手机号码")
                return@setOnClickListener
            }
            if (!StringUtils.isMobileNO(mobileNums)){
                ToastUtil.toast("请输入正确的手机号码")
                return@setOnClickListener
            }
            mPresenter.forgetUserPW(db,mobileNums)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        db?.close()
    }

    override fun initData() {
    }

    override fun getData() {
        mySqlLiteHelper = MySqlLiteHelper(context, DBConstant.DB_NAME, null, DBConstant.DB_VERSION)
        db = mySqlLiteHelper!!.writableDatabase// 打开数据库
    }

    override fun onClick(p0: View?) {
    }

    override fun addOneMemberSuccess() {
    }

    override fun addOneMemberFaild(msg: String?) {
    }

    override fun addOneMemberShipSuccess() {
    }

    override fun addOneMemberShipFaild(msg: String?) {
    }

    override fun upDataUserPWSuccess() {
    }

    override fun upDataUserPWFaild(msg: String?) {
    }

    override fun forgetUserPWSuccess(resultBean: ForGetPWResultBean?) {
        CommonDialog.build(this, "已找回信息\r\n登陆名: "+resultBean?.userName+"\r\n密码: "+resultBean?.password)
            .setListener(object : CommonDialog.Listener() {
                override fun onRightClick() {
                    SPUtils.clearSharePreference()
                    val intent = Intent(AppApplication.context, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }).setLeftBtStr("").setRightBtStr("重新登陆").show()
    }

    override fun forgetUserPWFaild(msg: String?) {
        ToastUtil.showSuccess(msg)
    }

}