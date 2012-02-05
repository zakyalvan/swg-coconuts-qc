package com.swg.core.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Kelas dari object entity yang merepresentasikan sebuah kabupaten.
 * 
 * @author zakyalvan
 */
@Entity
@DiscriminatorValue("district")
public class District extends Region {
	private static final long serialVersionUID = 7965809469184768119L;

	public static final Integer TYPE_KABUPATEN = 1;
	public static final Integer TYPE_KOTA = 2;
	
	@NotNull
	@Column(name="type")
	private Integer type = TYPE_KABUPATEN;

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
