package com.swg.initiator.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Kabupaten implements Wilayah, Serializable {
	private static final long serialVersionUID = -7308323711431607765L;

	public static enum Jenis {
		KABUPATEN, KOTA
	}
	
	private String kode;
	private String nama;
	private Jenis jenis;
	private Propinsi propinsi;
	private List<Kecamatan> daftarKecamatan = new ArrayList<Kecamatan>();
	
	public Kabupaten() {
		jenis = Jenis.KABUPATEN;
	}
	public Kabupaten(String kode, String nama) {
		this();
		this.kode = kode;
		this.nama = nama;
	}
	public Kabupaten(String kode, String nama, Jenis jenis) {
		this(kode, nama);
		this.jenis = jenis;
	}
	public Kabupaten(String kode, String nama, Propinsi propinsi) {
		this(kode, nama);
		this.propinsi = propinsi;
	}
	public Kabupaten(String kode, String nama, Jenis jenis, Propinsi propinsi) {
		this(kode, nama, propinsi);
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
	
	public Propinsi getPropinsi() {
		return propinsi;
	}
	public void setPropinsi(Propinsi propinsi) {
		this.propinsi = propinsi;
	}
	
	public List<Kecamatan> getDaftarKecamatan() {
		return daftarKecamatan;
	}
	public void setDaftarKecamatan(List<Kecamatan> daftarKecamatan) {
		this.daftarKecamatan = daftarKecamatan;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Propinsi getInduk() {
		return getPropinsi();
	}
	@Override
	public void setInduk(Wilayah induk) {
		if(!(induk instanceof Propinsi)) {
			throw new IllegalArgumentException("Jenis wilayah induk " + induk.getClass() + " tidak valid untuk jenis wilayah anak " + getClass());
		}
		setPropinsi((Propinsi) induk);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Kecamatan> getDaftarAnak() {
		return getDaftarKecamatan();
	}
	@SuppressWarnings("unchecked")
	@Override
	public <N extends Wilayah> void setDaftarAnak(List<N> daftarAnak) {
		for(Wilayah wilayah : daftarAnak) {
			if(!(wilayah instanceof Kecamatan)) {
				throw new IllegalArgumentException("Jenis wilayah anak " + wilayah.getClass() + " tidak valid untuk jenis wilayah induk " + getClass());
			}
		}
		setDaftarKecamatan((List<Kecamatan>) daftarAnak);
	}
}