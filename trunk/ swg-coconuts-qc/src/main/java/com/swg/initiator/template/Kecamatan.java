package com.swg.initiator.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Kecamatan implements Wilayah, Serializable {
	private static final long serialVersionUID = -3104141883839737333L;

	private String kode;
	private String nama;
	private Kabupaten kabupaten;
	private List<Kelurahan> daftarKelurahan = new ArrayList<Kelurahan>();
	
	public Kecamatan() {}
	public Kecamatan(String kode, String nama) {
		this.kode = kode;
		this.nama = nama;
	}
	public Kecamatan(String kode, String nama, Kabupaten kabupaten) {
		this(kode, nama);
		this.kabupaten = kabupaten;
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
	
	public Kabupaten getKabupaten() {
		return kabupaten;
	}
	public void setKabupaten(Kabupaten kabupaten) {
		this.kabupaten = kabupaten;
	}
	
	public List<Kelurahan> getDaftarKelurahan() {
		return daftarKelurahan;
	}
	public void setDaftarKelurahan(List<Kelurahan> daftarKelurahan) {
		this.daftarKelurahan = daftarKelurahan;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Kabupaten getInduk() {
		return getKabupaten();
	}
	@Override
	public void setInduk(Wilayah induk) {
		if(!(induk instanceof Kabupaten)) {
			throw new IllegalArgumentException("Jenis wilayah induk " + induk.getClass() + " tidak valid untuk jenis wilayah anak " + getClass());
		}
		setKabupaten((Kabupaten) induk);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Kelurahan> getDaftarAnak() {
		return getDaftarKelurahan();
	}
	@SuppressWarnings("unchecked")
	@Override
	public <N extends Wilayah> void setDaftarAnak(List<N> daftarAnak) {
		for(Wilayah wilayah : daftarAnak) {
			if(!(wilayah instanceof Kelurahan)) {
				throw new IllegalArgumentException("Jenis wilayah anak " + wilayah.getClass() + " tidak valid untuk jenis wilayah induk " + getClass());
			}
		}
		setDaftarKelurahan((List<Kelurahan>) daftarAnak);
	}
}
