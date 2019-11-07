package com.membership_score.ui.home.contract;

import com.membership_score.base.BaseView;

/**
 * @author YK
 * @createtime 2019/10/29 11:31
 */
public interface HomeFragmentContract {

    interface IView extends BaseView {
        void loginSuccess();

        void loginFaild(String msg);
    }

    interface IPresenter {
        void login();
    }

}


