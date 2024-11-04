package com.example.admin.demomanagentthuchi.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.demomanagentthuchi.Model.Management_khoanChi;

import java.util.ArrayList;

public class khoanChiDAO {
    SQLiteDatabase db;
    Dbhelper dbh;

    public khoanChiDAO(Context c) {
        dbh = new Dbhelper(c);
    }

    public void addKhoanChi(Management_khoanChi khoanChi) {
        db = dbh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenKhoanChi", khoanChi.tenKhoanChi);
        values.put("noiDung", khoanChi.noiDung);
        values.put("ngayChi", khoanChi.ngayChi);
        values.put("_idLoaiChi", khoanChi._idLoaiChi);
        db.insert("khoanChi", null, values);
    }

    public ArrayList<Management_khoanChi> ViewKhoanChi() {
        ArrayList<Management_khoanChi> list_KhoanChi = new ArrayList<Management_khoanChi>();
        db = dbh.getReadableDatabase();
        Cursor c = db.query("khoanChi", null, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                int _id = c.getInt(0);
                String tenKhoanChi = c.getString(1);
                String noiDung = c.getString(2);
                String ngayChi = c.getString(3);
                int _idLoaiChi = c.getInt(4);
                Management_khoanChi khoanChi = new Management_khoanChi(_id, tenKhoanChi, noiDung, ngayChi, _idLoaiChi);
                list_KhoanChi.add(khoanChi);
            } while (c.moveToNext());
        }
        return list_KhoanChi;
    }
    public void DeleteKhoanChi(int _id){
        db=dbh.getWritableDatabase();
        db.delete("khoanChi","_id=?",new String[]{_id+""});

    }
    public void UpdateKhoanChi(Management_khoanChi khoanChi){
        db=dbh.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("tenKhoanChi", khoanChi.tenKhoanChi);
        values.put("noiDung", khoanChi.noiDung);
        values.put("ngayChi", khoanChi.ngayChi);
        values.put("_idLoaiChi", khoanChi._idLoaiChi);
        db.update("khoanChi",values,"_id=?",new String[]{khoanChi._id+""});
    }
}
