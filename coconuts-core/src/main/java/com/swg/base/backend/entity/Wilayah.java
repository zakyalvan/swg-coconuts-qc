package com.swg.base.backend.entity;

import java.io.Serializable;
import java.util.List;

public interface Wilayah extends Serializable {
    public String getKode();

    public void setKode(String kode);

    public String getNama();

    public void setNama(String nama);

    public <M extends Wilayah> M getInduk();

    public <M extends Wilayah> void setInduk(M induk);

    public <N extends Wilayah> List<N> getDaftarAnak();

    public <N extends Wilayah> void setDaftarAnak(List<N> daftarAnak);
}