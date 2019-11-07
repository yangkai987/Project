package com.membership_score.ui.home.fragment

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.membership_score.R
import com.membership_score.base.BaseFragment
import com.membership_score.baselib.utils.ToastUtil
import com.membership_score.constant.DBConstant
import com.membership_score.database.bean.MemberShipInfoDBResult
import com.membership_score.database.other.MySqlLiteHelper
import com.membership_score.ui.home.activity.MemberDetailsActivity
import com.membership_score.ui.home.adapter.MyAllMemberInfoAdapter
import com.membership_score.ui.home.bean.EventBusBean
import com.membership_score.ui.home.contract.MSHomeFragmentContract
import com.membership_score.ui.home.presenter.MSHomeFragmentPresenter
import kotlinx.android.synthetic.main.fragment_membership_score_home.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MembershipScoreHomeFragment : BaseFragment<MSHomeFragmentPresenter>(), MSHomeFragmentContract.IView {
    override fun selectMSListPhoneAndNameSuccess(listData: MutableList<MemberShipInfoDBResult>?) {
        if(listData.isNullOrEmpty()){
            ToastUtil.showSuccess("未找到结果")
        }else{
            ToastUtil.showSuccess("已找到结果")
            rl_isempty.visibility = View.GONE
            lv_result_search.visibility = View.VISIBLE
            memberAdapter?.setNewData(listData)
        }
    }

    override fun selectMSListPhoneAndNameFaild(msg: String?) {
        ToastUtil.showSuccess("未找到结果")
    }

    var db: SQLiteDatabase? = null
    var mySqlLiteHelper: MySqlLiteHelper? = null

    var memberAdapter: MyAllMemberInfoAdapter?=null

    override fun selectMSListSuccess(listData: MutableList<MemberShipInfoDBResult>?) {
        if (listData?.isNotEmpty()!!) {
            rl_isempty.visibility = View.GONE
            lv_result_search.visibility = View.VISIBLE
            memberAdapter?.setNewData(listData)
        } else {
            rl_isempty.visibility = View.VISIBLE
            lv_result_search.visibility = View.GONE
            memberAdapter?.setNewData(null)
        }
    }

    override fun selectMSListFaild(msg: String?) {
        Log.i("MembershipScore",msg)
        ToastUtil.showFaild(msg)
        lv_result_search.visibility =View.GONE
        rl_isempty.visibility = View.VISIBLE
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_membership_score_home
    }

    override fun initView(rootView: View) {
        EventBus.getDefault().register(this)
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onEvent(event: EventBusBean) {
        when (event.type) {
            1 ->
                //接受到推送，刷新列表
                mPresenter.selectMSList(db)
        }
    }


    override fun initPresenter() {
        mPresenter = MSHomeFragmentPresenter()
    }

    override fun initListener() {
        tv_search_ms_name_and_phone.setOnClickListener {
            val fandStr =et_input_search_name_and_phone.text.toString();
            if (!TextUtils.isEmpty(fandStr)){
                val p ="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|16[0|1|2|3|5|6|7|8|9]|17[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$"
                if (fandStr.matches(p.toRegex())) {
                    mPresenter.selectMSListPhoneAndName(db,null,fandStr)
                }else{
                    mPresenter.selectMSListPhoneAndName(db,fandStr,null)
                }
            }else{
                ToastUtil.showFaild("输入姓名或手机号码查询")
            }
        }
    }

    override fun initData() {
        mySqlLiteHelper = MySqlLiteHelper(context, DBConstant.DB_NAME, null, DBConstant.DB_VERSION)
        db = mySqlLiteHelper!!.writableDatabase// 打开数据库
        lv_result_search.layoutManager = LinearLayoutManager(mContext)
        memberAdapter = MyAllMemberInfoAdapter(null)
        /**设置加载更多代理委托**/
        lv_result_search.adapter = memberAdapter
        memberAdapter?.setOnItemClickListener { _, _, position ->
            val bean = memberAdapter!!.data[position]
            val intent = Intent(context, MemberDetailsActivity::class.java)
            intent.putExtra("oneMember",bean)
            startActivity(intent)
        }
    }

    override fun getData() {
        mPresenter.selectMSList(db)
    }

    override fun onClick(view: View) {
    }

}
