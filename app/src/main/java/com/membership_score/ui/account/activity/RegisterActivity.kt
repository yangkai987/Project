package com.membership_score.ui.account.activity

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.view.View
import com.membership_score.R
import com.membership_score.app.AppApplication.Companion.context
import com.membership_score.base.BaseActivity
import com.membership_score.baselib.utils.DateUtil
import com.membership_score.baselib.utils.SPUtils
import com.membership_score.baselib.utils.StringUtils
import com.membership_score.baselib.utils.ToastUtil
import com.membership_score.constant.DBConstant
import com.membership_score.database.bean.RegisterDBBean
import com.membership_score.database.other.MySqlLiteHelper
import com.membership_score.ui.account.contract.LoginContract
import com.membership_score.ui.account.presenter.LoginPresenter
import com.membership_score.ui.common.activity.MainActivity
import com.membership_score.ui.home.pop.ISexCallBack
import com.membership_score.ui.home.pop.PopUtils
import kotlinx.android.synthetic.main.activity_login.tv_register
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_login.rl_view_layout as rl_view_layout1

/**
 * 注册
 * */
class RegisterActivity : BaseActivity<LoginPresenter>(), LoginContract.IView {
    var db: SQLiteDatabase? = null
    var mySqlLiteHelper: MySqlLiteHelper? = null
    /************
    男：1 女 2 其他
     ********************/
    internal var changeSex = 0
    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initView() {
    }

    override fun initPresenter() {
        mPresenter = LoginPresenter()
    }

    override fun initListener() {
        tv_register.setOnClickListener {
            var  loginName=et_loginname_member.text.toString()
            var  loginPW_one=et_login_pw_member.text.toString()
            var  loginPW_two=et_login_pw_member_two.text.toString()
            var  register_nickname=et_login_member_nickname.text.toString()
            var  register_phone_num=et_member_phone_num.text.toString()
            var  register_level=et_login_pw_member_level.text.toString()
            if(loginName.isEmpty()){
                ToastUtil.toast("用户名不能为空")
                return@setOnClickListener
            }
            if(loginPW_one.isEmpty()||loginPW_two.isEmpty()){
                ToastUtil.toast("密码不能为空")
                return@setOnClickListener
            }
            if(register_nickname.isEmpty()){
                ToastUtil.toast("会员昵称不能为空")
                return@setOnClickListener
            }
//            if(changeSex==0){
//                ToastUtil.toast("会员性别不能为空")
//                return@setOnClickListener
//            }
            if (!StringUtils.isMobileNO(register_phone_num)){
                ToastUtil.toast("请输入正确的手机号码")
                return@setOnClickListener
            }
            if(register_level.isEmpty()){
                ToastUtil.toast("会员等级不能为空")
                return@setOnClickListener
            }
            if(loginPW_one != loginPW_two){
                ToastUtil.toast("密码不一致")
                return@setOnClickListener
            }
            val userId = StringUtils.getRandomString(10)
            val register = RegisterDBBean(
                Integer.parseInt(register_level), userId, changeSex,register_nickname,register_phone_num,"","",
                DateUtil.getBeforeOrAfterDate(7, "yyyy-MM-dd hh:mm"),loginName,loginPW_one)
            mPresenter.register(db,register)
        }
        tv_member_sex.setOnClickListener {
            PopUtils.showSexChane(this,rl_register_layout,object : ISexCallBack {
                override fun onChaneGird() {
                    changeSex=2
                    tv_member_sex.text ="女"
                }
                override fun onChaneOther() {
                    changeSex=3
                    tv_member_sex.text ="其他"
                }
                override fun onChaneBoy() {
                    changeSex=1
                    tv_member_sex.text ="男"
                }
            })
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
        ToastUtil.showSuccess("注册成功")
        SPUtils.putInt(DBConstant.LOGINS_TATUE,1)
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun registerFaild(msg: String?) {
        ToastUtil.showFaild(msg)
    }

    override fun loginSuccess() {
    }

    override fun loginFaild(msg: String?) {
    }
}