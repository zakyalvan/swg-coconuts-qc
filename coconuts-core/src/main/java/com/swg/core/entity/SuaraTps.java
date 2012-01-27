package com.swg.core.entity;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class SuaraTps implements Serializable {
	private static final long serialVersionUID = 8965616285861917940L;

	private Tps tps;
	private Map<PasanganKandidat, Integer> distribusi = new HashMap<PasanganKandidat, Integer>();

	private Integer jumlahAbstain = 0;
	private Integer jumlahTidakSyah = 0;
	
	public Tps getTps() {
		return tps;
	}
	public void setTps(Tps tps) {
		this.tps = tps;
	}
	
	public Map<PasanganKandidat, Integer> getDistribusi() {
		return distribusi;
	}
	public void setDistribusi(Map<PasanganKandidat, Integer> distribusi) {
		this.distribusi = distribusi;
	}
	
	public Integer getJumlahAbstain() {
		return jumlahAbstain;
	}
	public void setJumlahAbstain(Integer jumlahAbstain) {
		if(jumlahAbstain < 0) {
			throw new InvalidParameterException("Jumlah suara abstain yang valid lebih besar dari 0, diberikan " + jumlahAbstain);
		}
		this.jumlahAbstain = jumlahAbstain;
	}
	
	public Integer getJumlahTidakSyah() {
		return jumlahTidakSyah;
	}
	public void setJumlahTidakSyah(Integer jumlahTidakSyah) {
		if(jumlahTidakSyah < 0) {
			throw new InvalidParameterException("Jumlah suara tidak syah yang valid lebih besar dari 0, diberikan " + jumlahTidakSyah);
		}
		this.jumlahTidakSyah = jumlahTidakSyah;
	}
	
	public Integer getJumlahTotal() {
		Integer total = 0;
		
		for(PasanganKandidat kandidat : distribusi.keySet()) {
			if(distribusi.get(kandidat) != null) {
				total += distribusi.get(kandidat);
			}
		}
		total += jumlahAbstain;
		total += jumlahTidakSyah;
		
		return total;
	}
}