package com.membership_score.ui.home.activity

import android.database.sqlite.SQLiteDatabase
import android.text.TextUtils
import android.view.View
import com.membership_score.R
import com.membership_score.app.AppApplication
import com.membership_score.base.BaseActivity
import com.membership_score.baselib.utils.DateUtil
import com.membership_score.baselib.utils.ToastUtil
import com.membership_score.constant.DBConstant
import com.membership_score.database.bean.MemberShipOperationBean
import com.membership_score.database.bean.MemberShipScoreNum
import com.membership_score.database.bean.MemberShipScoreNumResult
import com.membership_score.database.other.MySqlLiteHelper
import com.membership_score.ui.home.bean.EventBusBean
import com.membership_score.ui.home.contract.MemberShipManageContract
import com.membership_score.ui.home.pop.IPayTypeCallBack
import com.membership_score.ui.home.pop.PopUtils
import com.membership_score.ui.home.presenter.MemberShipManagePresenter
import kotlinx.android.synthetic.main.activity_member_ship_add.*
import org.greenrobot.eventbus.EventBus

class MemberShipAddActivity :BaseActivity<MemberShipManagePresenter>(), MemberShipManageContract.IView {
    var db: SQLiteDatabase? = null
    var mySqlLiteHelper: MySqlLiteHelper? = null
    var userId: String?=null
    var money: String?=null
    /************
     微信：1 支付宝 2 现金 3 银行卡 4 其他 5
     ********************/
    internal var payType = 0

    override fun onClick(p0: View?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_member_ship_add
    }

    override fun initView() {
    }

    override fun initPresenter() {
        mPresenter = MemberShipManagePresenter()
    }

    override fun initListener() {
        //添加一个积分
        tv_add_one_member_ship_sure.setOnClickListener {
            money = et_recharge_member_ship_pay_money.text.toString()
            if (TextUtils.isEmpty(money)){
                ToastUtil.toast("金额不能为空")
                return@setOnClickListener
            }
            if (payType==0){
                    ToastUtil.toast("请选择支付类型")
                return@setOnClickListener
            }
            val str = "^[\\-|0-9][0-9]{1,}\$"
            if (!money!!.matches(str.toRegex())){
                ToastUtil.toast("请输入正确的金额")
                return@setOnClickListener
            }
            val memberShipNew = MemberShipScoreNum(
                userId, Integer.parseInt(money), Integer.parseInt(money), DateUtil.getBeforeOrAfterDate(7, "yyyy-MM-dd hh:mm")
                ,payType)
            db?.let { it1 -> mPresenter.addOneMemberShip(it1, memberShipNew) }
        }
        tv_pay_type.setOnClickListener {
            PopUtils.showPayTypeChane(this, rl_layout_view, object : IPayTypeCallBack {
                override fun onChaneXJ() {
                    payType = 3
                    tv_pay_type.text = "现金"
                }

                override fun onChaneWX() {
                    payType = 1
                    tv_pay_type.text = "微信"
                }

                override fun onChaneZFB() {
                    payType = 2
                    tv_pay_type.text = "支付宝"
                }

                override fun onChaneBank() {
                    payType = 4
                    tv_pay_type.text = "银行卡"
                }

                override fun onChaneOther() {
                    payType = 5
                    tv_pay_type.text = "其他"
                }
            })
        }
    }

    override fun initData() {
        userId  = intent.extras.get("userId") as String?
    }

    override fun getData() {
        mySqlLiteHelper = MySqlLiteHelper(AppApplication.context, DBConstant.DB_NAME, null, DBConstant.DB_VERSION)
        db = mySqlLiteHelper!!.writableDatabase// 打开数据库
    }

    override fun addOneMemberShipFaild(msg: String?) {
        ToastUtil.showFaild("添加积分记录失败$msg")
    }

    override fun addOneMemberShipSuccess() {
        val memberShipOperation = MemberShipOperationBean(
            userId,  DateUtil.getBeforeOrAfterDate(7, "yyyy-MM-dd hh:mm"),
            payType,Integer.parseInt(money))
        db?.let { it1 -> mPresenter.insertOneMemberShipOperation(it1, memberShipOperation) }
    }

    override fun insertOneMemberShipOperationSuccess() {
        ToastUtil.showSuccess("添加积分成功")
        EventBus.getDefault().postSticky(EventBusBean(1))
    }

    override fun insertOneMemberShipOperationFaild(msg: String?) {
        ToastUtil.showFaild("添加积分失败$msg")
    }


    override fun selectOneMemberAllShipSuccess(ms_list: MutableList<MemberShipScoreNumResult>?) {
    }

    override fun selectOneMemberAllShipFaild(msg: String?) {
    }
}