package com.example.btlandroid;

import java.io.Serializable;

public class table implements Serializable {
    private int maBan;
    //private Date thoiGian;
    private String tenban;
    private int tongTien;
   // private byte[] hinh;
    private int soNguoi;
    private int hinh;

    public table(int maBan, String tenban, int soNguoi,int tongTien,int hinh) {
        this.maBan = maBan;
        this.tenban=tenban;
        this.tongTien = tongTien;
        this.soNguoi=soNguoi;
        this.hinh=hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public int getHinh() {
        return hinh;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public String getTenban() {
        return tenban;
    }

    public void setTenban(String tenban) {
        this.tenban = tenban;
    }
    //
//    public Date getThoiGian() {
//        return thoiGian;
//    }
//
//    public void setThoiGian(Date thoiGian) {
//        this.thoiGian = thoiGian;
//    }


//    public byte[] getHinh() {
//        return hinh;
//    }
//
//    public void setHinh(byte[] hinh) {
//        this.hinh = hinh;
//    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
//
//    public ArrayList<dish> getDsMonAn() {
//        return dsMonAn;
//    }
//
//    public void setDsMonAn(ArrayList<dish> dsMonAn) {
//        this.dsMonAn = dsMonAn;
//    }
//
    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }
}

