package com.membership_score.ui.home.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.membership_score.R;
import com.membership_score.database.bean.MemberShipScoreNumResult;

import java.util.List;

/**
 * 单会员总积分记录
 * **/
public class MyOneMemberShipRecordAdapter extends BaseQuickAdapter<MemberShipScoreNumResult, MyOneMemberShipRecordAdapter.MyOneMemberShipRecordViewHolder> {

    public MyOneMemberShipRecordAdapter(@Nullable List<MemberShipScoreNumResult> data) {
        super(R.layout.fragment_membership_record_adapter_item, data);
    }

    @Override
    protected void convert(MyOneMemberShipRecordViewHolder helper, MemberShipScoreNumResult item) {
        helper.tv_member_time.setText(item.getMembership_score_pay_time());
        helper.tv_recharge_money.setText("￥"+item.getMembership_score_pay_money_num());
        helper.tv_recharge_money_get_ship_num.setText(item.getMembership_score_get_num()+"");
        /************
         微信：1 支付宝 2 现金 3 银行卡 4 其他 5
         ********************/
        switch (item.getMs_pay_type()){
            case 1:
                helper.tv_recharge_money_pay_type.setText("微信");
                break;
            case 2:
                helper.tv_recharge_money_pay_type.setText("支付宝");
                break;
            case 3:
                helper.tv_recharge_money_pay_type.setText("现金");
                break;
            case 4:
                helper.tv_recharge_money_pay_type.setText("银行卡");
                break;
            default:
                helper.tv_recharge_money_pay_type.setText("其他");
                break;
        }
    }


    public class MyOneMemberShipRecordViewHolder extends BaseViewHolder {
        private TextView tv_member_time;
        private TextView tv_recharge_money;
        private TextView tv_recharge_money_get_ship_num;
        private TextView tv_recharge_money_pay_type;

        public MyOneMemberShipRecordViewHolder(View view) {
            super(view);
            tv_member_time = view.findViewById(R.id.tv_member_time);
            tv_recharge_money = view.findViewById(R.id.tv_recharge_money);
            tv_recharge_money_get_ship_num = view.findViewById(R.id.tv_recharge_money_get_ship_num);
            tv_recharge_money_pay_type = view.findViewById(R.id.tv_recharge_money_pay_type);
        }
    }
}
