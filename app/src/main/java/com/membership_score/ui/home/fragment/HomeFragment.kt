package com.membership_score.ui.home.fragment

import android.view.View
import com.orhanobut.logger.Logger
import com.membership_score.baselib.image.ImageLoader
import com.membership_score.baselib.utils.ToastUtil
import com.membership_score.R
import com.membership_score.base.BaseFragment
import com.membership_score.ui.home.contract.HomeFragmentContract
import com.membership_score.ui.home.presenter.HomeFragmentPresenter
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * @author zhoujiulong
 * @createtime 2019/2/27 11:29
 */
class HomeFragment : BaseFragment<HomeFragmentPresenter>(), HomeFragmentContract.IView {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(rootView: View) {
    }

    override fun initPresenter() {
        mPresenter = HomeFragmentPresenter()
    }

    override fun initListener() {
        setOnClick(mRootView.bt, mRootView.btrequest)
    }

    override fun initData() {
        mRootView.tvPackageName.text = mContext.packageName
    }

    override fun getData() {
        mRootView.loadingView.showLoading()
        mRootView.loadingView.postDelayed({
            if (mRootView.loadingView != null) {
                mRootView.loadingView.showContent()
            }
        }, 3000)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.bt -> {
                val url =
                    "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1946151466,3034958414&fm=26&gp=0.jpg"
                ImageLoader.getInstance().disPlayImageProgress(
                    this@HomeFragment,
                    url,
                    mRootView.iv,
                    R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher
                ) { imageUrl, percent, bytesRead, totalBytes, isDone, exception ->
                    Logger.e("img1:progress:$percent")
                }
                val url2 =
                    "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2824970053,2177995476&fm=26&gp=0.jpg"
                ImageLoader.getInstance().disPlayImageProgress(
                    this@HomeFragment,
                    url2,
                    mRootView.iv2,
                    R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher
                ) { imageUrl, percent, bytesRead, totalBytes, isDone, exception ->
                    Logger.e("img2:progress:$percent")
                }
            }
            R.id.btrequest -> mPresenter.login()
        }
    }

    override fun loginSuccess() {
        ToastUtil.toast("登陸成功")
    }

    override fun loginFaild(msg: String) {
        ToastUtil.toast(msg)
    }
}
