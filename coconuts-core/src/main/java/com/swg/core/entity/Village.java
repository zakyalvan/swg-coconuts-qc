package com.swg.core.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("village")
public class Village extends Region {
	private static final long serialVersionUID = 7706869318681425849L;

	public static final Integer TYPE_KELURAHAN = 3;
	public static final Integer TYPE_DESA = 4;
	
	@Column(name="type")
	private Integer type = TYPE_KELURAHAN;

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
