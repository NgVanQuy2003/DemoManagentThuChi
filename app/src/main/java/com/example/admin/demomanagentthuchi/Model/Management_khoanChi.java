package com.example.admin.demomanagentthuchi.Model;

public class Management_khoanChi {
    public int _id;
    public String tenKhoanChi;
    public String noiDung;
    public String ngayChi;
    public int _idLoaiChi;

    public Management_khoanChi(String tenKhoanChi, String noiDung, String ngayChi, int _idLoaiChi) {
        this.tenKhoanChi = tenKhoanChi;
        this.noiDung = noiDung;
        this.ngayChi = ngayChi;
        this._idLoaiChi = _idLoaiChi;
    }

    public Management_khoanChi(int _id, String tenKhoanChi, String noiDung, String ngayChi, int _idLoaiChi) {
        this._id = _id;
        this.tenKhoanChi = tenKhoanChi;
        this.noiDung = noiDung;
        this.ngayChi = ngayChi;
        this._idLoaiChi = _idLoaiChi;
    }


}
