package com.swg.base.backend.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PasanganKandidat implements Serializable {
    private static final long serialVersionUID = 6381481985312104320L;
    private Integer urutan;
    private Map<Posisi, Kandidat> pasangan = new HashMap<PasanganKandidat.Posisi, Kandidat>();
    public PasanganKandidat() {
    }

    public PasanganKandidat(Integer urutan) {
        this.urutan = urutan;
    }

    public PasanganKandidat(Kandidat kandidatKepala, Kandidat kandidatWakil) {
        this.pasangan.put(Posisi.KEPALA_DAERAH, kandidatKepala);
        this.pasangan.put(Posisi.WAKIL_KEPALA_DAERAH, kandidatWakil);
    }

    public PasanganKandidat(Integer urutan, Kandidat kandidatKepala, Kandidat kandidatWakil) {
        this.urutan = urutan;
        this.pasangan.put(Posisi.KEPALA_DAERAH, kandidatKepala);
        this.pasangan.put(Posisi.WAKIL_KEPALA_DAERAH, kandidatWakil);
    }

    public Integer getUrutan() {
        return urutan;
    }

    public void setUrutan(Integer urutan) {
        this.urutan = urutan;
    }

    public Map<Posisi, Kandidat> getPasangan() {
        return pasangan;
    }

    public void setPasangan(Map<Posisi, Kandidat> pasangan) {
        this.pasangan = pasangan;
    }

    public Kandidat getKandidatKepala() {
        return pasangan.get(Posisi.KEPALA_DAERAH);
    }

    public void setKandidatKepala(Kandidat kandidatKepala) {
        pasangan.put(Posisi.KEPALA_DAERAH, kandidatKepala);
    }

    public Kandidat getKandidatWakil() {
        return pasangan.get(Posisi.WAKIL_KEPALA_DAERAH);
    }

    public void setKandidatWakil(Kandidat kandidatWakil) {
        this.pasangan.put(Posisi.WAKIL_KEPALA_DAERAH, kandidatWakil);
    }

    public static enum Posisi {
        KEPALA_DAERAH, WAKIL_KEPALA_DAERAH
    }
}
