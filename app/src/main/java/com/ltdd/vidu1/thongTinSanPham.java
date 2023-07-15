package com.ltdd.vidu1;

import java.io.Serializable;

public class thongTinSanPham implements Serializable {
        int soluong;
        int dongia;
        String ngay;
        String id, ten;

        public thongTinSanPham(String id, String ten, int soluong, int dongia, String ngay) {
            this.id = id;
            this.ten = ten;
            this.soluong = soluong;
            this.dongia = dongia;
            this.ngay = ngay;
        }

        public thongTinSanPham() {
        }

        public String getTen() {
            return ten;
        }

        public void setTen(String ten) {
            this.ten = ten;
        }

        public int getsoluong() {
            return soluong;
        }

        public void setsoluong(int soluong) {
            this.soluong = soluong;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getdongia() {
            return dongia;
        }

        public void setdongia(int dongia) {
            this.dongia = dongia;
        }

        public String getngay() {
            return ngay;
        }

        public void setngay(String ngay) {
            this.ngay = ngay;
        }
    }