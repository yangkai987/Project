package com.membership_score.constant



/**
 * @author yk
 * @createtime 2019/8/20 16:09
 *
 * 数据库常量类
 */

object DBConstant {

    //数据库名称
    const val DB_NAME = "membership_score.db"
    const val DB_VERSION = 1
    //会员数据库名称
    const val MS_INFO_TAB_NAME = "membership_score_info_tab"
    //会员积分明细表名称
    const val MS_DETAILS_TAB_NAME = "membership_score_context_tab"

    const val ID = "id"
    const val USER_ID = "user_id"
    const val SEX = "sex"
    const val MS_USER_ID = "ms_user_id"
    const val NAME = "name"//会员名称
    const val LEVEL = "level"//等级
    const val PHONENUM ="phoneNum"//会员电话
    const val ICON = "icon"//会员头像
    const val ADDRESS = "address"//会员 地址
    const val MS_TOTAL_NUM = "ms_total_num"//会员总积分积分
    const val MS_LAST_TIME_NUM = "ms_last_time_num"//会员上一次积分
    const val MS_LAST_TIME = "ms_last_time"//上一次积分时间
    const val MEMBERSHIP_SCORE_PAY_MONEY_NUM = "membership_score_pay_money_num"//充值金额
    const val MEMBERSHIP_SCORE_GET_NUM = "membership_score_get_num"//充值积分
    const val MEMBERSHIP_SCORE_PAY_TIME = "membership_score_pay_time"//积分时间
    const val MS_PAY_TYPE = "ms_pay_type"//充值类型
    const val UPDATA_SQL_VERSION = "DROP TABLE IF EXISTS $MS_INFO_TAB_NAME and $MS_DETAILS_TAB_NAME"

    /************用户登录数据表**************/
    const val ADMIN_REGISTER_TAB="admin_register_tab"//会员ID
    const val ADMIN_ID="adminId"//会员ID
    const val ADMIN_SEX="adminSex"//会员性别
    const val ADMIN_NICKNAME="adminNickname"//会员昵称
    const val ADMIN_PHONENUM="adminPhoneNum"//会员电话
    const val ADMIN_ICON="adminIcon"//会员头像
    const val ADMIN_ADDRESS="adminAddress"//会员 地址
    const val ADMIN_REGISTERTIME="adminRegisterTime"//会员 注册时间
    const val ADMIN_LOGINNAME="adminLoginName"//会员 登陆用户名
    const val ADMIN_LOGINPASSWORD="adminLoginPassWord"//会员 登陆密码
    const val ADMIN_LEVEL="adminLevel"//等级1-4常规用户  5以及之上为admin用户

    const val LOGINS_TATUE="loginStatue"//登陆状态

    //注册用户表
    const val CREATE_ADMIN_REGISTER_TAB = "create table " +ADMIN_REGISTER_TAB + "(" +
            ID + " integer primary key autoincrement," +
            ADMIN_ID + " varchar(50)," +
            ADMIN_SEX + " integer," +
            ADMIN_NICKNAME + " text," +
            ADMIN_PHONENUM + " varchar(50)," +
            ADMIN_ICON + " varchar(100)," +
            ADMIN_ADDRESS + " varchar(50)," +
            ADMIN_REGISTERTIME + " varchar(50)," +
            ADMIN_LOGINNAME + " text," +
            ADMIN_LOGINPASSWORD + " varchar(50)," +
            ADMIN_LEVEL + " integer" +
            ")"

    const val CREATE_INFO_TAB = "create table " +MS_INFO_TAB_NAME + "(" +
            ID + " integer primary key autoincrement," +
            USER_ID + " varchar(50)," +
            NAME + " varchar(50)," +
            LEVEL + " integer," +
            SEX + " integer," +
            PHONENUM + " varchar(50)," +
            ICON + " varchar(100)," +
            ADDRESS + " varchar(50)," +
            MS_TOTAL_NUM + " integer," +
            MS_LAST_TIME_NUM + " integer," +
            MS_LAST_TIME + " varchar(50)" +
            ")"
    const val CREATE_MS_DETAILS_TAB = "create table " +MS_DETAILS_TAB_NAME + "(" +
            ID + " integer primary key autoincrement," +
            MS_USER_ID + " varchar(50)," +
            MEMBERSHIP_SCORE_PAY_MONEY_NUM + " integer," +
            MEMBERSHIP_SCORE_GET_NUM + " integer," +
            MEMBERSHIP_SCORE_PAY_TIME + " varchar(50)," +
            MS_PAY_TYPE + " integer)"

    const val SELECT_ALL_MEMBER = "select * from $MS_INFO_TAB_NAME"

    const val SELECT_ONE_MEMBER_PHONE = "select * from $MS_INFO_TAB_NAME where $PHONENUM =?"
    const val SELECT_ONE_MEMBER_NAME = "select * from $MS_INFO_TAB_NAME where $NAME =?"

    const val SELECT_ONE_MEMBER = "select * from $MS_INFO_TAB_NAME where id=?"

    const val SELECT_ALL_MEMBER_NUM = "select * from $MS_DETAILS_TAB_NAME where ms_user_id=?"
    const val SELECT_ALL_REGISTER_ADMIN = "select * from $ADMIN_REGISTER_TAB"

    //修改会员资料
    const val UPDATA_MEMBER_DATA_WHERE = "$USER_ID=?"

    //修改会员资料
    const val UPDATA_USER_DATA_WHERE = "$ADMIN_ID=?"
}














