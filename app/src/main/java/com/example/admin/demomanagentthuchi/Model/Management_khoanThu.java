package com.example.admin.demomanagentthuchi.Model;

public class Management_khoanThu {
    public int _id;
    public String tenKhoanThu;
    public String noiDung;
    public String ngayThu;
    public int _idLoaiThu;

    public Management_khoanThu(String tenKhoanThu, String noiDung, String ngayThu, int _idLoaiThu) {
        this.tenKhoanThu = tenKhoanThu;
        this.noiDung = noiDung;
        this.ngayThu = ngayThu;
        this._idLoaiThu = _idLoaiThu;
    }

    public Management_khoanThu(int _id, String tenKhoanThu, String noiDung, String ngayThu, int _idLoaiThu) {
        this._id = _id;
        this.tenKhoanThu = tenKhoanThu;
        this.noiDung = noiDung;
        this.ngayThu = ngayThu;
        this._idLoaiThu = _idLoaiThu;
    }
}
