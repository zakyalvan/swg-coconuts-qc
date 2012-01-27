package com.swg.core.entity;

import java.io.Serializable;

public class Tps implements Serializable {
	private static final long serialVersionUID = -3898123598811145006L;

	private Kelurahan kelurahan;
	private Integer nomor;
	
	public Kelurahan getKelurahan() {
		return kelurahan;
	}
	public void setKelurahan(Kelurahan kelurahan) {
		this.kelurahan = kelurahan;
	}
	
	public Integer getNomor() {
		return nomor;
	}
	public void setNomor(Integer nomor) {
		this.nomor = nomor;
	}
}
