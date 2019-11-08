package com.membership_score.database.other;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.membership_score.constant.DBConstant;


public class MySqlLiteHelper extends SQLiteOpenHelper {
    /**
     *
     * @param context 上下文对象
     * @param name 创建数据库名字
     * @param factory 工厂
     * @param version 版本
     */
    public MySqlLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public MySqlLiteHelper(Context context){
        super(context,DBConstant.DB_NAME,null,DBConstant.DB_VERSION);
    }
    /*
    创建数据库时使用的函数
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBConstant.CREATE_ADMIN_REGISTER_TAB);
        sqLiteDatabase.execSQL(DBConstant.CREATE_INFO_TAB);
        sqLiteDatabase.execSQL(DBConstant.CREATE_MS_DETAILS_TAB);
        sqLiteDatabase.execSQL(DBConstant.CREATE_MEMBERSHIP_TAB);
    }
    /**
     *  更新数据库时调用
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DBConstant.UPDATA_SQL_VERSION);
        onCreate(sqLiteDatabase);
    }
}