package com.example.admin.demomanagentthuchi.SQLite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {
    public Dbhelper(Context context) {
        super(context, "thu_Chi_Management", null, 1);
    }

    public Dbhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*


         */
        String sql = "create table loaiThu" +
                "(" +
                "_id integer primary key autoincrement, " +
                "tenLoaiThu text" +
                ")";
        db.execSQL(sql);

        String sql2 = "create table khoanThu" +
                "( " +
                "_id integer primary key autoincrement, " +
                "tenKhoanThu text," +
                "noiDung float," +
                "ngayThu text, " +
                "_idLoaiThu integer" +
                ")";
        db.execSQL(sql2);

        /*


         */
        String sql3 = "create table loaiChi" +
                "( " +
                "_id  integer primary key autoincrement, " +
                "tenLoaiChi text" +
                ")";
        db.execSQL(sql3);

        String sql4 = "create table khoanChi" +
                "( " +
                "_id integer primary key autoincrement, " +
                "tenKhoanChi text, " +
                "noiDung float, " +
                "ngayChi text, " +
                "_idLoaiChi integer" +
                ")";
        db.execSQL(sql4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists loaiThu"); // được sử dụng để xóa bảng có tên "loaiThu" khỏi cơ sở dữ liệu SQLite.
        db.execSQL("drop table if exists khoanThu");
        db.execSQL("drop table if exists loaiChi");
        db.execSQL("drop table if exists khoanChi");
        onCreate(db);
    }
}
