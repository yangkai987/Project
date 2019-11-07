package com.membership_score.ui.home.activity

import android.database.sqlite.SQLiteDatabase
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.membership_score.R
import com.membership_score.app.AppApplication
import com.membership_score.base.BaseActivity
import com.membership_score.constant.DBConstant
import com.membership_score.database.bean.MemberShipScoreNumResult
import com.membership_score.database.other.MySqlLiteHelper
import com.membership_score.ui.home.adapter.MyOneMemberShipRecordAdapter
import com.membership_score.ui.home.contract.MemberShipManageContract
import com.membership_score.ui.home.presenter.MemberShipManagePresenter
import kotlinx.android.synthetic.main.activity_member_ship_record.*

/***
 * 积分记录
 * */
class MemberShipRecordActivity :BaseActivity<MemberShipManagePresenter>(), MemberShipManageContract.IView {
    var db: SQLiteDatabase? = null
    var mySqlLiteHelper: MySqlLiteHelper? = null
    var userId: String?=null
    var memberShipRecordAdapter: MyOneMemberShipRecordAdapter?=null

    /************
    微信：1 支付宝 2 现金 3 银行卡 4 其他 5
    ********************/
    internal var payType = 0

    override fun onClick(p0: View?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_member_ship_record
    }

    override fun initView() {
    }

    override fun initPresenter() {
        mPresenter = MemberShipManagePresenter()
    }

    override fun initListener() {
    }

    override fun initData() {
        userId  = intent.extras.get("userId") as String?

        mySqlLiteHelper = MySqlLiteHelper(AppApplication.context, DBConstant.DB_NAME, null, DBConstant.DB_VERSION)
        db = mySqlLiteHelper!!.writableDatabase// 打开数据库
        recyclerview_ship_record.layoutManager = LinearLayoutManager(mContext)
        memberShipRecordAdapter = MyOneMemberShipRecordAdapter(null)
    }

    override fun getData() {
        /**设置加载更多代理委托**/
        recyclerview_ship_record.adapter = memberShipRecordAdapter
        loadingView_ship_record.showLoading()
        mPresenter.selectOneMemberAllShip(db,userId)
    }

    override fun selectOneMemberAllShipSuccess(ms_list: MutableList<MemberShipScoreNumResult>?) {
        if(ms_list.isNullOrEmpty()){
            loadingView_ship_record.showEmpty()
        }else{
            loadingView_ship_record.showContent()
            memberShipRecordAdapter?.setNewData(ms_list)
        }
    }

    override fun selectOneMemberAllShipFaild(msg: String?) {
        loadingView_ship_record.showError()
    }


    override fun addOneMemberShipFaild(msg: String?) {}

    override fun addOneMemberShipSuccess() {}
}