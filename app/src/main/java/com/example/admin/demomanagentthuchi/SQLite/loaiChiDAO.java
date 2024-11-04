package com.example.admin.demomanagentthuchi.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.demomanagentthuchi.Model.Mangement_loaiChi;

import java.util.ArrayList;

public class loaiChiDAO {
    SQLiteDatabase db;
    Dbhelper dbh;
    public loaiChiDAO(Context c) {
        dbh=new Dbhelper(c);
    }

    public void AddLoaiChi(Mangement_loaiChi loaiChi){
        db=dbh.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("tenLoaiChi",loaiChi.tenLoaiChi);
        db.insert("loaiChi",null,values);
    }
    public ArrayList<Mangement_loaiChi> ViewLoaiChi(){
        ArrayList<Mangement_loaiChi> list_LoaiChi=new ArrayList<Mangement_loaiChi>();

        db=dbh.getReadableDatabase();
        Cursor c=db.query("loaiChi",null,null,null,null,null,null);
        if (c.moveToFirst()){
            do {
                int _id=c.getInt(0);
                String tenLoaiChi=c.getString(1);
                Mangement_loaiChi loaiChi=new Mangement_loaiChi(_id,tenLoaiChi);
                list_LoaiChi.add(loaiChi);
            }while (c.moveToNext());
        }
        return list_LoaiChi;
    }

    public void DeleteLoaiChi(int _id){
        db=dbh.getWritableDatabase();
        db.delete("loaiChi","_id=?",new String[]{_id+""});

    }
    public void UpdateLoaiChi(Mangement_loaiChi loaiChi){
        db=dbh.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("tenLoaiChi",loaiChi.tenLoaiChi);
        db.update("loaiChi",values,"_id=?",new String[]{loaiChi._id+""});

    }
}
