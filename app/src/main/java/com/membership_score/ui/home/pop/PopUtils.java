package com.membership_score.ui.home.pop;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.membership_score.R;

public class PopUtils {
    public static void showPayTypeChane(final Activity activity, View showDown, final IPayTypeCallBack callBack){
        final PopupWindow popupWindow = new PopupWindow(activity);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View pop= View.inflate(activity, R.layout.pop_show_pay_type_chane,null);
        TextView tv_chane_wx = (TextView)pop.findViewById(R.id.tv_chane_wx);
        TextView tv_chane_apay = (TextView)pop.findViewById(R.id.tv_chane_apay);
        TextView tv_chane_bank = (TextView)pop.findViewById(R.id.tv_chane_bank);
        TextView tv_chane_xj = (TextView)pop.findViewById(R.id.tv_chane_xj);
        TextView tv_chane_other = (TextView)pop.findViewById(R.id.tv_chane_other);
        TextView tv_cancel = (TextView)pop.findViewById(R.id.tv_cancel);
        tv_chane_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                callBack.onChaneWX();
            }
        });
        tv_chane_apay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                callBack.onChaneZFB();
            }
        });
        tv_chane_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //用户地址不正确
                popupWindow.dismiss();
                callBack.onChaneBank();
            }
        });
        tv_chane_xj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //用户地址不正确
                popupWindow.dismiss();
                callBack.onChaneXJ();
            }
        });
        tv_chane_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //其他
                popupWindow.dismiss();
                callBack.onChaneOther();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(activity,1f);
            }
        });
        popupWindow.setContentView(pop);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        backgroundAlpha(activity,0.7f);
        //设置动画效果
        popupWindow.setAnimationStyle(R.style.popupAnimation);
        popupWindow.showAtLocation(showDown, Gravity.BOTTOM,0,0);
//        popupWindow.showAsDropDown(rl_view);
    }
    public static void showSexChane(final Activity activity, View showDown, final ISexCallBack callBack){
        final PopupWindow popupWindow = new PopupWindow(activity);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View pop= View.inflate(activity, R.layout.pop_show_sex_chane,null);
        TextView tv_chane_boy = (TextView)pop.findViewById(R.id.tv_chane_boy);
        TextView tv_chane_gird = (TextView)pop.findViewById(R.id.tv_chane_gird);
        TextView tv_chane_other = (TextView)pop.findViewById(R.id.tv_chane_other);
        TextView tv_cancel = (TextView)pop.findViewById(R.id.tv_cancel);
        tv_chane_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                callBack.onChaneBoy();
            }
        });
        tv_chane_gird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                callBack.onChaneGird();
            }
        });
        tv_chane_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //其他
                popupWindow.dismiss();
                callBack.onChaneOther();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(activity,1f);
            }
        });
        popupWindow.setContentView(pop);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        backgroundAlpha(activity,0.7f);
        //设置动画效果
        popupWindow.setAnimationStyle(R.style.popupAnimation);
        popupWindow.showAtLocation(showDown, Gravity.BOTTOM,0,0);
//        popupWindow.showAsDropDown(rl_view);
    }

    /**
     * 设置popupwindow外面背景透明度
     * @param bgalpha 透明度  0-1   0-透明   1-不透明
     */
    public static void backgroundAlpha(Activity activity, float bgalpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgalpha;
        activity.getWindow().setAttributes(lp);
    }

}
