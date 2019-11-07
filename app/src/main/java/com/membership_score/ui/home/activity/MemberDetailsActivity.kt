package com.membership_score.ui.home.activity

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.view.View
import com.membership_score.R
import com.membership_score.app.AppApplication
import com.membership_score.app.AppApplication.Companion.context
import com.membership_score.base.BaseActivity
import com.membership_score.baselib.utils.ToastUtil
import com.membership_score.constant.DBConstant
import com.membership_score.database.bean.MemberShipInfoDBResult
import com.membership_score.database.other.MySqlLiteHelper
import com.membership_score.ui.home.bean.EventBusBean
import com.membership_score.ui.home.contract.MemberManageContract
import com.membership_score.ui.home.presenter.MemberManagePresenter
import com.membership_score.widgetlib.dialog.CommonDialog
import kotlinx.android.synthetic.main.activity_member_details.*
import org.greenrobot.eventbus.EventBus

class MemberDetailsActivity :BaseActivity<MemberManagePresenter>(), MemberManageContract.IView {
    var db: SQLiteDatabase? = null
    var mySqlLiteHelper: MySqlLiteHelper? = null
    var intentBean: MemberShipInfoDBResult?=null
    override fun onClick(p0: View?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_member_details
    }

    override fun initView() {
    }

    override fun initPresenter() {
        mPresenter = MemberManagePresenter()
    }

    override fun initListener() {
        //添加一个积分
        tv_add_one_member_ship.setOnClickListener {
            val intent = Intent(context, MemberShipAddActivity::class.java)
            intent.putExtra("userId", intentBean!!.userId)
            startActivity(intent)
        }
        //查询积分列表
        tv_select_all_member_ship_list.setOnClickListener {
            val intent = Intent(context, MemberShipRecordActivity::class.java)
            intent.putExtra("userId", intentBean!!.userId)
            startActivity(intent)
        }
        //修改
        tv_updata_one_member.setOnClickListener {
            CommonDialog.build(this, "请确认修改信息\r\n"+et_updata_member_name.text.toString()
                    +"--"+et_updata_member_phoen.text.toString())
                .setListener(object : CommonDialog.Listener() {
                    override fun onRightClick() {
                        mPresenter.upDataOneMember(db,intentBean!!.userId,et_updata_member_name.text.toString()
                        ,et_updata_member_phoen.text.toString())
                    }
                }).show()
        }
        //删除
        tv_delect_one_member.setOnClickListener {
            CommonDialog.build(this, "请确认删除信息")
                .setListener(object : CommonDialog.Listener() {
                    override fun onRightClick() {
                        mPresenter.deleteOneMember(db,intentBean!!.userId)
                    }
                }).show()
        }
    }

    override fun initData() {
        intentBean  = intent.extras.get("oneMember") as MemberShipInfoDBResult?
    }

    override fun getData() {
        et_updata_member_name.setText(intentBean!!.name)
        et_updata_member_phoen.setText(intentBean!!.phoneNum)
        if (intentBean!!.sex==1){
            tv_add_member_sex.text ="男"
        }else{
            tv_add_member_sex.text ="女"
        }
        et_add_member_address.text = intentBean!!.address
        tv_member_total.text = intentBean!!.ms_total_num.toString()

        mySqlLiteHelper = MySqlLiteHelper(AppApplication.context, DBConstant.DB_NAME, null, DBConstant.DB_VERSION)
        db = mySqlLiteHelper!!.writableDatabase// 打开数据库
    }

    override fun deleteOneMemberSuccess(deleteOneMemberInfo: Long) {
        EventBus.getDefault().postSticky(EventBusBean(1))
        ToastUtil.showSuccess("删除成功！")
        finish()
    }

    override fun deleteOneMemberFaild(msg: String?) {
        ToastUtil.showFaild("删除失败:$msg")
    }

    override fun upDataOneMemberSuccess() {
        EventBus.getDefault().postSticky(EventBusBean(1))
        ToastUtil.showSuccess("修改成功！")
        finish()
    }

    override fun upDataOneMemberFaild(msg: String?) {
        ToastUtil.showFaild("修改失败:$msg")
    }

    override fun addOneMemberShipFaild(msg: String?) {
        ToastUtil.showFaild("添加积分失败$msg")
    }

    override fun addOneMemberShipSuccess() {
        ToastUtil.showSuccess("添加积分成功")
    }
}