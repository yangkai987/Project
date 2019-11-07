package com.membership_score.ui.account.activity

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.text.TextUtils
import android.view.View
import com.membership_score.R
import com.membership_score.app.AppApplication.Companion.context
import com.membership_score.base.BaseActivity
import com.membership_score.baselib.utils.SPUtils
import com.membership_score.baselib.utils.ToastUtil
import com.membership_score.constant.DBConstant
import com.membership_score.database.bean.RegisterDBBean
import com.membership_score.database.other.MySqlLiteHelper
import com.membership_score.ui.account.bean.ForGetPWResultBean
import com.membership_score.ui.account.contract.AccountFragmentContract
import com.membership_score.ui.account.presenter.AccountFragmentPresenter
import kotlinx.android.synthetic.main.activity_member_modify_pw.*

/**
 * 修改密码
 * */
class MemberModifyPWActivity : BaseActivity<AccountFragmentPresenter>(), AccountFragmentContract.IView {
    var db: SQLiteDatabase? = null
    var mySqlLiteHelper: MySqlLiteHelper? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_member_modify_pw
    }

    override fun initView() {
    }

    override fun initPresenter() {
        mPresenter = AccountFragmentPresenter()
    }

    override fun initListener() {
        tv_add_member_modify_pw_sure.setOnClickListener{
            val member_old_pw = et_member_old_modify.text.toString()
            val member_new_modify_pw_one = et_member_new_modify_pw_one.text.toString()
            val member_new_modify_pw_two = et_member_new_modify_pw_two.text.toString()
            if (TextUtils.isEmpty(member_old_pw)){
                ToastUtil.toast("请输入旧密码")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(member_new_modify_pw_one)){
                ToastUtil.toast("请输入新密码")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(member_new_modify_pw_two)){
                ToastUtil.toast("请输入确认密码")
                return@setOnClickListener
            }
            if(member_new_modify_pw_one!=member_new_modify_pw_two){
                ToastUtil.toast("输入的两次新密码不一致")
                return@setOnClickListener
            }
            val userBean = SPUtils.getObject("userInfoBean", RegisterDBBean::class.java)
            mPresenter.upDataUserPW(db,userBean.adminId,member_new_modify_pw_two)
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
        ToastUtil.showSuccess("密码修改成功！请重新登陆")
        SPUtils.putInt(DBConstant.LOGINS_TATUE,0)
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)

        finish()
    }

    override fun upDataUserPWFaild(msg: String?) {
        ToastUtil.showSuccess("密码修改失败！")
    }

    override fun forgetUserPWSuccess(resultBean: ForGetPWResultBean?) {
    }

    override fun forgetUserPWFaild(msg: String?) {
    }


}