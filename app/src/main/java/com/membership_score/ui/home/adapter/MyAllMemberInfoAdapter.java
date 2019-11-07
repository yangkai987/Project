package com.membership_score.ui.home.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.membership_score.R;
import com.membership_score.database.bean.MemberShipInfoDBResult;
import java.util.List;

public class MyAllMemberInfoAdapter extends BaseQuickAdapter<MemberShipInfoDBResult, MyAllMemberInfoAdapter.MyAllMemberViewHolder> {

    public MyAllMemberInfoAdapter(@Nullable List<MemberShipInfoDBResult> data) {
        super(R.layout.fragment_membership_score_home_adapter_item, data);
    }

    @Override
    protected void convert(MyAllMemberViewHolder helper, MemberShipInfoDBResult item) {
        helper.tv_member_name.setText(item.getName());
        helper.tv_member_phone.setText(item.getPhoneNum());
        Integer total = 0;
        if(item.getMs_total_num()!=null){
            total = item.getMs_total_num();
        }
        helper.tv_member_ship_total_num.setText(total+"");
    }


    public class MyAllMemberViewHolder extends BaseViewHolder {
        private TextView tv_member_name;
        private TextView tv_member_phone;//消息标题
        private TextView tv_member_ship_total_num;//消息内容

        public MyAllMemberViewHolder(View view) {
            super(view);
            tv_member_name = view.findViewById(R.id.tv_member_name);
            tv_member_phone = view.findViewById(R.id.tv_member_phone);
            tv_member_ship_total_num = view.findViewById(R.id.tv_member_ship_total_num_text);
        }
    }
}
