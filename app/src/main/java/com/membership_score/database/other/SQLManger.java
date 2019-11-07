package com.membership_score.database.other;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import com.membership_score.baselib.utils.DateUtil;
import com.membership_score.baselib.utils.SPUtils;
import com.membership_score.constant.DBConstant;
import com.membership_score.database.bean.*;
import com.membership_score.ui.account.bean.ForGetPWResultBean;

import java.util.ArrayList;

public class SQLManger {
    private final String TAG = "SQLManger";

    private static SQLManger sqlManger;

    public static synchronized SQLManger getIntance() {
        if (sqlManger == null) {
            sqlManger = new SQLManger();
        }
        return sqlManger;
    }

    /**
     * 查找方法
     * 返回的是一个Cursor对象
     * selectionArgs 查询条件占位符
     */
    public static Cursor selectSQL(SQLiteDatabase db, String sql, String[] selectionArgs) {
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, selectionArgs);
        }
        return cursor;
    }

    public static Cursor selectMSSQL(SQLiteDatabase db, String sql, String[] selectionArgs) {
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, selectionArgs);
        }
        return cursor;
    }

    /**
     * 将Curcor对象转化成list集合
     *
     * @param db
     * @return 集合对象
     */
    public ArrayList<MemberShipInfoDBResult> selectMemberInfoToList(SQLiteDatabase db) {
        Cursor cursor = selectSQL(db, DBConstant.SELECT_ALL_MEMBER, null);
        ArrayList<MemberShipInfoDBResult> list = new ArrayList<>();
        while (cursor.moveToNext()) {   //判断游标是否有下一个字段
            //getColumnIndext作用是返回给定字符串的下标(指的是int类型)
            int columnIndex = cursor.getColumnIndex(DBConstant.ID);
            //通过下标找到指定value
            int id = cursor.getInt(columnIndex);  // 获取id
            String userId = cursor.getString(cursor.getColumnIndex(DBConstant.USER_ID));
            Integer sex = cursor.getInt(cursor.getColumnIndex(DBConstant.SEX));
            String name = cursor.getString(cursor.getColumnIndex(DBConstant.NAME));
            Integer level = cursor.getInt(cursor.getColumnIndex(DBConstant.LEVEL));
            String phoneNum = cursor.getString(cursor.getColumnIndex(DBConstant.PHONENUM));
            String icon = cursor.getString(cursor.getColumnIndex(DBConstant.ICON));
            String address = cursor.getString(cursor.getColumnIndex(DBConstant.ADDRESS));
            Integer ms_last_time_num = cursor.getInt(cursor.getColumnIndex(DBConstant.MS_LAST_TIME_NUM));
            String ms_last_time = cursor.getString(cursor.getColumnIndex(DBConstant.MS_LAST_TIME));

            ArrayList<MemberShipScoreNumResult> ms_details = selectMemberShipToList(db, userId);
            Log.i(TAG, "ms_details---->" + ms_details.size());
            Integer ms_total_num = 0;
            if (!ms_details.isEmpty()) {
                ms_total_num = getMSTotalNum(ms_details);
            }
            Log.i(TAG, "ms_total_num---->" + ms_total_num);
            MemberShipInfoDBResult th_data = new MemberShipInfoDBResult(
                    userId, sex, level, name, phoneNum,
                    icon, address, ms_total_num, ms_last_time_num,
                    ms_last_time, ms_details);
            list.add(th_data);
        }
        cursor.close();
        return list;
    }


    /**
     * 将Curcor对象转化成list集合
     *
     * @param db
     * @param nameSelect
     * @param phoneSelect
     * @return 集合对象
     */
    public ArrayList<MemberShipInfoDBResult> selectOneMemberPhoneAndName(SQLiteDatabase db, String phoneSelect, String nameSelect) {
        Cursor cursor;
        if (!TextUtils.isEmpty(phoneSelect)) {
            cursor = selectSQL(db, DBConstant.SELECT_ONE_MEMBER_PHONE, new String[]{phoneSelect});
        } else {
            cursor = selectSQL(db, DBConstant.SELECT_ONE_MEMBER_NAME, new String[]{nameSelect});
        }
        ArrayList<MemberShipInfoDBResult> list = new ArrayList<>();
        while (cursor.moveToNext()) {   //判断游标是否有下一个字段
            //getColumnIndext作用是返回给定字符串的下标(指的是int类型)
            int columnIndex = cursor.getColumnIndex(DBConstant.ID);
            //通过下标找到指定value
            int id = cursor.getInt(columnIndex);  // 获取id
            String userId = cursor.getString(cursor.getColumnIndex(DBConstant.USER_ID));
            Integer sex = cursor.getInt(cursor.getColumnIndex(DBConstant.SEX));
            String name = cursor.getString(cursor.getColumnIndex(DBConstant.NAME));
            Integer level = cursor.getInt(cursor.getColumnIndex(DBConstant.LEVEL));
            String phoneNum = cursor.getString(cursor.getColumnIndex(DBConstant.PHONENUM));
            String icon = cursor.getString(cursor.getColumnIndex(DBConstant.ICON));
            String address = cursor.getString(cursor.getColumnIndex(DBConstant.ADDRESS));
            Integer ms_last_time_num = cursor.getInt(cursor.getColumnIndex(DBConstant.MS_LAST_TIME_NUM));
            String ms_last_time = cursor.getString(cursor.getColumnIndex(DBConstant.MS_LAST_TIME));

            ArrayList<MemberShipScoreNumResult> ms_details = selectMemberShipToList(db, userId);
            Log.i(TAG, "ms_details---->" + ms_details.size());
            Integer ms_total_num = 0;
            if (!ms_details.isEmpty()) {
                ms_total_num = getMSTotalNum(ms_details);
            }
            Log.i(TAG, "ms_total_num---->" + ms_total_num);
            MemberShipInfoDBResult th_data = new MemberShipInfoDBResult(
                    userId, sex, level, name, phoneNum,
                    icon, address, ms_total_num, ms_last_time_num,
                    ms_last_time, ms_details);
            list.add(th_data);
        }
        cursor.close();
        return list;
    }


    public static Integer getMSTotalNum(ArrayList<MemberShipScoreNumResult> ms_details) {
        Integer ms_total_num = 0;
        if (ms_details.isEmpty()) {
            return ms_total_num;
        }
        for (int i = 0; i < ms_details.size(); i++) {
            ms_total_num = ms_total_num + ms_details.get(i).getMembership_score_get_num();
        }
        return ms_total_num;
    }

    //查询全部单个会员对应的
    public ArrayList<MemberShipScoreNumResult> selectMemberShipToList(SQLiteDatabase db, String member_id) {
        Cursor cursor = null;
        try {
            cursor = selectMSSQL(db, DBConstant.SELECT_ALL_MEMBER_NUM, new String[]{member_id});
        } catch (Exception ex) {
            cursor.close();
            return null;
        }
        ArrayList<MemberShipScoreNumResult> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Integer user_id = cursor.getInt(cursor.getColumnIndex(DBConstant.MS_USER_ID));
            Integer membership_score_pay_money_num = cursor.getInt(cursor.getColumnIndex(DBConstant.MEMBERSHIP_SCORE_PAY_MONEY_NUM));
            Integer membership_score_get_num = cursor.getInt(cursor.getColumnIndex(DBConstant.MEMBERSHIP_SCORE_GET_NUM));
            String membership_score_pay_time = cursor.getString(cursor.getColumnIndex(DBConstant.MEMBERSHIP_SCORE_PAY_TIME));
            Integer ms_pay_type = cursor.getInt(cursor.getColumnIndex(DBConstant.MS_PAY_TYPE));
            MemberShipScoreNumResult tp_data = new MemberShipScoreNumResult(user_id, membership_score_pay_money_num, membership_score_get_num
                    , membership_score_pay_time, ms_pay_type);
            list.add(tp_data);
        }
        cursor.close();
        return list;
    }

    //查询全部注册用户
    public ArrayList<RegisterDBBean> selectRegisterDBBean(SQLiteDatabase db) {
        Cursor cursor = null;
        try {
            cursor = selectMSSQL(db, DBConstant.SELECT_ALL_REGISTER_ADMIN, null);
        } catch (Exception ex) {
            cursor.close();
            return null;
        }
        ArrayList<RegisterDBBean> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Integer adminLevel = cursor.getInt(cursor.getColumnIndex(DBConstant.ADMIN_LEVEL));
            String adminId = cursor.getString(cursor.getColumnIndex(DBConstant.ADMIN_ID));
            Integer adminSex = cursor.getInt(cursor.getColumnIndex(DBConstant.ADMIN_SEX));
            String adminNickname = cursor.getString(cursor.getColumnIndex(DBConstant.ADMIN_NICKNAME));
            String adminPhoneNum = cursor.getString(cursor.getColumnIndex(DBConstant.ADMIN_PHONENUM));
            String adminIcon = cursor.getString(cursor.getColumnIndex(DBConstant.ADMIN_ICON));
            String adminAddress = cursor.getString(cursor.getColumnIndex(DBConstant.ADMIN_ADDRESS));
            String adminRegisterTime = cursor.getString(cursor.getColumnIndex(DBConstant.ADMIN_REGISTERTIME));
            String adminLoginName = cursor.getString(cursor.getColumnIndex(DBConstant.ADMIN_LOGINNAME));
            String adminLoginPassWord = cursor.getString(cursor.getColumnIndex(DBConstant.ADMIN_LOGINPASSWORD));
            RegisterDBBean tp_data = new RegisterDBBean(adminLevel, adminId, adminSex
                    , adminNickname, adminPhoneNum, adminIcon, adminAddress, adminRegisterTime, adminLoginName, adminLoginPassWord);
            list.add(tp_data);
        }
        cursor.close();
        return list;
    }

    /**
     * @param user_id 会员记录ID
     *                删除单个会员记录
     */
    public long deleteOneMemberInfo(SQLiteDatabase db, String user_id) {
        // TODO Auto-generated method stub
        ContentValues values = new ContentValues();
        values.put(DBConstant.USER_ID, user_id);
        String where = DBConstant.USER_ID + "=?";
        int i = db.delete(DBConstant.MS_INFO_TAB_NAME, where, new String[]{user_id});
        db.close();
        if (i > 0) {
            Log.i(TAG, "数据会员删除成功!");
            return 1;
        }
        Log.i(TAG, "数据未删除!");
        return -1;
    }

    /**
     * @param member_id 积分记录ID
     *                  删除单个会员对应积分记录
     */
    public long deleteOneMemberShipInfo(SQLiteDatabase db, String member_id) {
        // TODO Auto-generated method stub
        String where = "where " + DBConstant.MS_USER_ID + "=?";
        int i = db.delete(DBConstant.MS_DETAILS_TAB_NAME, where, new String[]{member_id});
        if (i > 0) {
            Log.i(TAG, "数据删除成功!");
            return 1;
        }
        Log.i(TAG, "数据未删除!");
        return -1;
    }

    /**
     * @param member_id 积分记录ID
     *                  删除单个会员对应积分记录
     */
    public long upDataMemberInfo(SQLiteDatabase db, String member_id, String name, String phone) {
        int resultCode = -1;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBConstant.NAME, name);
            contentValues.put(DBConstant.PHONENUM, phone);
            resultCode = db.update(DBConstant.MS_INFO_TAB_NAME, contentValues, DBConstant.UPDATA_MEMBER_DATA_WHERE, new String[]{member_id});
            db.close();
            return resultCode;
        } catch (Exception ex) {
            db.close();
            return resultCode;
        }
    }

    /**
     * @param info 添加的会员数据
     *             插入数据
     */
    public long insertOneMember(SQLiteDatabase db, MemberShipInfo info) {
        ArrayList<MemberShipInfoDBResult> list = selectMemberInfoToList(db);
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                //数据库中已存在
                if (list.get(i).getName().equals(info.getName()) || list.get(i).getPhoneNum().equals(info.getPhoneNum())) {
                    return -2;
                }
            }
        }

        // TODO Auto-generated method stub
        ContentValues values = new ContentValues();
        values.put(DBConstant.NAME, info.getName());
        values.put(DBConstant.USER_ID, info.getUserId());
        values.put(DBConstant.LEVEL, 0);
        values.put(DBConstant.PHONENUM, info.getPhoneNum());
        values.put(DBConstant.ICON, info.getIcon());
        values.put(DBConstant.ADDRESS, info.getAddress());
        values.put(DBConstant.MS_LAST_TIME, DateUtil.getBeforeOrAfterDate(7, "yyyy-MM-dd"));
        values.put(DBConstant.MS_LAST_TIME_NUM, 0);

        return db.insert(DBConstant.MS_INFO_TAB_NAME, null, values);
    }

    /**
     * @param info 添加的会员单挑积分数据
     *             插入数据
     */
    public long insertOneMemberShip(SQLiteDatabase db, MemberShipScoreNum info) {
        // TODO Auto-generated method stub
        ContentValues values = new ContentValues();
        values.put(DBConstant.MS_USER_ID, info.getUser_id());
        values.put(DBConstant.MEMBERSHIP_SCORE_PAY_MONEY_NUM, info.getMembership_score_pay_money_num());
        //支付一元获得一积分
        values.put(DBConstant.MEMBERSHIP_SCORE_GET_NUM, info.getMembership_score_pay_money_num());
        values.put(DBConstant.MEMBERSHIP_SCORE_PAY_TIME, info.getMembership_score_pay_time());
        values.put(DBConstant.MS_PAY_TYPE, info.getMs_pay_type());
        return db.insert(DBConstant.MS_DETAILS_TAB_NAME, null, values);
    }

    /**
     * @param registerDBBean 注册用户信息
     *                       注册用户
     */
    public long insertRegister(SQLiteDatabase db, RegisterDBBean registerDBBean) {
        // TODO Auto-generated method stub
        long result=-1;
        ArrayList<RegisterDBBean> listData = selectRegisterDBBean(db);
        if (listData.isEmpty()) {
            result=insertUser(db, registerDBBean);
            if(result>0){
                SPUtils.putObject("userInfoBean",registerDBBean);
            }
            return result;
        }
        for (RegisterDBBean bean : listData) {
            if (registerDBBean.getAdminLoginName().equals(bean.getAdminLoginName())
                    ||registerDBBean.getAdminPhoneNum().equals(bean.getAdminPhoneNum())) {
                    return 2;
                }
            }

        result=insertRegister(db,registerDBBean);
        if(result>0){
            SPUtils.putObject("userInfoBean",registerDBBean);
        }
        return result;
    }

    private long insertUser(SQLiteDatabase db, RegisterDBBean registerDBBean){
        ContentValues values = new ContentValues();
        values.put(DBConstant.ADMIN_ID, registerDBBean.getAdminId());
        values.put(DBConstant.ADMIN_ADDRESS, registerDBBean.getAdminAddress());
        values.put(DBConstant.ADMIN_ICON, registerDBBean.getAdminIcon());
        values.put(DBConstant.ADMIN_LEVEL, registerDBBean.getAdminLevel());
        values.put(DBConstant.ADMIN_REGISTERTIME, registerDBBean.getAdminRegisterTime());
        values.put(DBConstant.ADMIN_NICKNAME, registerDBBean.getAdminNickname());
        values.put(DBConstant.ADMIN_PHONENUM, registerDBBean.getAdminPhoneNum());
        values.put(DBConstant.ADMIN_LOGINNAME, registerDBBean.getAdminLoginName());
        values.put(DBConstant.ADMIN_LOGINPASSWORD, registerDBBean.getAdminLoginPassWord());
        values.put(DBConstant.ADMIN_SEX, registerDBBean.getAdminSex());
        return db.insert(DBConstant.ADMIN_REGISTER_TAB, null, values);
    }

    /**
     * @param userName
     * @param PassWord
     * */
    public long loginAdmin(SQLiteDatabase db, String userName,String PassWord) {
        ArrayList<RegisterDBBean> listData=selectRegisterDBBean(db);
        if(listData.isEmpty()){
            return -1;
        }
        for(RegisterDBBean bean:listData){
            if(userName.equals(bean.getAdminLoginName())&&
                    PassWord.equals(bean.getAdminLoginPassWord())){
                if(bean.getAdminLevel()>4){
                    SPUtils.putObject("userInfoBean",bean);
                    return 1;
                }else {
                    return 2;
                }
            }
        }
        return -1;
    }


    /**
     * @param usreID 用户ID
     * @param newPassWord 新密码
     * 修改用户密码
     */
    public long upDataUserPW(SQLiteDatabase db, String usreID,String newPassWord) {
        int resultCode = -1;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBConstant.ADMIN_LOGINPASSWORD, newPassWord);
            resultCode = db.update(DBConstant.ADMIN_REGISTER_TAB, contentValues, DBConstant.UPDATA_USER_DATA_WHERE, new String[]{usreID});
            db.close();
            return resultCode;
        } catch (Exception ex) {
            db.close();
            return resultCode;
        }
    }

    /**
     * @param phone 用户手机号
     * 找回用户密码
     */
    public ForGetPWResultBean forgetUserPW(SQLiteDatabase db, String phone) {
        ForGetPWResultBean resultBean=null;
        try {
            ArrayList<RegisterDBBean> listData = selectRegisterDBBean(db);
            for (RegisterDBBean bean:listData){
                if(bean.getAdminPhoneNum().equals(phone)){
                    resultBean=new ForGetPWResultBean(bean.getAdminLoginName(),bean.getAdminLoginPassWord());
                }
            }
            db.close();
            return resultBean;
        } catch (Exception ex) {
            db.close();
            return resultBean;
        }
    }
}
