package id.ac.polihasnur.ti.haqi.ujikom.dao;

import java.util.List;

import id.ac.polihasnur.ti.haqi.ujikom.model.Mahasiswa;

public interface MahasiswaDao {
    void insert(Mahasiswa f);
    void update(int id, Mahasiswa f);
    void delete(int id);
    Mahasiswa getAMahasiswa(int id);
    List<Mahasiswa> getAllMahasiswas();
}
