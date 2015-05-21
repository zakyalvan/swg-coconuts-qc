package com.swg.core.entity;

import java.io.Serializable;

public class SuaraPasanganKandidat implements Serializable {
    private static final long serialVersionUID = -2230851771502701193L;

    private PasanganKandidat pasanganKandidat;
    private Integer jumlah;

    public PasanganKandidat getPasanganKandidat() {
        return pasanganKandidat;
    }

    public void setPasanganKandidat(PasanganKandidat pasanganKandidat) {
        this.pasanganKandidat = pasanganKandidat;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
}
