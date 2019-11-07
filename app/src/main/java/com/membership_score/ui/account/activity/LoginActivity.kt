package com.membership_score.ui.account.activity

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.view.View
import com.membership_score.R
import com.membership_score.app.AppApplication.Companion.context
import com.membership_score.base.BaseActivity
import com.membership_score.baselib.utils.SPUtils
import com.membership_score.baselib.utils.ToastUtil
import com.membership_score.constant.DBConstant
import com.membership_score.database.other.MySqlLiteHelper
import com.membership_score.ui.account.contract.LoginContract
import com.membership_score.ui.account.presenter.LoginPresenter
import com.membership_score.ui.common.activity.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginPresenter>(), LoginContract.IView {
    var db: SQLiteDatabase? = null
    var mySqlLiteHelper: MySqlLiteHelper? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
    }

    override fun initPresenter() {
        mPresenter = LoginPresenter()
    }

    override fun initListener() {
        tv_login.setOnClickListener {
            var  loginName=et_login_member_name.text.toString()
            var  loginPW=et_login_pw.text.toString()
            if(loginName.isEmpty()){
                ToastUtil.toast("用户名不能为空")
                return@setOnClickListener
            }
            if(loginPW.isEmpty()){
                ToastUtil.toast("密码不能为空")
                return@setOnClickListener
            }
            mPresenter.login(db,loginName,loginPW)
        }

        tv_register.setOnClickListener {
            startActivity(Intent(context, RegisterActivity::class.java))
        }
        tv_forget_pw.setOnClickListener {
            startActivity(Intent(context, MemberForGetPWActivity::class.java))
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

    override fun registerSuccess() {
    }

    override fun registerFaild(msg: String?) {
    }

    override fun loginSuccess() {
        ToastUtil.showSuccess("登陆成功")
        SPUtils.putInt(DBConstant.LOGINS_TATUE,1)
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun loginFaild(msg: String?) {

        ToastUtil.showFaild(msg)
    }
}