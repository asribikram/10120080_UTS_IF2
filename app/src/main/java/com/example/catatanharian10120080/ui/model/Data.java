package com.example.catatanharian10120080.ui.model;


//10120080_habib asrib_IF2
public class Data {
    private String id, judul, deskripsi;

    public Data(){

    }

    public Data(String id, String judul, String deskripsi){
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
