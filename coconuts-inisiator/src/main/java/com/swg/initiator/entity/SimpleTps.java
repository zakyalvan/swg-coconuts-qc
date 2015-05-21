package com.swg.initiator.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Simplified version for testing purpose
 *
 * @author satriaprayoga
 */
@Entity
public class SimpleTps implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String kelurahan;

    private String kodeKelurahan;

    private String kecamatan;

    private String kodeKecamatan;

    private String kabupaten;

    private String kodeKabupaten;

    @OneToOne(mappedBy = "simpleTps", cascade = CascadeType.ALL)
    private VoteObserver voteObserver;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKodeKelurahan() {
        return kodeKelurahan;
    }

    public void setKodeKelurahan(String kodeKelurahan) {
        this.kodeKelurahan = kodeKelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }


    public String getKodeKecamatan() {
        return kodeKecamatan;
    }

    public void setKodeKecamatan(String kodeKecamatan) {
        this.kodeKecamatan = kodeKecamatan;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getKodeKabupaten() {
        return kodeKabupaten;
    }

    public void setKodeKabupaten(String kodeKabupaten) {
        this.kodeKabupaten = kodeKabupaten;
    }

    public VoteObserver getVoteObserver() {
        return voteObserver;
    }

    public void setVoteObserver(VoteObserver voteObserver) {
        this.voteObserver = voteObserver;
    }

    @Override
    public String toString() {
        return "SimpleTps [id=" + id + ", kelurahan=" + kelurahan
                + ", kodeKelurahan=" + kodeKelurahan + ", kecamatan="
                + kecamatan + ", kodeKecamatan=" + kodeKecamatan
                + ", kabupaten=" + kabupaten + ", kodeKabupaten="
                + kodeKabupaten + ", voteObserver=" + voteObserver + "]";
    }


}
