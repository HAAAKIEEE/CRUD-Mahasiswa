package id.ac.polihasnur.ti.haqi.ujikom.model;

import java.io.Serializable;

public class Mahasiswa implements Serializable {
    public int id;
    public String nama,tanggall,jenisKelamin,alamat;

    public Mahasiswa(int id, String nama, String tanggall, String jenisKelamin, String alamat) {
        this.id = id;
        this.nama = nama;
        this.tanggall = tanggall;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
    }

    public Mahasiswa(String nama, String tanggall, String jenisKelamin, String alamat) {
        this.nama = nama;
        this.tanggall = tanggall;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggall() {
        return tanggall;
    }

    public void setTanggall(String tanggall) {
        this.tanggall = tanggall;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
