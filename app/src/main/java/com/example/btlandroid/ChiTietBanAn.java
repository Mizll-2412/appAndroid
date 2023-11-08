package com.example.btlandroid;

import java.io.Serializable;

public class ChiTietBanAn implements Serializable {
    private int maBan;
    private int maMonan;
    private int soLuong;
    private double gia;

    public ChiTietBanAn(int maBan, int maMonan, int soLuong,double gia) {
        this.maBan = maBan;
        this.maMonan = maMonan;
        this.soLuong = soLuong;
        this.gia=gia;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public int getMaMonan() {
        return maMonan;
    }

    public void setMaMonan(int maMonan) {
        this.maMonan = maMonan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
}

