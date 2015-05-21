package com.swg.base.backend.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Propinsi implements Wilayah, Serializable {
    private static final long serialVersionUID = 5570025152216369139L;

    private String kode;
    private String nama;
    private List<Kabupaten> daftarKabupaten = new ArrayList<Kabupaten>();

    public Propinsi() {
    }

    public Propinsi(String kode, String nama) {
        this.kode = kode;
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<Kabupaten> getDaftarKabupaten() {
        return daftarKabupaten;
    }

    public void setDaftarKabupaten(List<Kabupaten> daftarKabupaten) {
        this.daftarKabupaten = daftarKabupaten;
    }

    @Override
    public <M extends Wilayah> M getInduk() {
        return null;
    }

    @Override
    public <M extends Wilayah> void setInduk(M induk) {
        throw new RuntimeException("Jenis wilayah " + getClass() + " tidak memiliki induk (Dalam konteks aplikasi)");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Kabupaten> getDaftarAnak() {
        return getDaftarKabupaten();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <N extends Wilayah> void setDaftarAnak(List<N> daftarAnak) {
        for (Wilayah wilayah : daftarAnak) {
            if (!(wilayah instanceof Kabupaten)) {
                throw new IllegalArgumentException("Jenis wilayah anak " + wilayah.getClass() + " tidak valid untuk jenis wilayah induk " + getClass());
            }
        }
        setDaftarKabupaten((List<Kabupaten>) daftarAnak);
    }
}