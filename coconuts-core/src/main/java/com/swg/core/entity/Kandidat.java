package com.swg.core.entity;

import java.io.Serializable;
import java.util.Date;

public class Kandidat implements Serializable {
    private static final long serialVersionUID = 6381481985312104320L;

    private String namaLengkap;
    private Date tanggalLahir;

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }
}