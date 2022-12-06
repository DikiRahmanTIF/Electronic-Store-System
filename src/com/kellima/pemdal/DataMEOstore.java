package com.kellima.pemdal;

import java.text.NumberFormat;

public class DataMEOstore {
    private String merek;
    private String kustomer;
    private String jenisproduk;
    private int harga;
    private int jumlah;
    private int TotalHarga;
    private NumberFormat nf;

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }

    public String getKustomer() {
        return kustomer;
    }

    public void setKustomer(String kustomer) {
        this.kustomer = kustomer;
    }

    public String getJenisproduk() {
        return jenisproduk;
    }

    public void setJenisproduk(String jenisproduk) {
        this.jenisproduk = jenisproduk;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int HargaTotalPemesanan(){
        int TotalHarga = getHarga()*getJumlah();
        restartTotalHarga();
        return Integer.parseInt("Rp."+nf.format(TotalHarga));
    }

    public void restartTotalHarga() {
        this.TotalHarga = 0;
    }

}