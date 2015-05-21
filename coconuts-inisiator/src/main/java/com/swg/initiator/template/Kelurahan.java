package com.swg.initiator.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Kelurahan implements Wilayah, Serializable {
    private static final long serialVersionUID = -3168315950583881102L;
    private String kode;
    private String nama;
    private Jenis jenis;
    private Kecamatan kecamatan;
    private List<Tps> daftarTps = new ArrayList<Tps>();

    public Kelurahan() {
        jenis = Jenis.KELURAHAN;
    }

    public Kelurahan(String kode, String nama) {
        this();
        this.kode = kode;
        this.nama = nama;
    }

    public Kelurahan(String kode, String nama, Kecamatan kecamatan) {
        this(kode, nama);
        this.kecamatan = kecamatan;
    }

    public Kelurahan(String kode, String nama, Jenis jenis, Kecamatan kecamatan) {
        this(kode, nama, kecamatan);
        this.jenis = jenis;
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

    public Jenis getJenis() {
        return jenis;
    }

    public void setJenis(Jenis jenis) {
        this.jenis = jenis;
    }

    public Kecamatan getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(Kecamatan kecamatan) {
        this.kecamatan = kecamatan;
    }

    public List<Tps> getDaftarTps() {
        return daftarTps;
    }

    public void setDaftarTps(List<Tps> daftarTps) {
        this.daftarTps = daftarTps;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Kecamatan getInduk() {
        return getKecamatan();
    }

    @Override
    public void setInduk(Wilayah induk) {
        if (!(induk instanceof Kecamatan)) {
            throw new IllegalArgumentException("Jenis wilayah induk " + induk.getClass() + " tidak valid untuk jenis wilayah anak " + getClass());
        }
        setKecamatan((Kecamatan) induk);
    }

    @Override
    public <N extends Wilayah> List<N> getDaftarAnak() {
        throw new RuntimeException("Jenis wilayah " + getClass() + " tidak memiliki anak.");
    }

    @Override
    public <N extends Wilayah> void setDaftarAnak(List<N> daftarAnak) {
        throw new RuntimeException("Tidak dapat mengeset daftar anak untuk jenis wilayah " + getClass());
    }

    @Override
    public String toString() {
        return nama;
    }

    public static enum Jenis {
        KELURAHAN, DESA
    }
}