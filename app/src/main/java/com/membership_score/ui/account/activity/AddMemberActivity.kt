package com.membership_score.ui.account.activity

import android.database.sqlite.SQLiteDatabase
import android.text.TextUtils
import android.view.View
import com.membership_score.R
import com.membership_score.app.AppApplication.Companion.context
import com.membership_score.base.BaseActivity
import com.membership_score.baselib.utils.DateUtil
import com.membership_score.baselib.utils.StringUtils
import com.membership_score.baselib.utils.ToastUtil
import com.membership_score.constant.DBConstant
import com.membership_score.database.bean.MemberShipInfo
import com.membership_score.database.bean.MemberShipScoreNum
import com.membership_score.database.other.MySqlLiteHelper
import com.membership_score.ui.account.bean.ForGetPWResultBean
import com.membership_score.ui.account.contract.AccountFragmentContract
import com.membership_score.ui.account.presenter.AccountFragmentPresenter
import com.membership_score.ui.home.bean.EventBusBean
import com.membership_score.ui.home.pop.ISexCallBack
import com.membership_score.ui.home.pop.PopUtils
import kotlinx.android.synthetic.main.activity_add_member.*
import org.greenrobot.eventbus.EventBus

class AddMemberActivity : BaseActivity<AccountFragmentPresenter>(), AccountFragmentContract.IView {
    var db: SQLiteDatabase? = null
    var mySqlLiteHelper: MySqlLiteHelper? = null
    /************
    男：1 女 2 其他
     ********************/
    internal var changeSex = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_add_member
    }

    override fun initView() {
    }

    override fun initPresenter() {
        mPresenter = AccountFragmentPresenter()
    }

    override fun initListener() {
        rl_sex_layout.setOnClickListener {
            PopUtils.showSexChane(this,rl_view_layout,object : ISexCallBack{
                override fun onChaneGird() {
                    changeSex=2
                    tv_add_member_sex.text ="女"
                }
                override fun onChaneOther() {
                    changeSex=3
                    tv_add_member_sex.text ="其他"
                }
                override fun onChaneBoy() {
                    changeSex=1
                    tv_add_member_sex.text ="男"
                }
            })
        }

        tv_add_one_member.setOnClickListener{
            val name = et_add_member_name.text.toString()
            val phone = et_add_member_phone.text.toString()
            val address = et_add_member_address.text.toString()
            if (TextUtils.isEmpty(name)){
                ToastUtil.toast("姓名不能为空")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(phone)){
                ToastUtil.toast("手机号码不能为空")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(address)){
                ToastUtil.toast("会员地址不能为空")
                return@setOnClickListener
            }
            if(changeSex==0){
                ToastUtil.toast("会员性别不能为空")
                return@setOnClickListener
            }
            if (!StringUtils.isMobileNO(phone)){
                ToastUtil.toast("请输入正确的手机号码")
                return@setOnClickListener
            }
            val userId = StringUtils.getRandomString(10)

            val infoNew = MemberShipInfo(
                0, userId,changeSex,name, phone, "", address, 0, 0, DateUtil.getBeforeOrAfterDate(7, "yyyy-MM-dd")
            )
            mPresenter.addOneMember(db,infoNew)
            val memberShipNew = MemberShipScoreNum(
                userId, 0, 0,DateUtil.getBeforeOrAfterDate(7, "yyyy-MM-dd hh:mm")
                ,0)
            mPresenter.addOneMemberShip(db,memberShipNew)
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
        EventBus.getDefault().postSticky(EventBusBean(1))
        ToastUtil.showSuccess("添加成功")
    }

    override fun upDataUserPWSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun upDataUserPWFaild(msg: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun forgetUserPWFaild(msg: String?) {
    }

    override fun forgetUserPWSuccess(resultBean: ForGetPWResultBean?) {
    }
}